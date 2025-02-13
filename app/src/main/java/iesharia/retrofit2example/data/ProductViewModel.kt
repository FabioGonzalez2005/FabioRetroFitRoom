package iesharia.retrofit2example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iesharia.retrofit2example.network.ProductRepository
import iesharia.retrofit2example.network.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val productsRepository = ProductRepository()

    private val _productList = MutableLiveData<List<ProductResponse>>(emptyList())
    val productList: LiveData<List<ProductResponse>> = _productList

    private val _productSearchList = MutableLiveData<List<ProductResponse>>(emptyList())
    val productSearchList: LiveData<List<ProductResponse>> = _productSearchList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _productList.postValue(productsRepository.getAllProducts().products)
            _isLoading.value = false
        }
    }

    fun searchProducts(searchString: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _productSearchList.postValue(productsRepository.searchProduct(searchString).products)
            _isLoading.value = false
        }
    }
}