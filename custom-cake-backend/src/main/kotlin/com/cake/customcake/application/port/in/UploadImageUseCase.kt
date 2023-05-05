package com.cake.customcake.application.port.`in`

import com.cake.customcake.common.converter.ImageType
import org.springframework.web.multipart.MultipartFile

interface UploadImageUseCase {
    fun upload(imageFile: MultipartFile, imageType: ImageType): String
}