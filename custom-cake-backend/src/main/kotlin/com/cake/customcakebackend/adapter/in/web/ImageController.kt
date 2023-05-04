package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.port.`in`.UploadImageUseCase
import com.cake.customcakebackend.common.converter.ImageType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val uploadImageUseCase: UploadImageUseCase
) {

    @PostMapping("/upload")
    fun upload(@RequestParam("image") image: MultipartFile, @RequestParam("type") imageType: ImageType): String {
        return uploadImageUseCase.upload(image, imageType)
    }
}