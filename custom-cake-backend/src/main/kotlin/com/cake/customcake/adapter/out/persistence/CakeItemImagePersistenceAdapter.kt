package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.CakeItemImageEntity
import com.cake.customcake.adapter.out.persistence.repository.CakeItemImageRepository
import com.cake.customcake.application.port.out.CakeItemImagePort
import org.springframework.stereotype.Component

@Component
class CakeItemImagePersistenceAdapter(
    private val itemImageRepository: CakeItemImageRepository
) : CakeItemImagePort {

    override fun uploadImage(itemId: Long, url: String, isThumbnail: Boolean): Long {
        if (isThumbnail) {
            val currentThumbnail = itemImageRepository.findByCakeItemIdAndIsThumbnailIsTrue(itemId)
            currentThumbnail.isThumbnail = false
        }

        return itemImageRepository.save(CakeItemImageEntity(
            cakeItemId = itemId,
            url = url,
            isThumbnail = isThumbnail
        )).id
    }


}