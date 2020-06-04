package com.Bohni.Network

import com.kiyansoftech.student.utils.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://kptechs.in/Novelle/Api/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(interceptor).build()
//                httpClient.addInterceptor { chain ->
//                    val original = chain.request()
//                    var request: Request? = null
//                    if (GlobalPreference(MyApplication.context!!).getString(GlobalPreference.USER_TYPE) == GlobalPreference.ADVISOR) {
//                        request = original.newBuilder()
//                            .method(original.method(), original.body()).header(
//                                "Authorization",
//                                "Bearer " + GlobalPreference(MyApplication.context!!).getString(
//                                    GlobalPreference.ADVISOR_APITOKEN
//                                )
//                            )
//                            .header("device-type", "ANDROID")
//                            .header("Accept", "application/json")
//                            .build()
//                        Log.e(
//                            "token : ",
//                            GlobalPreference(MyApplication.context!!).getString(GlobalPreference.ADVISOR_APITOKEN) + " nn "
//                        )
//                    } else if (GlobalPreference(MyApplication.context!!).getString(GlobalPreference.USER_TYPE) == GlobalPreference.RETAILER) {
//                        request = original.newBuilder()
//                            .method(original.method(), original.body()).header(
//                                "Authorization",
//                                "Bearer " + GlobalPreference(context!!).getString(
//                                    GlobalPreference.RETAILER_APITOKEN
//                                )
//                            )
//                            .header("device-type", "ANDROID")
//                            .header("Accept", "application/json")
//                            .build()
//                        Log.e(
//                            "token : ",
//                            GlobalPreference(MyApplication.context!!).getString(GlobalPreference.RETAILER_APITOKEN) + " nn "
//                        )
//                    } else if (GlobalPreference(MyApplication.context!!).getString(GlobalPreference.USER_TYPE) == GlobalPreference.USER) {
//                        request = original.newBuilder()
//                            .method(original.method(), original.body()).header(
//                                "Authorization",
//                                "Bearer " + GlobalPreference(context!!).getString(
//                                    GlobalPreference.FARMER_APITOKEN
//                                )
//                            )
//                            .header("device-type", "ANDROID")
//                            .header("Accept", "application/json")
//                            .build()
//                        Log.e(
//                            "token : ",
//                            GlobalPreference(MyApplication.context!!).getString(GlobalPreference.FARMER_APITOKEN) + " nn "
//                        )
//                    }
//
//                    chain.proceed(request!!)
//                }
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }
}