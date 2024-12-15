package com.luanadev.aluvery.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.luanadev.aluvery.ui.theme.AluveryTheme

@Composable
fun SearchTextField(
    searchText: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            onSearchChange(newValue)
        },
        modifier,
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Ícone de busca"
            )
        },
        label = {
            Text(text = "Digite para buscar")
        },
        placeholder = {
            Text("O que você procura?")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                "",
                onSearchChange = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                searchText = "a",
                onSearchChange = {},
            )
        }
    }
}