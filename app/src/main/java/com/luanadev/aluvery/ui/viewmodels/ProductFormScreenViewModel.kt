package com.luanadev.aluvery.ui.viewmodels

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.luanadev.aluvery.dao.ProductDao
import com.luanadev.aluvery.model.Product
import com.luanadev.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {

    private val dao = ProductDao()
    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )
    val uiState: StateFlow<ProductFormUiState> get() = _uiState

    private val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(url = it)
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(name = it)
                },
                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) it else null
                    }
                    price?.let {
                        _uiState.value = _uiState.value.copy(price = price)
                    }
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(description = it)
                }
            )
        }
    }

    fun save(context: Context) {
        _uiState.value.run {
            if (name.isBlank() || price.isBlank() || description.isBlank()) {
                Toast.makeText(
                    context,
                    "Preencha todos os campos obrigatórios",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }

            val product = Product(
                name = name,
                image = url,
                price = convertedPrice,
                description = description
            )
            dao.save(product)

            // Feedback de vibração
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrateDevice(vibrator)

            // Feedback de mensagem
            Toast.makeText(
                context,
                "Produto salvo com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun vibrateDevice(vibrator: Vibrator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // API 26+: Usa VibrationEffect
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    100, // Duração da vibração em milissegundos
                    VibrationEffect.DEFAULT_AMPLITUDE // Intensidade padrão
                )
            )
        } else {
            // API mais antiga: Usa método legado
            @Suppress("DEPRECATION")
            vibrator.vibrate(100) // Duração da vibração em milissegundos
        }
    }
}