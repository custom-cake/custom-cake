package com.cake.customcake.adapter.`in`.web

import com.cake.customcake.application.port.`in`.UploadImageUseCase
import com.cake.customcake.common.ImageType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/image")
class ImageController(
    private val uploadImageUseCase: UploadImageUseCase
) {

    @PostMapping("/upload")
    fun upload(@RequestParam("image") image: MultipartFile, @RequestParam("type") imageType: ImageType): String {
        return uploadImageUseCase.upload(image, imageType)
    }

    @PostMapping("/store/{storeId}")
    fun uploadStoreImage(@RequestParam("image") image: MultipartFile, @PathVariable("storeId") storeId: Long): String {
        return uploadImageUseCase.uploadStoreImage(image, storeId)
    }

    @PostMapping("/product/{itemId}")
    fun uploadProductImage(@RequestParam("image") image: MultipartFile, @RequestParam("isThumbnail") isThumbnail: Boolean, @PathVariable("itemId") itemId: Long): String {
        return uploadImageUseCase.uploadProductImage(image, itemId, isThumbnail)
    }

    @PostMapping("/custom/{storeId}/{userId}")
    fun uploadCustomCakeImage(@RequestParam("image") image: MultipartFile, @PathVariable("storeId") storeId: Long, @PathVariable("userId") userId: Long): String {
        return uploadImageUseCase.uploadCustomCakeImage(image, storeId, userId)
    }

    @PostMapping("/custom/additional/{customOrderSheetId}")
    fun uploadAdditionalCustomCakeImage(@RequestParam("image") image: MultipartFile, @PathVariable("customOrderSheetId") customOrderSheetId: Long): String {
        return uploadAdditionalCustomCakeImage(image, customOrderSheetId)
    }

    @PostMapping("/gallery/{storeId}")
    fun uploadGalleryImage(@RequestParam("image") image: MultipartFile, @PathVariable("storeId") storeId: Long): String {
        return uploadImageUseCase.uploadGalleryImage(image, storeId)
    }
}