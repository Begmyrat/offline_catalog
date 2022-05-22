package com.fabrika.tagtabazar.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fabrika.tagtabazar.repository.ProductRepository

class ProductViewModelProviderFactory(
    val app: Application,
    val productRepository: ProductRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(app, productRepository) as T
    }
}