package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.StoreGallery

interface StoreGalleryPort {
    fun load(storeId: Long): StoreGallery
    fun save(storeId: Long)
}