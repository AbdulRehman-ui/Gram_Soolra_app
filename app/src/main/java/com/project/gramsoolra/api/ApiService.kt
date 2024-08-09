package com.project.gramsoolra.api

import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.response.LoginResponse
import com.project.gramsoolra.response.ProductListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("stock/view-products")
    suspend fun productList(@Body productListRequest: ProductListRequest): Response<ProductListResponse>
}