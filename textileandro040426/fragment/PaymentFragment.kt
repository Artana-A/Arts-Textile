package com.example.textileandro040426.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textileandro040426.MainActivity
import com.example.textileandro040426.R
import com.example.textileandro040426.Textile
import com.example.textileandro040426.TextileAdapter
import com.example.textileandro040426.TextileData
import com.example.textileandro040426.databinding.FragmentCartBinding
import com.example.textileandro040426.databinding.FragmentHomeBinding
import com.example.textileandro040426.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // mengambil data
        val dataDiterima = arguments?.getParcelableArrayList<Textile>("EXTRA_ITEMS")

        var saldo : Int = 1000000
        var total: Int = 0


        // mengecek data
        if (!dataDiterima.isNullOrEmpty()) {

            // tampilkan ke RecyclerView
            setupRecyclerView(dataDiterima)

            for (item in dataDiterima) {
                total += (item.price * item.quantity)
            }

        } else {
            Toast.makeText(requireContext(), "Keranjang masih kosong.", Toast.LENGTH_LONG).show()
        }


        // ---------- logika penghitungan pembayaran ----------
        val sisaSaldo = saldo - total

        binding.apply {

            // penampilan uang
            tvSaldo.text = "Rp $saldo"
            tvTotal.text = "Rp $total"
            tvSisaSaldo.text = "Rp $sisaSaldo"

            btnBuy.setOnClickListener {
                if (sisaSaldo < 0) {
                    Toast.makeText(requireContext(), "Saldo tidak cukup!", Toast.LENGTH_SHORT).show()
                } else {

                    val activityUtama = (activity as MainActivity)
                    activityUtama.masterListKain.forEach { kain ->
                        kain.stock += kain.quantity // kembalikan quantity ke stok
                        kain.quantity = 0           // reset jumlah beli jadi 0
                    }

                    // pada saat pembayara berhasil, akan di reset
                    activityUtama.globalCartList.clear()
                    activityUtama.setSelectedMenu(R.id.nav_home)
                    activityUtama.replaceFragment(HomeFragment())

                    Toast.makeText(requireContext(), "Pembayaran Berhasil!", Toast.LENGTH_SHORT)
                        .show()


                }
            }
        }


    }

    // recycler group
    private fun setupRecyclerView(list: ArrayList<Textile>) {

        // adapter
        val adapter = TextileAdapter(list)
        binding.rvPay.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPay.adapter = adapter

        adapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}