package kh.edu.rupp.ite.visitmekotlin.presenter

import android.widget.Toast
import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import kh.edu.rupp.ite.visitmekotlin.ui.view.ProvincesView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesPresenter(private val provincesView: ProvincesView) {

    fun loadProvinceList() {
        val httpClient = Retrofit.Builder()
            .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = httpClient.create(ApiService::class.java)

        val task: Call<List<Provinces>> = apiService.loadProvinceList()
        task.enqueue(object : Callback<List<Provinces>> {
            override fun onResponse(
                call: Call<List<Provinces>>,
                response: Response<List<Provinces>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { provincesView.showProvinceList(it) }
                }
            }

            override fun onFailure(call: Call<List<Provinces>>, t: Throwable) {
                provincesView.showError("Load province list failed!")
            }
        })
    }

    fun onProvinceClicked(province: Provinces) {
        provincesView.showProvinceDetail(province)
    }
}