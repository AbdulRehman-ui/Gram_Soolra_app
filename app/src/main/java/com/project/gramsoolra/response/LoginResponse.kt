package com.project.gramsoolra.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("parameters")
	val parameters: Parameters? = null
)

data class Parameters(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("user_type")
	val userType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("mobile_number")
	val mobileNumber: String? = null
)
