package com.oeye.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.kiyansoftech.student.Network.ItemTypeAdapterFactory
import com.kiyansoftech.student.utils.ApiInterface
import com.kiyansoftech.student.utils.HttpLoggingInterceptor

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class Networking(private val context: Context? = null) {
    private var baseURL: String = "http://kptechs.in/Novelle/Api/"

    companion object {
        /**
         * @param context
         * @return Instance of this class
         * create instance of this class
         */
        fun with(context: Context? = null): Networking {
            return Networking(context)
        }

       /* fun wrapParams(params: HashMap<String, *>): RequestBody {
            return JSONObject(params).toString()
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        }*/
    }

    fun getServices(): ApiInterface {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)

        //Authentication
        //httpClient.interceptors().add(SessionInterceptor())

        //Log
        val logging = HttpLoggingInterceptor()
       // logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        //GSON converter
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ItemTypeAdapterFactory())
            .create()

        return retrofit2.Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build().create(ApiInterface::class.java)



    }

/*
    fun getServices2(): APIInterface {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)

        //Authentication
        httpClient.interceptors().add(SessionInterceptor())

        //Log
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        //GSON converter
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ItemTypeAdapterFactory())
            .create()

        return retrofit2.Retrofit.Builder()
            .baseUrl(baseURL2)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build().create(APIInterface::class.java)
    }
*/
}

