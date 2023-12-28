package kh.edu.rupp.ite.visitmekotlin.service

import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/provinces.json")
    suspend fun loadProvinceList(): List<Provinces>

    @POST("/login")
    suspend fun login(): String
}