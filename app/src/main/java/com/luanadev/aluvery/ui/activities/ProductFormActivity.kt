package com.luanadev.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.luanadev.aluvery.ui.screens.ProductFormScreen
import com.luanadev.aluvery.ui.theme.AluveryTheme
import com.luanadev.aluvery.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val viewModel: ProductFormScreenViewModel by viewModels()
                    ProductFormScreen(
                        viewModel = viewModel,
                        onSaveClick = {
                            finish()
                        })
                }
            }
        }
    }

}