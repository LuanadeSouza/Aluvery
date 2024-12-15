package com.luanadev.aluvery.dao

import com.luanadev.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProductDao : ProductDao() {

    private val _fakeProducts = MutableStateFlow<List<Product>>(emptyList())
    val fakeProducts: StateFlow<List<Product>> get() = _fakeProducts

    override fun products(): StateFlow<List<Product>> = fakeProducts

    override fun save(product: Product) {
        _fakeProducts.value = _fakeProducts.value + product
    }

    override fun delete(product: Product) {
        _fakeProducts.value = _fakeProducts.value - product
    }

    fun clear() {
        _fakeProducts.value = emptyList()
    }
}
