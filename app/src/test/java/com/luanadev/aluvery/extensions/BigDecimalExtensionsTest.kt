package com.luanadev.aluvery.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionsTest {

//    Objetivo: Verificar se um valor positivo (1234.56) é convertido corretamente para o formato monetário brasileiro.
//    Etapas:
//    Dado: Um valor em BigDecimal (ex.: 1234.56).
//    Quando: A função toBrazilianCurrency() é chamada e o espaço NBSP é substituído por um espaço regular.
//    Então: O resultado deve ser igual a "R$ 1.234,56", confirmando que o número foi formatado corretamente.


    @Test
    fun `deve formatar valor positivo para moeda brasileira`() {
        // Dado
        val valor = BigDecimal("1234.56")

        // Quando
        val resultado = valor.toBrazilianCurrency().replace('\u00A0', ' ')

        // Então
        assertEquals("R$ 1.234,56", resultado)
    }

//    Objetivo: Garantir que 0.00 seja formatado corretamente como "R$ 0,00".
//    Etapas:
//    Dado: Um valor em BigDecimal igual a zero (0.00).
//    Quando: A função é chamada e o espaço NBSP é tratado.
//    Então: O resultado deve ser "R$ 0,00". Isso valida que zero é exibido no formato correto, com centavos e símbolo.

    @Test
    fun `deve formatar valor zero para moeda brasileira`() {
        // Dado
        val valor = BigDecimal("0.00")

        // Quando
        val resultado = valor.toBrazilianCurrency().replace('\u00A0', ' ')

        // Então
        assertEquals("R$ 0,00", resultado)
    }

//    Objetivo: Validar que números negativos são formatados com o sinal de menos antes do valor monetário.
//    Etapas:
//    Dado: Um valor negativo em BigDecimal (ex.: -9876.54).
//    Quando: A função é chamada e o espaço NBSP é tratado.
//    Então: O resultado deve ser "R$ -9.876,54", confirmando que o valor negativo é exibido corretamente com o símbolo do Real.

    @Test
    fun `deve formatar valor negativo para moeda brasileira`() {
        // Dado
        val valor = BigDecimal("-9876.54")

        // Quando
        val resultado = valor.toBrazilianCurrency().replace('\u00A0', ' ')

        // Então
        assertEquals("R$ -9.876,54", resultado)
    }

//    Objetivo: Garantir que números com mais de duas casas decimais sejam arredondados corretamente.
//    Etapas:
//    Dado: Um valor em BigDecimal com muitas casas decimais (ex.: 1234.56789).
//    Quando: A função é chamada e o espaço NBSP é tratado.
//    Então: O resultado deve ser "R$ 1.234,57". Isso valida que a formatação arredonda o valor corretamente para duas casas decimais.

    @Test
    fun `deve formatar valor com muitas casas decimais arredondando para moeda brasileira`() {
        // Dado
        val valor = BigDecimal("1234.56789")

        // Quando
        val resultado = valor.toBrazilianCurrency().replace('\u00A0', ' ')

        // Então
        assertEquals("R$ 1.234,57", resultado)
    }
}
