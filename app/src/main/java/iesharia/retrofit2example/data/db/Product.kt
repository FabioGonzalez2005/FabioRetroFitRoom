package iesharia.retrofit2example.data.db
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import iesharia.retrofit2example.network.ProductResponse

@Entity
data class Product(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var discountPercentage: Double,
    var rating: Double,
    var stock: Int,
    var brand: String,
    var category: String,
    var thumbnail: String
)

fun productResponseToProduct(product: ProductResponse): Product {
    return Product(
        product.id,
        product.title,
        product.description,
        product.price,
        product.discountPercentage,
        product.rating,
        product.stock,
        product.brand,
        product.category,
        product.thumbnail
    )
}

