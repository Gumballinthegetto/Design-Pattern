package kh.edu.rupp.ite.visitmekotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.visitmekotlin.api.model.ApiData
import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import kh.edu.rupp.ite.visitmekotlin.api.model.Status
import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesViewModel: ViewModel() {

    private val _provincesData = MutableLiveData<ApiData<List<Provinces>>>()
    val provincesData: LiveData<ApiData<List<Provinces>>>
        get() = _provincesData

    fun loadProvinceList() {

        val apiData = ApiData<List<Provinces>>(Status.PROCESSING, null)
        _provincesData.postValue(apiData)

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
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _provincesData.postValue(apiData)
                } else {
                    val apiData = ApiData<List<Provinces>>(Status.ERROR, null)
                    _provincesData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<List<Provinces>>, t: Throwable) {
                val apiData = ApiData<List<Provinces>>(Status.ERROR, null)
                _provincesData.postValue(apiData)
            }
        })
    }
}