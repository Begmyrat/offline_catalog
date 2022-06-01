package com.fabrika.tagtabazar.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabrika.tagtabazar.R
import com.fabrika.tagtabazar.adapters.CategoryListAdapter
import com.fabrika.tagtabazar.adapters.ProductListAdapter
import com.fabrika.tagtabazar.databinding.ActivityMainBinding
import com.fabrika.tagtabazar.model.Category
import com.fabrika.tagtabazar.model.Product
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductListAdapter
    lateinit var categoryAdapter: CategoryListAdapter
    lateinit var db: FirebaseFirestore
    var list = mutableListOf<Product>()

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor =
            ContextCompat.getColor(this, R.color.white) // set status background white

        db = FirebaseFirestore.getInstance()

        getCategories()
        getProductsFemale("Aýakgap")
//        val imageString = toBase64(R.drawable.bershka)
//
//        Log.d("RACIST: ", imageString)
//
//        val f = File(applicationContext.filesDir, "data.txt").printWriter().use { out ->
//            out.println(imageString)
//        }

        initRecyclerViews()

        addListeners()
    }

    private fun addListeners() {
        productAdapter.setOnItemClickListener {
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("i", it.Img_detail)
            startActivity(intent)
        }

        categoryAdapter.setOnItemClickListener {
            getProductsFemale(it.Name.toString())
        }
    }

    private fun initRecyclerViews() {
        productAdapter = ProductListAdapter()
        binding.recyclerviewProducts.apply{
            adapter = productAdapter
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }

        categoryAdapter = CategoryListAdapter()
        binding.recyclerviewMagazines.apply{
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun toBase64(image: Int): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val bitmap = decodeResource(resources, image)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    private fun toBitmap(base64String: String): Bitmap {
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        //        imageView.setImageBitmap(decodedImage)
        return decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    private fun getCategories(){
        db.collection("Categories")
            .get()
            .addOnSuccessListener { d ->
                categoryAdapter.differ.submitList(d.toObjects(Category::class.java))
            }
    }

    private fun getProductsFemale(category: String){
        list.clear()
        db.collection("Products")
            .document("Clothes")
            .collection(category)
            .document("Female")
            .collection("items")
            .get()
            .addOnSuccessListener { d ->
//                productAdapter.differ.submitList(d.toObjects(Product::class.java))
                list = (d.toObjects(Product::class.java))
                Log.d("famale: ${category}", "${list}")
                getProductsMale(category)
            }
    }

    private fun getProductsMale(category: String){
        db.collection("Products")
            .document("Clothes")
            .collection(category)
            .document("Male")
            .collection("items")
            .get()
            .addOnSuccessListener { d ->
//                productAdapter.differ.submitList(d.toObjects(Product::class.java))
                list.addAll(d.toObjects(Product::class.java))
                Log.d("male: ${category}", "${list}")
                productAdapter.differ.submitList(list)
                productAdapter.notifyDataSetChanged()
            }
    }



    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.r_search -> {

            }
            R.id.i_sort -> {

            }
            R.id.i_filter -> {

            }
        }
    }
}