package kh.edu.rupp.ite.visitmekotlin.api.model

import com.google.gson.annotations.SerializedName

data class ProvincesViewModel(
    @SerializedName("imageUrl")
    private val imageURL: String,
    private val name: String
) {
    fun getImageUrl(): String {
        return this.imageURL
    }

    fun getName(): String {
        return this.name
    }
}