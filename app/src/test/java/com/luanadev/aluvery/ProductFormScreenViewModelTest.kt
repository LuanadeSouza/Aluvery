package com.luanadev.aluvery

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import com.luanadev.aluvery.dao.ProductDao
import com.luanadev.aluvery.model.Product
import com.luanadev.aluvery.ui.viewmodels.ProductFormScreenViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class ProductFormScreenViewModelTest {

    private lateinit var viewModel: ProductFormScreenViewModel
    private lateinit var mockContext: Context
    private lateinit var mockVibrator: Vibrator

    @Before
    fun setup() {
        viewModel = ProductFormScreenViewModel()
        mockContext = mock(Context::class.java)
        mockVibrator = mock(Vibrator::class.java)
        `when`(mockContext.getSystemService(Context.VIBRATOR_SERVICE)).thenReturn(mockVibrator)
    }

    @Test
    fun `Estado inicial do formulário deve ter valores padrão`() {
        // Dado
        // ViewModel inicializado no setup

        // Quando
        val state = viewModel.uiState.value

        // Então
        assertEquals("", state.name, "O nome inicial deve estar vazio")
        assertEquals("", state.price, "O preço inicial deve estar vazio")
        assertEquals("", state.description, "A descrição inicial deve estar vazia")
    }

    @Test
    fun `Ao salvar produto deve atualizar DAO`() {
        // Dado
        val dao = mock(ProductDao::class.java)
        val produto = Product(name = "Produto Teste", price = BigDecimal("19.99"))
        viewModel.uiState.value.run {
            onNameChange(produto.name)
            onPriceChange(produto.price.toString())
        }

        // Quando
        viewModel.save(mockContext)

        // Então
        verify(dao).save(produto)
    }

    @Test
    fun `Erro ao salvar produto com preço inválido`() {
        // Dado
        viewModel.uiState.value.run {
            onNameChange("Produto Teste")
            onPriceChange("preço inválido")
        }

        // Quando / Então
        assertFailsWith<NumberFormatException> {
            viewModel.save(mockContext)
        }
    }

    @Test
    fun `Vibração é acionada ao salvar produto com sucesso`() {
        // Dado
        viewModel.uiState.value.run {
            onNameChange("Produto Teste")
            onPriceChange("19.99")
        }

        // Quando
        viewModel.save(mockContext)

        // Então
        verify(mockVibrator).vibrate(any(VibrationEffect::class.java))
    }

    @Test
    fun `não deve salvar produto com campos obrigatórios vazios`() {
        // Dado
        val mockContext: Context = mock(Context::class.java)
        viewModel.uiState.value.run {
            onNameChange("")
            onPriceChange("")
            onDescriptionChange("")
        }

        // Quando
        viewModel.save(mockContext)

        // Então
        val produtos = ProductDao().products().value
        assertTrue(
            "Nenhum produto deve ser salvo com campos obrigatórios vazios",
            produtos.isEmpty()
        )
    }

    @Test
    fun `Mensagem de sucesso é exibida ao salvar produto`() {
        // Dado
        val produto = Product(name = "Produto Teste", price = BigDecimal("19.99"))
        viewModel.uiState.value.run {
            onNameChange(produto.name)
            onPriceChange(produto.price.toString())
        }

        // Quando
        viewModel.save(mockContext)

        // Então
        verify(mockContext).let {
            Toast.makeText(
                it,
                "Produto salvo com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
