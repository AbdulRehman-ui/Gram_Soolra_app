package com.project.gramsoolra.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("mobile_number")
	var mobileNumber: String? = null,

	@field:SerializedName("auth_token")
	var authToken: String? = null
)
