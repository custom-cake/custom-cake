package com.cake.customcake.application.port.`in`

import com.cake.customcake.common.ImageType
import org.springframework.web.multipart.MultipartFile

interface UploadImageUseCase {
    fun upload(imageFile: MultipartFile, imageType: ImageType): String
    fun uploadStoreImage(imageFile: MultipartFile, storeId: Long): String
    fun uploadProductImage(imageFile: MultipartFile, itemId: Long, isThumbnail: Boolean): String
}