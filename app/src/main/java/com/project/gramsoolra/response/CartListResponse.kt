package com.project.gramsoolra.response

import com.google.gson.annotations.SerializedName

data class CartListResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("parameters")
	val parameters: Parameters3? = null,

	@field:SerializedName("extra_param")
	val extraParam: String? = null
)

data class BankAccountDetails(

	@field:SerializedName("upi_list")
	val upiList: List<Any?>? = null,

	@field:SerializedName("bank_list")
	val bankList: List<Any?>? = null
)

data class StockImagesItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stock_id")
	val stockId: Int? = null
)

data class DataItem2(

	@field:SerializedName("added_at")
	val addedAt: String? = null,

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Product(

	@field:SerializedName("min_order_qty")
	val minOrderQty: Int? = null,

	@field:SerializedName("breadth")
	val breadth: Int? = null,

	@field:SerializedName("narration_name")
	val narrationName: String? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("lot_size_id")
	val lotSizeId: Int? = null,

	@field:SerializedName("vle_status")
	val vleStatus: Int? = null,

	@field:SerializedName("stockImages")
	val stockImages: List<StockImagesItem?>? = null,

	@field:SerializedName("it_is_certified")
	val itIsCertified: Int? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("certificate_url")
	val certificateUrl: String? = null,

	@field:SerializedName("added_by")
	val addedBy: Int? = null,

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

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("expiry_date")
	val expiryDate: String? = null,

	@field:SerializedName("length")
	val length: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("packing_info_id")
	val packingInfoId: Int? = null,

	@field:SerializedName("transport")
	val transport: Any? = null,

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

	@field:SerializedName("transport_type")
	val transportType: String? = null,

	@field:SerializedName("qty_to_market")
	val qtyToMarket: Int? = null,

	@field:SerializedName("cost_per_unit_above_moq_ship")
	val costPerUnitAboveMoqShip: Any? = null,

	@field:SerializedName("certificate_no")
	val certificateNo: String? = null,

	@field:SerializedName("item_verify_status")
	val itemVerifyStatus: Int? = null
)

data class Parameters3(

	@field:SerializedName("bank_account_details")
	val bankAccountDetails: BankAccountDetails? = null,

	@field:SerializedName("cost_moq_locally_sum")
	val costMoqLocallySum: Int? = null,

	@field:SerializedName("cost_moq_country_ship_sum")
	val costMoqCountryShipSum: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem2?>? = null,

	@field:SerializedName("shipping")
	val shipping: Int? = null,

	@field:SerializedName("cost_per_unit_above_moq_ship_sum")
	val costPerUnitAboveMoqShipSum: Int? = null,

	@field:SerializedName("sub_total")
	val subTotal: Int? = null,

	@field:SerializedName("total_price_payable")
	val totalPricePayable: Int? = null
)
