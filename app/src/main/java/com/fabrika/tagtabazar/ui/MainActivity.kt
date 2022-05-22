package com.fabrika.tagtabazar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.fabrika.tagtabazar.R
import com.fabrika.tagtabazar.databinding.ActivityMainBinding
import com.fabrika.tagtabazar.fragments.ProductDetailFragment
import com.fabrika.tagtabazar.fragments.ProductListFragment

class MainActivity : AppCompatActivity() {

    lateinit var productListFragment :ProductListFragment
    lateinit var productDetailFragment :ProductDetailFragment
    lateinit var fragmentManager: FragmentManager
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        window.statusBarColor =
            ContextCompat.getColor(this, R.color.white) // set status background white


        productListFragment = ProductListFragment()
        productDetailFragment = ProductDetailFragment()

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, productListFragment).commit()




    }
}