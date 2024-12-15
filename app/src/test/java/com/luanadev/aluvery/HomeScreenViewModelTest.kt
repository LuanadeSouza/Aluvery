package com.luanadev.aluvery

import com.luanadev.aluvery.dao.FakeProductDao
import com.luanadev.aluvery.model.Product
import com.luanadev.aluvery.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class HomeScreenViewModelTest {

    private lateinit var fakeDao: FakeProductDao
    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        fakeDao = FakeProductDao()
        viewModel = HomeScreenViewModel(fakeDao)
    }

    @Test
    fun `deve adicionar produto ao DAO e refletir na ViewModel`() = runTest {
        // Dado
        val novoProduto = Product("Novo Produto", BigDecimal("9.99"))

        // Quando
        fakeDao.save(novoProduto)

        // Então
        val produtos = viewModel.uiState.value.sections["Todos produtos"]
        assertTrue("O produto deve estar na seção 'Todos produtos'", produtos!!.contains(novoProduto))
    }

    @Test
    fun `deve retornar produtos pesquisados pelo nome`() = runTest {
        // Dado
        val produto1 = Product("Chocolate", BigDecimal("4.99"))
        val produto2 = Product("Sorvete", BigDecimal("6.99"))
        fakeDao.save(produto1)
        fakeDao.save(produto2)

        // Quando
        viewModel.uiState.value.onSearchChange("Chocolate")

        // Então
        val produtosBuscados = viewModel.uiState.value.searchedProducts
        assertEquals(1, produtosBuscados.size)
        assertEquals("Chocolate", produtosBuscados[0].name)
    }

    @Test
    fun `deve atualizar produto no DAO`() = runTest {
        // Dado
        val produto = Product("Cerveja", BigDecimal("5.99"))
        fakeDao.save(produto)

        // Quando
        val produtoAtualizado = produto.copy(price = BigDecimal("4.99"))
        fakeDao.update(produtoAtualizado)

        // Então
        val produtos = fakeDao.products().value
        assertTrue(produtos.any { it.price == BigDecimal("4.99") })
    }

    @Test
    fun `deve deletar produto do DAO`() = runTest {
        // Dado
        val produto = Product("Pizza", BigDecimal("19.99"))
        fakeDao.save(produto)

        // Quando
        fakeDao.delete(produto)

        // Então
        val produtos = fakeDao.products().value
        assertTrue(produtos.isEmpty())
    }

    @Test
    fun `deve exibir as seções corretamente`() = runTest {
        // Dado
        val produto1 = Product("Bolo", BigDecimal("12.99"))
        val produto2 = Product("Suco", BigDecimal("3.99"))
        fakeDao.save(produto1)
        fakeDao.save(produto2)

        // Quando
        viewModel.uiState.value // Trigger to load sections

        // Então
        val sections = viewModel.uiState.value.sections
        assertTrue(sections["Todos produtos"]!!.contains(produto1))
        assertTrue(sections["Todos produtos"]!!.contains(produto2))
    }
}