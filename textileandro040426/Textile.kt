package com.example.textileandro040426
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Textile (
    val image: Int,
    val id: Int,
    val name: String,
    val type: TextileType,
    val price: Int,
    var stock: Int,
    var quantity: Int = 0

) : Parcelable

enum class TextileType {
    Cotton, Silk, Polyester
}