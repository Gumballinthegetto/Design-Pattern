package kh.edu.rupp.ite.visitmekotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.visitmekotlin.adapter.RecyclerViewAdapter
import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces
import kh.edu.rupp.ite.visitmekotlin.api.model.Status
import kh.edu.rupp.ite.visitmekotlin.databinding.FragmentProvincesBinding
import kh.edu.rupp.ite.visitmekotlin.service.ApiService
import kh.edu.rupp.ite.visitmekotlin.ui.viewmodel.ProvincesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesFragment : Fragment() {

    private lateinit var binding: FragmentProvincesBinding
    private val viewModel = ProvincesViewModel()
    private lateinit var adapter: RecyclerViewAdapter

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

        adapter = RecyclerViewAdapter()

        val layoutManager = LinearLayoutManager(context)

        binding.provincesRecyclerView.layoutManager = layoutManager
        binding.provincesRecyclerView.adapter = adapter

        viewModel.loadProvinceList()

        viewModel.provincesData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.PROCESSING -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> adapter.submitList(it.data)
                Status.ERROR -> Toast.makeText(context, "Error while loading data from server", Toast.LENGTH_SHORT).show()
            }
        }
    }
}