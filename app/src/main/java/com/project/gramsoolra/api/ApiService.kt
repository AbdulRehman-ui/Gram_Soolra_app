package com.project.gramsoolra.api

import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}