package com.project.gramsoolra.response

import com.google.gson.annotations.SerializedName

data class ProductListResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("parameters")
	val parameters: Parameters2? = null,

	@field:SerializedName("extra_param")
	val extraParam: String? = null
)

data class Parameters2(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("page")
	val page: Page? = null
)

data class Page(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null,

	@field:SerializedName("total_record")
	val totalRecord: Int? = null
)

data class DataItem(

	@field:SerializedName("min_order_qty")
	val minOrderQty: Int? = null,

	@field:SerializedName("breadth")
	val breadth: Int? = null,

	@field:SerializedName("narration_name")
	val narrationName: String? = null,

	@field:SerializedName("stockPhotos")
	val stockPhotos: List<StockPhotosItem?>? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("lot_size_id")
	val lotSizeId: Int? = null,

	@field:SerializedName("vle_status")
	val vleStatus: Int? = null,

	@field:SerializedName("it_is_certified")
	val itIsCertified: Int? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("certificate_url")
	val certificateUrl: String? = null,

	@field:SerializedName("added_by")
	val addedBy: Int? = null,

	@field:SerializedName("stockAddress")
	val stockAddress: List<StockAddressItem?>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("address_book_id")
	val addressBookId: Int? = null,

	@field:SerializedName("vle_id")
	val vleId: Any? = null,

	@field:SerializedName("is_sell_on_market_place")
	val isSellOnMarketPlace: Int? = null,

	@field:SerializedName("cartification_agency_id")
	val cartificationAgencyId: Int? = null,

	@field:SerializedName("lot_size")
	val lotSize: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("min_order_qty_id")
	val minOrderQtyId: Any? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("qty_to_market_id")
	val qtyToMarketId: Int? = null,

	@field:SerializedName("certificate_verified")
	val certificateVerified: Int? = null,

	@field:SerializedName("shipping_address_id")
	val shippingAddressId: Any? = null,

	@field:SerializedName("is_active")
	val isActive: Int? = null,

	@field:SerializedName("expiry_date")
	val expiryDate: String? = null,

	@field:SerializedName("length")
	val length: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("packing_info_id")
	val packingInfoId: Int? = null,

	@field:SerializedName("warehouse_stock_qty")
	val warehouseStockQty: Int? = null,

	@field:SerializedName("added_at")
	val addedAt: String? = null,

	@field:SerializedName("market_place_id")
	val marketPlaceId: Int? = null,

	@field:SerializedName("cost_moq_country_ship")
	val costMoqCountryShip: Any? = null,

	@field:SerializedName("cost_per_unit")
	val costPerUnit: Int? = null,

	@field:SerializedName("cost_moq_locally")
	val costMoqLocally: Any? = null,

	@field:SerializedName("qty_to_market")
	val qtyToMarket: Int? = null,

	@field:SerializedName("cost_per_unit_above_moq_ship")
	val costPerUnitAboveMoqShip: Any? = null,

	@field:SerializedName("certificate_no")
	val certificateNo: String? = null,

	@field:SerializedName("item_verify_status")
	val itemVerifyStatus: Int? = null
)

data class StockPhotosItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stock_id")
	val stockId: Int? = null
)

data class StockAddressItem(

	@field:SerializedName("pincode")
	val pincode: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address_type")
	val addressType: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("added_at")
	val addedAt: String? = null,

	@field:SerializedName("email_address")
	val emailAddress: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("address_title")
	val addressTitle: Any? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("district")
	val district: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null
)
