package com.cake.customcake.application.port.out

interface CakeItemImagePort {

    fun uploadImage(itemId: Long, url: String, isThumbnail: Boolean): Long
}