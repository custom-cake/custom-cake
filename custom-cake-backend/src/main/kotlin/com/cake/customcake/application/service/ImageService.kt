package com.cake.customcake.application.service

import com.cake.customcake.application.port.`in`.UploadImageUseCase
import com.cake.customcake.application.port.out.UploadImagePort
import com.cake.customcake.common.converter.ImageType
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