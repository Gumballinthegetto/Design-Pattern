package kh.edu.rupp.ite.visitmekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.visitmekotlin.api.model.ProvincesModel
import kh.edu.rupp.ite.visitmekotlin.databinding.ViewholderProvinceBinding

class RecyclerViewAdapter: ListAdapter<ProvincesModel, RecyclerViewAdapter.ProvinceViewHolder>(ProvinceItemCallBack()) {

    private lateinit var binding: ViewholderProvinceBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewholderProvinceBinding.inflate(layoutInflater, parent, false)
        return ProvinceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val provinceItem: ProvincesModel = getItem(position)
        holder.bind(provinceItem)
    }

    class ProvinceItemCallBack: DiffUtil.ItemCallback<ProvincesModel>() {
        override fun areItemsTheSame(
            oldItem: ProvincesModel,
            newItem: ProvincesModel
        ): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        override fun areContentsTheSame(
            oldItem: ProvincesModel,
            newItem: ProvincesModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    class ProvinceViewHolder(private var itemBinding: ViewholderProvinceBinding): RecyclerView.ViewHolder(itemBinding.root)  {

        fun bind(provinceItem: ProvincesModel) {
            Picasso.get().load(provinceItem.getImageUrl()).into(itemBinding.provinceImage)
            itemBinding.provinceName.text = provinceItem.getName()
        }
    }
}