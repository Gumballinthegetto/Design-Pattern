package kh.edu.rupp.ite.visitmekotlin.client

import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()

    private val httpClient = Retrofit.Builder()
        .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = httpClient.create(ApiService::class.java)

    companion object {

        private var instance: ApiClient? = null

        fun get(): ApiClient {
            if (instance == null) {
                instance = ApiClient()
            }
            return instance!!
        }
    }
}