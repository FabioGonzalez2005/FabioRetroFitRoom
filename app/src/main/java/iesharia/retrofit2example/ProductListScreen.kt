package iesharia.retrofit2example

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import iesharia.retrofit2example.data.ProductDBViewModel
import iesharia.retrofit2example.data.ProductViewModel
import iesharia.retrofit2example.data.db.productResponseToProduct
import iesharia.retrofit2example.network.ProductResponse

@Composable
fun ProductListScreen(
    productViewModel: ProductViewModel,
    databaseViewModel: ProductDBViewModel,
    context: Context
) {
    val isLoading: Boolean by productViewModel.isLoading.observeAsState(initial = true)
    if (isLoading) {
        productViewModel.getAllProducts()
        LoadingScreen()
    } else {
        ProductListView("Listado de productos", productViewModel.productList.value!!, databaseViewModel, context)
    }
}

@Composable
fun ProductListView(
    title: String,
    productList: List<ProductResponse>,
    databaseViewModel: ProductDBViewModel,
    context: Context
) {
    ProductList(title, productList, Icons.Filled.Add) { product: ProductResponse ->
        Toast.makeText(context, "Producto añadido", Toast.LENGTH_SHORT).show()
        databaseViewModel.insertOrUpdateFavoriteProduct(productResponseToProduct(product))
    }
}