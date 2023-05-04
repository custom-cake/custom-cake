package com.cake.customcakebackend.common.converter

enum class ImageType(val path: String) {
    STORE("store-image"),
    PRODUCT("cake-item-image"),
    GALLERY("gallery"),
    CUSTOM_CAKE("cake-custom-image"),
}