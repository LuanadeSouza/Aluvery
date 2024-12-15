package com.luanadev.aluvery.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luanadev.aluvery.dao.FakeProductDao
import com.luanadev.aluvery.model.Product
import com.luanadev.aluvery.sampledata.sampleCandies
import com.luanadev.aluvery.sampledata.sampleDrinks
import com.luanadev.aluvery.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeDao: FakeProductDao
    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        // Configura o MainDispatcher para usar o TestDispatcher
        Dispatchers.setMain(testDispatcher)

        fakeDao = FakeProductDao()
        viewModel = HomeScreenViewModel(fakeDao)
    }

    @After
    fun tearDown() {
        // Reseta o MainDispatcher para evitar interferência em outros testes
        Dispatchers.resetMain()
    }

    @Test
    fun `deve inicializar com secoes de amostra`() = runTest {
        // Objetivo: Verificar se as seções de amostra são exibidas corretamente.
        // Dado: As seções de amostra de doces e bebidas.

        // Quando: Inicializamos a ViewModel
        val sections = viewModel.uiState.value.sections

        // Então: As seções de "Doces" e "Bebidas" devem conter os dados mockados.
        assertEquals(sampleCandies, sections["Doces"], "A seção 'Doces' deve conter os produtos de amostra")
        assertEquals(sampleDrinks, sections["Bebidas"], "A seção 'Bebidas' deve conter os produtos de amostra")
    }

    @Test
    fun `deve adicionar produto ao DAO e refletir na ViewModel`() = runTest {
        // Objetivo: Verificar se um novo produto é adicionado ao DAO e refletido na ViewModel.
        // Dado: Um produto vazio no DAO.
        val novoProduto = Product("Novo Produto", BigDecimal("9.99"))

        // Quando: Salvamos um novo produto no DAO.
        fakeDao.save(novoProduto)
        advanceUntilIdle() // Avança o tempo da coroutine para garantir coleta do fluxo.

        // Então: O produto deve aparecer na seção "Todos produtos".
        val produtos = viewModel.uiState.value.sections["Todos produtos"]
        assertEquals(1, produtos?.size, "A seção 'Todos produtos' deve conter um produto")
        assertEquals(novoProduto, produtos?.first(), "O produto adicionado deve ser 'Novo Produto'")
    }

    @Test
    fun `deve filtrar produtos pela busca`() = runTest {
        // Objetivo: Verificar se a busca retorna os produtos corretos.
        // Dado: Produtos de amostra no DAO.
        val produtoBusca = Product("Produto Busca", BigDecimal("15.99"))
        fakeDao.save(produtoBusca)
        advanceUntilIdle()

        // Quando: Alteramos o texto de busca na ViewModel.
        viewModel.uiState.value.onSearchChange("Busca")
        advanceUntilIdle()

        // Então: Apenas produtos correspondentes ao texto de busca devem ser retornados.
        val produtosBuscados = viewModel.uiState.value.searchedProducts
        assertEquals(1, produtosBuscados.size, "A busca deve retornar apenas um produto")
        assertEquals(produtoBusca, produtosBuscados.first(), "O produto retornado deve ser 'Produto Busca'")
    }

    @Test
    fun `deve retornar lista vazia ao buscar por produto inexistente`() = runTest {
        // Objetivo: Verificar se a busca retorna lista vazia quando não há correspondências.
        // Dado: Nenhum produto correspondente à busca.

        // Quando: Alteramos o texto de busca na ViewModel.
        viewModel.uiState.value.onSearchChange("Inexistente")
        advanceUntilIdle()

        // Então: A lista de produtos buscados deve estar vazia.
        val produtosBuscados = viewModel.uiState.value.searchedProducts
        assertTrue(produtosBuscados.isEmpty(), "A lista de busca deve estar vazia")
    }
}
