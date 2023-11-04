package kh.edu.rupp.ite.visitmekotlin.service

import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/provinces.json")
    fun loadProvinceList(): Call<List<Provinces>>
}