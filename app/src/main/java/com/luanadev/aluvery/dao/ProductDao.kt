package com.luanadev.aluvery.dao

import com.luanadev.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class ProductDao {

    companion object {
        val products = MutableStateFlow<List<Product>>(emptyList())
    }

    // Retorna um fluxo imutável de produtos
    open fun products(): StateFlow<List<Product>> = products.asStateFlow()

    // Adiciona um produto à lista, verificando duplicatas
    open fun save(product: Product) {
        if (!products.value.any { it.name == product.name }) {
            products.value = products.value + product
        }
    }

    // Remove um produto da lista
    open fun delete(product: Product) {
        products.value = products.value - product
    }

    // Atualiza um produto existente
    open fun update(product: Product) {
        products.value = products.value.map {
            if (it.name == product.name) product else it
        }
    }

    // Busca produtos pelo nome ou descrição
    open fun findByNameOrDescription(query: String): List<Product> {
        return products.value.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description?.contains(query, ignoreCase = true) == true
        }
    }
}
