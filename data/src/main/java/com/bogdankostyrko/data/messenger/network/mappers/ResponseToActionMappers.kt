package com.bogdankostyrko.data.messenger.network.mappers

import com.bogdankostyrko.data.messenger.network.dto.ResponseToActionDTO

fun ResponseToActionDTO.toResult(): Result<String> = when (this.result.lowercase()) {

    "success" -> Result.success(this.msg ?: "")

    "error" -> Result.failure(Exception("API returned error. ${this.msg ?: ""}"))

    else -> Result.failure(Exception("Unknown result: ${this.result}"))
}