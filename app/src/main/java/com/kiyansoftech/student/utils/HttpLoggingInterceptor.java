/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kiyansoftech.student.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;

import static okhttp3.internal.platform.Platform.INFO;

public final class HttpLoggingInterceptor implements Interceptor {
    private boolean isMerge = false;

    private final char TOP_LEFT_CORNER = '┌';
    private final char MIDDLE_CORNER = '├';
    private final char HORIZONTAL_LINE = '│';
    private final char BOTTOM_LEFT_CORNER = '└';

    private final String DOUBLE_DIVIDER = "──────────────────────────────────────────────────────────────────────────────";
    private final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";

    private String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;
    private String MIDDLE_BOLD_BORDER = MIDDLE_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {NONE, BASIC, HEADERS, BODY}

    public interface Logger {
        void log(String message);

        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;
    Charset charset = UTF8;

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String param = "";
        try {
            Buffer bufferq = new Buffer();
            request.body().writeTo(bufferq);

            MediaType contentTypeq = request.body().contentType();
            if (contentTypeq != null) {
                charset = contentTypeq.charset(UTF8);
            }
            param = bufferq.readString(charset);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        String headers = "";
        for (int i = 0, count = request.headers().size(); i < count; i++) {
            headers += "  -H '" + request.headers().name(i) + ": " + request.headers().value(i) + "' \\\n";
        }
        if (param.length() > 0) {
            headers += "  -d '" + param + "' \\\n";
            String[] paramList = param.split("&");
            try {
                for (String s : paramList) {
                    headers += "  -F '" + s + "' \\\n";
                }
            } catch (Exception e) {

            }
        }
        Log.i("OkHttp: --> ", "\\\ncurl -X " + request.method() + " \\\n" + "  " + request.url() + " \\\n" + headers);
//        printRequest(request.method() + ' ' + request.url(), request.headers(), param);

        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logger.log("<-- HTTP FAILED: " + e);
            throw e;
        }

        ResponseBody responseBody = response.body();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffers = source.buffer();
        MediaType contentTypes = responseBody.contentType();
        if (contentTypes != null) {
            charset = contentTypes.charset(UTF8);
        }
        printResponse(response, buffers.clone().readString(charset).trim());

        return response;
    }

    StringBuilder log;

    private void printRequest(String title, Headers headers, String param) {
        try {
            log = new StringBuilder(TOP_BORDER);
            log.append("\n>" + HORIZONTAL_LINE + "--> " + title);
            log.append("\n>" + MIDDLE_BORDER);
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    log.append("\n>" + HORIZONTAL_LINE).append(name).append(": ").append(headers.value(i));
                }
            }
            if (param.length() > 0) {
                log.append("\n>" + MIDDLE_BORDER);
                log.append("\n>" + HORIZONTAL_LINE).append(param.length() < 1000 ? param : "<PARAM>");
            }
            if (!isMerge) {
                log.append("\n>" + MIDDLE_BORDER);
                log.append("\n>" + HORIZONTAL_LINE + "--> END " + title);
                log.append("\n>" + BOTTOM_BORDER);
                logger.log(log.toString());
            } else
                log.append("\n>" + MIDDLE_BOLD_BORDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printResponse(Response response, String json) {
        try {
            String title = response.code() + " " + response.request().method() + " " + response.request().url();
            if (!isMerge) {
                log = new StringBuilder(TOP_BORDER);
                log.append("\n" + HORIZONTAL_LINE + "<-- " + title);
                log.append("\n" + MIDDLE_BORDER);
            }
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                log.append("\n" + HORIZONTAL_LINE).append(jsonObject.toString(2).replaceAll("\n<", "\n<" + HORIZONTAL_LINE));
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                log.append("\n" + HORIZONTAL_LINE).append(jsonArray.toString(2).replaceAll("\n<", "\n<" + HORIZONTAL_LINE));
            } else
                log.append("\n" + HORIZONTAL_LINE).append(json);
            log.append("\n" + MIDDLE_BORDER);
            log.append("\n" + HORIZONTAL_LINE + "<-- END " + title);
            log.append("\n" + BOTTOM_BORDER);
            logger.log(log.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
