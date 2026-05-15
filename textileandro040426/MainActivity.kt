package com.example.textileandro040426
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.textileandro040426.fragment.HomeFragment
import com.example.textileandro040426.fragment.CartFragment
import com.example.textileandro040426.fragment.PaymentFragment
import androidx.fragment.app.Fragment
import com.example.textileandro040426.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // view binding
    private lateinit var binding: ActivityMainBinding
    val masterListKain by lazy { TextileData.listTextile }
    val globalCartList = mutableListOf<Textile>()

    override fun onCreate(savedInstanceState: Bundle?) {

        // memulai splashscreen sebelum activity main
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // pemindahan fragment saat di nav bar berganti
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {replaceFragment(HomeFragment()); true}

                R.id.nav_cart -> {if (globalCartList.isNotEmpty()) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("EXTRA_ITEMS", ArrayList(globalCartList))

                    val cartFragment = CartFragment()
                    cartFragment.arguments = bundle
                    replaceFragment(cartFragment)
                    true
                } else {
                    // jika user belum menambah
                    replaceFragment(CartFragment())
                    android.widget.Toast.makeText(this, "Keranjang masih kosong!", android.widget.Toast.LENGTH_SHORT).show()
                }; true}

                R.id.nav_pay -> {if (globalCartList.isNotEmpty()) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("EXTRA_ITEMS", ArrayList(globalCartList))

                    val paymentFragment = PaymentFragment()
                    paymentFragment.arguments = bundle
                    replaceFragment(paymentFragment)
                    true
                } else {
                    // jika cart kosong
                    replaceFragment(CartFragment())
                    android.widget.Toast.makeText(this, "Tidak ada item yang dibeli", android.widget.Toast.LENGTH_SHORT).show()
                }; true}
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun setSelectedMenu(itemId: Int) {
        binding.bottomNavigation.selectedItemId = itemId
    }
}