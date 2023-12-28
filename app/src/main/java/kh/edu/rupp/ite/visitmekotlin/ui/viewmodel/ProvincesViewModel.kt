package kh.edu.rupp.ite.visitmekotlin.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.visitmekotlin.api.model.ApiData
import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import kh.edu.rupp.ite.visitmekotlin.api.model.Status
import kh.edu.rupp.ite.visitmekotlin.client.ApiClient
import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import kh.edu.rupp.ite.visitmekotlin.utility.AppPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ProvincesViewModel: ViewModel() {

    private val _provincesData = MutableLiveData<ApiData<List<Provinces>>>()
    val provincesData: LiveData<ApiData<List<Provinces>>>
        get() = _provincesData

    fun loadProvinceList() {

        var apiData = ApiData<List<Provinces>>(Status.PROCESSING, null)
        _provincesData.postValue(apiData)

        viewModelScope.launch(Dispatchers.IO) {
            apiData = try {
                val response = ApiClient.get().apiService.loadProvinceList()
                ApiData(Status.SUCCESS, response)
            } catch (ex: Exception) {
                Log.e("ited", "Load province error: ${ex.message}")
                ApiData(Status.ERROR, null)
            }

            withContext(Dispatchers.Main.immediate) {
                _provincesData.postValue(apiData)
            }
        }
    }

    fun login(context: Context, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.get().apiService.login()
                AppPreference.get(context).storeApiToken(response)
            } catch (ex: Exception) {
                Log.e("ited", "Load provinces error: ${ex.message}")
            }
        }
    }
}