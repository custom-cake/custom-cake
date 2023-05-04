package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.domain.StoreGallery

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