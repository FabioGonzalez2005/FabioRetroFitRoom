package iesharia.retrofit2example

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iesharia.retrofit2example.data.ProductDBViewModel
import iesharia.retrofit2example.data.ProductViewModel

@Composable
fun SearchScreen(
    productViewModel: ProductViewModel,
    databaseViewModel: ProductDBViewModel,
    context: Context
) {
    val isLoading by productViewModel.isLoading.observeAsState(initial = false)
    var searchString by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Buscar productos",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        // Barra de búsqueda
        TextField(
            value = searchString,
            label = { Text("Buscar producto") },
            onValueChange = { searchString = it },
            trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Icono búsqueda") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Botón de búsqueda
        Button(
            onClick = { productViewModel.searchProducts(searchString) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF415A3A))
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            LoadingScreen()
        } else {
            ProductListView(
                "Productos encontrados",
                productViewModel.productSearchList.value ?: emptyList(),
                databaseViewModel,
                context
            )
        }
    }
}
