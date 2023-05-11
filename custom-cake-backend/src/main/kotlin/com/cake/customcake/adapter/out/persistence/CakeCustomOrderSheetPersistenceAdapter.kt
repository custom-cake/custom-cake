package com.cake.customcake.adapter.out.persistence

import com.cake.customcake.adapter.out.persistence.mapper.CakeCustomOrderSheetMapper
import com.cake.customcake.adapter.out.persistence.repository.CakeCustomOrderSheetRepository
import com.cake.customcake.application.port.out.CakeCustomOrderSheetPort
import com.cake.customcake.domain.CakeCustomOrderSheet
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class CakeCustomOrderSheetPersistenceAdapter(
    private val cakeCustomOrderSheetRepository: CakeCustomOrderSheetRepository,
    private val cakeCustomOrderSheetMapper: CakeCustomOrderSheetMapper
): CakeCustomOrderSheetPort {
    override fun load(sheetId: Long): CakeCustomOrderSheet {
        val orderSheetEntity = cakeCustomOrderSheetRepository.findByIdOrNull(sheetId)
            ?: throw EntityNotFoundException("CakeCustomOrderSheet id=$sheetId not found.")

        return cakeCustomOrderSheetMapper.toDomain(orderSheetEntity)
    }

    override fun save(cakeCustomOrderSheet: CakeCustomOrderSheet) {
        cakeCustomOrderSheetRepository.save(cakeCustomOrderSheetMapper.toEntity(cakeCustomOrderSheet))
    }
}