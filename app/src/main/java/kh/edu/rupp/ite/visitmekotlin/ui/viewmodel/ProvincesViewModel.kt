package kh.edu.rupp.ite.visitmekotlin.ui.viewmodel

import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesViewModel {

    fun loadProvinceList() {
        val httpClient = Retrofit.Builder()
            .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = httpClient.create(ApiService::class.java)

    }
}