package com.example.textileandro040426.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textileandro040426.Textile
import com.example.textileandro040426.TextileAdapter
import com.example.textileandro040426.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // mengambil array list
        val dataDiterima = arguments?.getParcelableArrayList<Textile>("EXTRA_ITEMS")

        // mengecek adakah data yang sudah dimasukkan
        if (!dataDiterima.isNullOrEmpty()) {
            // Tampilkan ke RecyclerView
            setupRecyclerView(dataDiterima)
        } else {
            Toast.makeText(requireContext(), "Keranjang masih kosong.", Toast.LENGTH_LONG).show()
        }

    }

    private fun setupRecyclerView(list: ArrayList<Textile>) {
        // menggunakan adapter TextileAdapter
        val adapter = TextileAdapter(list)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}