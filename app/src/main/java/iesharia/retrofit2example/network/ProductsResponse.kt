package iesharia.retrofit2example.network

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products") var products: List<ProductResponse>,
    @SerializedName("total") var total: Int,
    @SerializedName("skip") var skip: Int,
    @SerializedName("limit") var limit: Int
)