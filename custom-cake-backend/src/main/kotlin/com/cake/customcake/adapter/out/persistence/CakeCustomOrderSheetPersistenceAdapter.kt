package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.entity.QCakeCustomOrderSheetEntity.cakeCustomOrderSheetEntity as CUSTOM_ORDER_SHEET
import com.cake.customcake.adapter.out.persistence.mapper.CakeCustomOrderSheetMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeCustomOrderSheetRepository
import com.cake.customcake.application.port.out.CakeCustomOrderSheetPort
import com.cake.customcake.domain.CakeCustomOrderSheet
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class CakeCustomOrderSheetPersistenceAdapter(
    private val cakeCustomOrderSheetMapper: CakeCustomOrderSheetMapper,
    private val cakeCustomOrderSheetRepository: CakeCustomOrderSheetRepository,
    private val jpaQueryFactory: JPAQueryFactory
): CakeCustomOrderSheetPort {
    override fun load(sheetId: Long): CakeCustomOrderSheet {
        val orderSheetEntity = cakeCustomOrderSheetRepository.findByIdOrNull(sheetId)
            ?: throw EntityNotFoundException("CakeCustomOrderSheet id=$sheetId not found.")

        return cakeCustomOrderSheetMapper.toDomain(orderSheetEntity)
    }

    override fun save(cakeCustomOrderSheet: CakeCustomOrderSheet) {
        cakeCustomOrderSheetRepository.save(cakeCustomOrderSheetMapper.toEntity(cakeCustomOrderSheet))
    }

    override fun hasSheet(storeId: Long, userId: Long): Pair<Boolean, Long?> {
        val orderSheetEntity = jpaQueryFactory
            .select(CUSTOM_ORDER_SHEET)
            .from(CUSTOM_ORDER_SHEET)
            .where(
                CUSTOM_ORDER_SHEET.storeId.eq(storeId),
                CUSTOM_ORDER_SHEET.userId.eq(userId)
            )
            .fetchOne()

        return orderSheetEntity
             ?. let { (true to it.id) }
             ?: let { (false to null) }
    }

    override fun updateImage(sheet: CakeCustomOrderSheet, url: String): Long {
        val entity = cakeCustomOrderSheetMapper.toEntity(sheet)
        entity.cakeCustomImageUrl = url
        return entity.id
    }

    override fun addAdditionalImage(sheet: CakeCustomOrderSheet, url: String): Long {
        val entity = cakeCustomOrderSheetMapper.toEntity(sheet)
        entity.additionalImageList = entity.additionalImageList + url
        return entity.id
    }
}