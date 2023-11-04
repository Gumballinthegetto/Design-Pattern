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
import kh.edu.rupp.ite.visitmekotlin.databinding.FragmentProvincesBinding
import kh.edu.rupp.ite.visitmekotlin.presenter.ProvincesPresenter
import kh.edu.rupp.ite.visitmekotlin.ui.view.ProvincesView

class ProvincesFragment : Fragment(), ProvincesView {

    private lateinit var binding: FragmentProvincesBinding
    private lateinit var presenter: ProvincesPresenter
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvincesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)

        adapter = RecyclerViewAdapter()

        binding.provincesRecyclerView.layoutManager = layoutManager
        binding.provincesRecyclerView.adapter = adapter

        presenter = ProvincesPresenter(this)
        presenter.loadProvinceList()
    }

    override fun showProvinceList(provinceList: List<Provinces>) {
        if (provinceList.isEmpty()) {
            Toast.makeText(context, "No province is found.", Toast.LENGTH_SHORT).show()
        } else {
            adapter.submitList(provinceList)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProvinceDetail(province: Provinces) {
        TODO("Not yet implemented")
    }
}