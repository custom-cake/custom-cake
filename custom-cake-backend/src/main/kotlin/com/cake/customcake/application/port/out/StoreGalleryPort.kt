package com.cake.customcake.application.port.out

import com.cake.customcake.domain.StoreGallery

interface StoreGalleryPort {
    fun load(storeId: Long): StoreGallery
    fun save(storeId: Long)
}