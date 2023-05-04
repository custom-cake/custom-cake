package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.common.converter.ImageType
import org.springframework.web.multipart.MultipartFile

interface UploadImageUseCase {
    fun upload(imageFile: MultipartFile, imageType: ImageType): String
}