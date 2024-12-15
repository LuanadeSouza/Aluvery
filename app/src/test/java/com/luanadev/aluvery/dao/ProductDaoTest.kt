package com.luanadev.aluvery.dao

import com.luanadev.aluvery.model.Product
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ProductDaoTest {

    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        // Reinicializa o ProductDao para garantir estado limpo antes de cada teste
        dao = ProductDao().apply {
            // Caso necessário, reinicie explicitamente o estado aqui
            ProductDao.products.value = emptyList()
        }
    }

//    Objetivo: Validar que um produto é salvo corretamente.
//    Dado: Um produto.
//    Quando: O método save() é chamado.
//    Então: O produto deve estar presente na lista de produtos.

    @Test
    fun `deve salvar produto na lista`() = runBlocking {
        // Dado
        val produto = Product(name = "Pizza", price = BigDecimal("19.99"))

        // Quando
        dao.save(produto)

        // Então
        val produtos = dao.products().first()
        assertTrue("A lista de produtos deve conter o produto salvo", produtos.contains(produto))
    }

//    Objetivo: Garantir que, ao iniciar, a lista está vazia.
//    Dado: Nenhum produto salvo.
//    Quando: O método products() é chamado.
//    Então: A lista deve ser vazia.

    @Test
    fun `a lista de produtos deve estar vazia inicialmente`() = runBlocking {
        // Dado
        // Nenhum produto salvo

        // Quando
        val produtos = dao.products().first()

        // Então
        assertTrue("A lista de produtos deve estar vazia", produtos.isEmpty())
    }

//    Objetivo: Certificar que produtos duplicados não sejam salvos.
//    Dado: Um produto já salvo.
//    Quando: O mesmo produto é salvo novamente.
//    Então: O produto não deve ser duplicado na lista.

    @Test
    fun `nao deve adicionar produto duplicado`() = runBlocking {
        // Dado
        val produto = Product(name = "Cerveja", price = BigDecimal("5.99"))
        dao.save(produto)

        // Quando
        dao.save(produto)

        // Então
        val produtos = dao.products().first()
        val count = produtos.count { it.name == produto.name && it.price == produto.price }
        assertEquals("O produto não deve ser adicionado mais de uma vez", 1, count)
    }

    //Objetivo: Verificar se um produto é removido corretamente.
    //Dado: Um produto salvo.
    //Quando: O método delete() é chamado.
    //Então: O produto não deve mais estar na lista.

    @Test
    fun `deve remover produto da lista`() = runBlocking {
        // Dado
        val produto = Product(name = "Sorvete", price = BigDecimal("7.99"))
        dao.save(produto)

        // Quando
        dao.delete(produto)

        // Então
        val produtos = dao.products().first()
        assertFalse("O produto deve ser removido da lista", produtos.contains(produto))
    }

    // Objetivo: Validar que um produto pode ser atualizado.
    //Dado: Um produto salvo.
    //Quando: O método update() é chamado com um produto atualizado.
    //Então: As mudanças devem estar refletidas na lista.

    @Test
    fun `deve atualizar produto na lista`() = runBlocking {
        // Dado
        val produto = Product(name = "Chocolate", price = BigDecimal("4.99"))
        dao.save(produto)

        // Quando
        val produtoAtualizado = produto.copy(price = BigDecimal("3.99"))
        dao.update(produtoAtualizado)

        // Então
        val produtos = dao.products().first()
        val produtoEncontrado = produtos.find { it.name == produto.name }
        assertNotNull("O produto deve existir na lista", produtoEncontrado)
        assertEquals(
            "O preço do produto deve ser atualizado",
            BigDecimal("3.99"),
            produtoEncontrado?.price
        )
    }

    // Objetivo: Garantir que a busca retorna produtos correspondentes ao texto.
    //Dado: Um produto com nome e descrição específicos.
    //Quando: O método findByNameOrDescription() é chamado com uma string que corresponde ao nome ou descrição.
    //Então: O produto correspondente deve ser retornado.

    @Test
    fun `deve buscar produto pelo nome ou descricao`() = runBlocking {
        // Dado
        val produto =
            Product(name = "Água", price = BigDecimal("2.99"), description = "Bebida refrescante")
        dao.save(produto)

        // Quando
        val resultados = dao.findByNameOrDescription("Água")

        // Então
        assertTrue("A busca deve retornar o produto correspondente", resultados.contains(produto))
    }

    //Objetivo: Validar que a busca retorna uma lista vazia se nenhum produto corresponder.
    //Dado: Um produto salvo.
    //Quando: O método findByNameOrDescription() é chamado com uma string que não corresponde a nenhum produto.
    //Então: A lista retornada deve estar vazia.

    @Test
    fun `a busca deve retornar lista vazia se nenhum produto corresponder`() = runBlocking {
        // Dado
        val produto = Product(name = "Hambúrguer", price = BigDecimal("12.99"))
        dao.save(produto)

        // Quando
        val resultados = dao.findByNameOrDescription("Pizza")

        // Então
        assertTrue("A busca deve retornar uma lista vazia", resultados.isEmpty())
    }
}
