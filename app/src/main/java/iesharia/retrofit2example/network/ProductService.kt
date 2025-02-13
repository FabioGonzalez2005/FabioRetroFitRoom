package iesharia.retrofit2example.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllProducts(): ProductsResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductClient::class.java).getAllProducts()
            return@withContext response.body()!!
        }
    }

    suspend fun searchProduct(searchString: String): ProductsResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ProductClient::class.java).searchProduct(searchString)
            return@withContext response.body()!!
        }
    }

}