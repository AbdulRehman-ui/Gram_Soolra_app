package com.project.gramsoolra.repositories

import androidx.lifecycle.LiveData
import com.project.gramsoolra.api.ApiService
import com.project.gramsoolra.common.Resource
import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.response.CartListResponse
import com.project.gramsoolra.response.LoginResponse
import com.project.gramsoolra.response.ProductListResponse

class MainRepository (
    private val apiService: ApiService
) {

    suspend fun login(loginRequest: LoginRequest) : Resource<LoginResponse> {
        return try {
            val response = apiService.login(loginRequest)

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Network Error: ${e.localizedMessage}", null)
        }
    }

    suspend fun productList(productListRequest: ProductListRequest) : Resource<ProductListResponse> {
        return try {
            val response = apiService.productList(productListRequest)

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Network Error: ${e.localizedMessage}", null)
        }
    }

    suspend fun cartList(productListRequest: ProductListRequest) : Resource<CartListResponse> {
        return try {
            val response = apiService.cartList(productListRequest)

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Network Error: ${e.localizedMessage}", null)
        }
    }

}