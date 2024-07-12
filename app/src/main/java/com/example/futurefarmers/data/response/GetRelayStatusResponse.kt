package com.example.futurefarmers.data.response

import com.google.gson.annotations.SerializedName

data class GetRelayStatusResponse(

	@field:SerializedName("nut_a")
	val nutA: Int? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("is_manual_1")
	val isManual1: Int? = null,

	@field:SerializedName("is_manual_2")
	val isManual2: Int? = null,

	@field:SerializedName("is_manual_3")
	val isManual3: Int? = null,

	@field:SerializedName("is_manual_4")
	val isManual4: Int? = null,

	@field:SerializedName("is_manual_5")
	val isManual5: Int? = null,

	@field:SerializedName("fan")
	val fan: Int? = null,

	@field:SerializedName("is_manual_6")
	val isManual6: Int? = null,

	@field:SerializedName("ph_down")
	val phDown: Int? = null,

	@field:SerializedName("light")
	val light: Int? = null,

	@field:SerializedName("nut_b")
	val nutB: Int? = null,

	@field:SerializedName("ph_up")
	val phUp: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
