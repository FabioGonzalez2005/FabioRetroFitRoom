package iesharia.retrofit2example.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductClient {
    @GET("/products")
    suspend fun getAllProducts(): Response<ProductsResponse>

    @GET("/products/search")
    suspend fun searchProduct(@Query("q") queryString: String): Response<ProductsResponse>
}