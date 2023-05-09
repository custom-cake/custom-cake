package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.domain.StoreGallery

data class GalleryListResponse(
    val id: Long,
    val storeId: Long,
    val imageUrlList: List<String>
)

fun StoreGallery.toResponse(): GalleryListResponse = GalleryListResponse(
    id = this.id,
    storeId = this.storeId,
    imageUrlList = this.imageUrlList
)