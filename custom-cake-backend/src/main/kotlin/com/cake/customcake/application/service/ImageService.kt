package com.cake.customcake.application.service

import com.cake.customcake.application.port.`in`.UploadImageUseCase
import com.cake.customcake.application.port.out.CakeItemImagePort
import com.cake.customcake.application.port.out.CakeItemPort
import com.cake.customcake.application.port.out.StorePort
import com.cake.customcake.application.port.out.UploadImagePort
import com.cake.customcake.common.ImageType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import javax.imageio.ImageIO

@Service
class ImageService(
    private val uploadImagePort: UploadImagePort,
    private val storePort: StorePort,
    private val itemPort: CakeItemPort,
    private val itemImagePort: CakeItemImagePort
) : UploadImageUseCase {

    override fun upload(imageFile: MultipartFile, imageType: ImageType): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val fullFileName = imageType.path + "/" + imageFile.originalFilename
        return uploadImagePort.uploadImage(bufferedImage, fullFileName)
    }

    @Transactional
    override fun uploadStoreImage(imageFile: MultipartFile, storeId: Long): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val fullFileName = ImageType.STORE.path + "/" + imageFile.originalFilename

        val store = storePort.loadByStoreId(storeId)
        val url = uploadImagePort.uploadImage(bufferedImage, fullFileName)
        storePort.updateStoreImage(store, url)

        return  url
    }

    @Transactional
    override fun uploadProductImage(imageFile: MultipartFile, itemId: Long, isThumbnail: Boolean): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val fullFileName = ImageType.PRODUCT.path + "/" + imageFile.originalFilename

        val item = itemPort.loadInfo(itemId)
        val url = uploadImagePort.uploadImage(bufferedImage, fullFileName)

        if (isThumbnail)
            itemPort.updateThumbnailImage(item, url)

        itemImagePort.uploadImage(itemId, url, isThumbnail)

        return url
    }
}