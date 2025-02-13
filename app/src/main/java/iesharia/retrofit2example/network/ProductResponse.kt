package iesharia.retrofit2example.network

import com.google.gson.annotations.SerializedName
import iesharia.retrofit2example.data.db.Product

data class ProductResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: Double,
    @SerializedName("discountPercentage") var discountPercentage: Double,
    @SerializedName("rating") var rating: Double,
    @SerializedName("stock") var stock: Int,
    @SerializedName("brand") var brand: String,
    @SerializedName("category") var category: String,
    @SerializedName("thumbnail") var thumbnail: String,
    @SerializedName("images") var images: List<String>
)

fun productToProductResponse(product: Product): ProductResponse {
    return ProductResponse(
        product.id,
        product.title,
        product.description,
        product.price,
        product.discountPercentage,
        product.rating,
        product.stock,
        product.brand,
        product.category,
        product.thumbnail,
        emptyList()
    )
}