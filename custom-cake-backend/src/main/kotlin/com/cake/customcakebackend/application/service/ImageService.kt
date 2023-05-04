package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.UploadImageUseCase
import com.cake.customcakebackend.application.port.out.UploadImagePort
import com.cake.customcakebackend.common.converter.ImageType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.imageio.ImageIO

@Service
class ImageService(
    private val uploadImagePort: UploadImagePort
) : UploadImageUseCase {
    override fun upload(imageFile: MultipartFile, imageType: ImageType): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val fullFileName = imageType.path + "/" + imageFile.originalFilename
        return uploadImagePort.uploadImage(bufferedImage, fullFileName)
    }
}