package com.example.textileandro040426.fragment // Sesuaikan package-mu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textileandro040426.MainActivity
import com.example.textileandro040426.Textile
import com.example.textileandro040426.databinding.FragmentHomeBinding
import com.example.textileandro040426.TextileData
import com.example.textileandro040426.TextileAdapter


class HomeFragment : Fragment() {

    // view binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // menggunakan binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityUtama = (activity as MainActivity)
        val dataKain = activityUtama.masterListKain

        // recyclerview
        binding.rvTextile.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
            setHasFixedSize(true)
        }

        val adapter = TextileAdapter(dataKain)
        binding.rvTextile.adapter = adapter

        // List untuk menampung sementara barang yang dipilih
        val cartList = mutableListOf<Textile>()

        adapter.setOnAdapterListener(object : TextileAdapter.OnAdapterListener {
            override fun onQuantityChanged(data: Textile, quantity: Int) {
                // Logika update cartList di sini (Tambah jika > 0, hapus jika 0
            }
        })



        adapter.setOnAdapterListener(object : TextileAdapter.OnAdapterListener {
            override fun onQuantityChanged(data: Textile, quantity: Int) {
                val activityUtama = (activity as MainActivity)

                if (quantity > 0) {
                    // mengecek apakah barang ada di keranjang
                    val itemAda = activityUtama.globalCartList.find { it.name == data.name }
                    if (itemAda == null) {
                        // kalau belum ada, maka akan ditambah
                        activityUtama.globalCartList.add(data)
                    }
                } else {
                    // kalau quantity 0, maka dihapus dari keranjang
                    activityUtama.globalCartList.removeAll { it.name == data.name }
                }
            }
        })


    }

    // membersihkan binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}