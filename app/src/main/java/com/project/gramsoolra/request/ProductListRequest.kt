package com.project.gramsoolra.request

import com.google.gson.annotations.SerializedName

data class ProductListRequest(

	@field:SerializedName("user_id")
	var userId: String? = null,

	@field:SerializedName("search_term")
	var searchTerm: String? = null,

	@field:SerializedName("page")
	var page: String? = null,

	@field:SerializedName("auth_token")
	var authToken: String? = null
)
