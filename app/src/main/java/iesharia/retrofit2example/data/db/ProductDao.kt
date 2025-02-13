package iesharia.retrofit2example.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Upsert
    suspend fun upsertProduct(model: Product)

    @Query("SELECT * FROM product")
    fun getFavoriteProductList(): Flow<List<Product>>

    @Query("DELETE FROM product WHERE id = :id")
    suspend fun deleteFavoriteProduct(id: Int): Unit

}