package com.luanadev.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luanadev.aluvery.R
import com.luanadev.aluvery.extensions.toBrazilianCurrency
import com.luanadev.aluvery.model.Product
import com.luanadev.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isExpanded: Boolean = false
) {
    var expanded by rememberSaveable { mutableStateOf(isExpanded) }

    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expanded = !expanded
            },
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = "Imagem do produto: ${product.name}",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            if (expanded) {
                product.description?.let {
                    Text(
                        text = it,
                        Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99")
                ),
            )
        }
    }
}

@Preview
@Composable
fun CardProductItemWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    "teste",
                    BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first(),
                ),
                isExpanded = true
            )
        }
    }
}