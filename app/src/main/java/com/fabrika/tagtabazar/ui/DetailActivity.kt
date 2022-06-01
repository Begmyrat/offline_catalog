package com.fabrika.tagtabazar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.fabrika.tagtabazar.R
import com.fabrika.tagtabazar.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding
    private val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor =
            ContextCompat.getColor(this, R.color.white) // set status background white


        val extras = intent.extras
        val s = extras?.get("i").toString()
        val a = s.split(",")

        a.forEach{ e ->
            list.add(CarouselItem(imageUrl = e))
        }
        carousel.setData(list)

    }
}