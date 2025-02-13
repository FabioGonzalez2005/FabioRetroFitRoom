package iesharia.retrofit2example.network

class ProductRepository {
    val api = ProductService()

    suspend fun getAllProducts(): ProductsResponse {
        return api.getAllProducts()
    }

    suspend fun searchProduct(searchString: String) : ProductsResponse {
        return api.searchProduct(searchString)
    }
}