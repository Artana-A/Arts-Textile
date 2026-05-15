package com.example.textileandro040426

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.textileandro040426.databinding.ItemTextileBinding
import java.text.NumberFormat
import java.util.Locale

class TextileAdapter(private val listTextile: List<Textile>) :
    RecyclerView.Adapter<TextileAdapter.TextileViewHolder>() {

    // interface
    interface OnAdapterListener {
        fun onQuantityChanged(data: Textile, quantity: Int)
    }

    private var listener: OnAdapterListener? = null

    fun setOnAdapterListener(listener: OnAdapterListener) {
        this.listener = listener
    }

    class TextileViewHolder(val binding: ItemTextileBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextileViewHolder {
        val binding = ItemTextileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextileViewHolder, position: Int) {
        val dataKain = listTextile[position]
        // destructuring data class
        var (gambar, id, nama, tipe, harga, stok, jumlahInput) = dataKain

        holder.binding.apply {
            ivTextile.setImageResource(gambar)
            tvTextileName.text = nama
            tvTextileType.text = tipe.name
            tvTextilePrice.text = "Rp $harga / meter"
            tvTextileStock.text = "Stok: $stok"
            tvItemCount.text = "$jumlahInput"

            if (dataKain.quantity > 0) {
                tvItemCount.visibility = View.VISIBLE
            } else {
                tvItemCount.visibility = View.GONE
            }

            btnAdd.setOnClickListener {
                if (dataKain.quantity < dataKain.stock) {
                    jumlahInput++
                    stok--

                    dataKain.quantity = jumlahInput
                    dataKain.stock = stok

                    tvTextileStock.text = "Stok: $stok"
                    tvItemCount.text = jumlahInput.toString()
                    tvItemCount.visibility = View.VISIBLE
                    listener?.onQuantityChanged(dataKain, jumlahInput)

                } else {
                    Toast.makeText(holder.itemView.context, "Stok kosong!", Toast.LENGTH_SHORT).show()
                }
            }

            btnDec.setOnClickListener {
                if (dataKain.quantity > 0) {
                    jumlahInput--
                    stok++

                    dataKain.quantity = jumlahInput
                    dataKain.stock = stok

                    tvTextileStock.text = "Stok: $stok"
                    tvItemCount.text = if (jumlahInput > 0) jumlahInput.toString() else ""
                    if (jumlahInput == 0) tvItemCount.visibility = View.GONE
                    listener?.onQuantityChanged(dataKain, jumlahInput)
                } else {
                    Toast.makeText(holder.itemView.context, "Item pada keranjang kosong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = listTextile.size

}