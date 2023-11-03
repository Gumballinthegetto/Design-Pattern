package kh.edu.rupp.ite.visitmekotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.visitmekotlin.adapter.RecyclerViewAdapter
import kh.edu.rupp.ite.visitmekotlin.api.model.ProvincesViewModel
import kh.edu.rupp.ite.visitmekotlin.databinding.FragmentProvincesBinding
import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesFragment : Fragment() {

    private lateinit var binding: FragmentProvincesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProvincesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProvinceList()
    }

    private fun loadProvinceList() {
        val httpClient = Retrofit.Builder()
            .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = httpClient.create(ApiService::class.java)

        val task: Call<List<ProvincesViewModel>> = apiService.loadProvinceList()
        task.enqueue(object : Callback<List<ProvincesViewModel>> {
            override fun onResponse(
                call: Call<List<ProvincesViewModel>>,
                response: Response<List<ProvincesViewModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { showProvinceList(it) }
                }
            }

            override fun onFailure(call: Call<List<ProvincesViewModel>>, t: Throwable) {
                Toast.makeText(context, "Load province list failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProvinceList(provinceList: List<ProvincesViewModel>) {
        val layoutManager = LinearLayoutManager(context)

        val adapter = RecyclerViewAdapter()
        adapter.submitList(provinceList)

        binding.provincesRecyclerView.layoutManager = layoutManager
        binding.provincesRecyclerView.adapter = adapter
    }
}