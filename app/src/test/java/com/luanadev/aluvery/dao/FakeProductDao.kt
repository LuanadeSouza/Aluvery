package com.luanadev.aluvery.dao

import com.luanadev.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeProductDao : ProductDao() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())

    // Simula o fluxo de produtos
    override fun products(): StateFlow<List<Product>> = _products.asStateFlow()

    // Simula o método save
    override fun save(product: Product) {
        _products.value = _products.value + product
    }

    // Simula o método delete
    override fun delete(product: Product) {
        _products.value = _products.value - product
    }

    // Simula o método update
    override fun update(product: Product) {
        _products.value = _products.value.map {
            if (it.name == product.name) product else it
        }
    }

    // Simula o método findByNameOrDescription
    override fun findByNameOrDescription(query: String): List<Product> {
        return _products.value.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description?.contains(query, ignoreCase = true) == true
        }
    }
}
