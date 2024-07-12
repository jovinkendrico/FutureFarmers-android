package com.example.futurefarmers.data.response

import com.google.gson.annotations.SerializedName

data class RelayHistoryResponse(

	@field:SerializedName("page_number")
	val pageNumber: Int,

	@field:SerializedName("total_record_count")
	val totalRecordCount: Int,

	@field:SerializedName("records")
	val records: List<RecordsItem>,

	@field:SerializedName("page_size")
	val pageSize: Int
)

data class RecordsItem(

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("status")
	val status: String
)
