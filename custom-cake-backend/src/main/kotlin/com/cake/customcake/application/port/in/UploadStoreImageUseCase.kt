package com.cake.customcake.application.port.`in`

import org.springframework.web.multipart.MultipartFile

interface UploadStoreImageUseCase {
    fun uploadStoreImage(imageFile: MultipartFile, id: Long): String
}