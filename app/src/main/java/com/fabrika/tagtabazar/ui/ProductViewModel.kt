package com.fabrika.tagtabazar.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fabrika.tagtabazar.repository.ProductRepository

class ProductViewModel(
    app: Application,
    val productRepository: ProductRepository
): AndroidViewModel(app) {



}