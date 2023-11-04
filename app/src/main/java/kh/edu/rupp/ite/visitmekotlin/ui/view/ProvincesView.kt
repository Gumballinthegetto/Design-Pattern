package kh.edu.rupp.ite.visitmekotlin.ui.view

import kh.edu.rupp.ite.visitmekotlin.api.model.Provinces

interface ProvincesView {

    fun showProvinceList(provinceList: List<Provinces>)

    fun showError(message: String)

    fun showProvinceDetail(province: Provinces)

}