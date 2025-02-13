package iesharia.retrofit2example

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iesharia.retrofit2example.data.ProductDBViewModel
import iesharia.retrofit2example.data.db.productResponseToProduct
import iesharia.retrofit2example.network.ProductResponse
import iesharia.retrofit2example.network.productToProductResponse

@Composable
fun FavoriteListScreen(databaseViewModel: ProductDBViewModel, context: Context) {
    val favoriteListState = databaseViewModel.getFavoriteProductList().collectAsState(initial = emptyList())
    val processedList = remember { derivedStateOf { favoriteListState.value.map { productToProductResponse(it) } } }
    val isLoading by databaseViewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Productos favoritos",
            fontSize = 38.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        if (isLoading) {
            LoadingScreen()
        } else {
            FavoriteListView(processedList.value, databaseViewModel, context)
        }
    }
}

@Composable
fun FavoriteListView(
    favoriteList: List<ProductResponse>,
    databaseViewModel: ProductDBViewModel,
    context: Context
) {
    ProductList(title = "", productList = favoriteList, icon = androidx.compose.material.icons.Icons.Filled.Delete) { product ->
        databaseViewModel.deleteFavoriteProduct(productResponseToProduct(product).id)
        Toast.makeText(context, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show()
    }
}