package com.cake.customcake.application.service

import com.cake.customcake.application.port.`in`.UploadImageUseCase
import com.cake.customcake.application.port.out.*
import com.cake.customcake.common.ImageType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import javax.imageio.ImageIO

@Service
class ImageService(
    private val uploadImagePort: UploadImagePort,
    private val storePort: StorePort,
    private val itemPort: CakeItemPort,
    private val itemImagePort: CakeItemImagePort,
    private val galleryPort: StoreGalleryPort
) : UploadImageUseCase {

    override fun upload(imageFile: MultipartFile, imageType: ImageType): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val fullFileName = imageType.path + "/" + imageFile.originalFilename
        return uploadImagePort.uploadImage(bufferedImage, fullFileName)
    }

    @Transactional
    override fun uploadStoreImage(imageFile: MultipartFile, storeId: Long): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)

        val originalFilename = imageFile.originalFilename
        val extension = originalFilename?.substringAfterLast('.', "") ?: ""
        val fullFileName = ImageType.STORE.path + "/store_${storeId}/" + createFileName(extension)
        val store = storePort.loadByStoreId(storeId)

        val url = uploadImagePort.uploadImage(bufferedImage, fullFileName)

        storePort.updateStoreImage(store, url)
        return  url
    }

    @Transactional
    override fun uploadProductImage(imageFile: MultipartFile, itemId: Long, isThumbnail: Boolean): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)
        val item = itemPort.loadInfo(itemId)

        val originalFilename = imageFile.originalFilename
        val extension = originalFilename?.substringAfterLast('.', "") ?: ""
        val fullFileName = ImageType.PRODUCT.path + "/store_${item.storeId}/" + createFileName(extension)

        val url = uploadImagePort.uploadImage(bufferedImage, fullFileName)

        if (isThumbnail)
            itemPort.updateThumbnailImage(item, url)

        itemImagePort.uploadImage(itemId, url, isThumbnail)
        return url
    }

    @Transactional
    override fun uploadCustomCakeImage(imageFile: MultipartFile, storeId: Long, userId: Long): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)

        val originalFilename = imageFile.originalFilename
        val extension = originalFilename?.substringAfterLast('.', "") ?: ""
        val fullFileName = ImageType.CUSTOM_CAKE.path + "/store_${storeId}/user_${userId}/" + createFileName(extension)

        // TODO : check user, store ids

        return uploadImagePort.uploadImage(bufferedImage, fullFileName)
    }

    @Transactional
    override fun uploadGalleryImage(imageFile: MultipartFile, storeId: Long): String {
        val bufferedImage = ImageIO.read(imageFile.inputStream)

        val originalFilename = imageFile.originalFilename
        val extension = originalFilename?.substringAfterLast('.', "") ?: ""
        val fullFileName = ImageType.GALLERY.path + "store_${storeId}/" + createFileName(extension)
        val gallery = galleryPort.load(storeId)

        val url = uploadImagePort.uploadImage(bufferedImage, fullFileName)
        galleryPort.addImage(gallery, url)

        return url
    }

    private fun createFileName(extension: String): String = UUID.randomUUID().toString() + "." + extension
}