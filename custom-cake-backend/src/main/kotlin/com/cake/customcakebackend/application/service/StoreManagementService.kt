package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.application.port.out.DayoffPort
import com.cake.customcakebackend.application.port.out.StoreGalleryPort
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.DayoffType
import com.cake.customcakebackend.domain.Dayoff
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StoreManagementService(
    private val storePort: StorePort,
    private val dayoffPort: DayoffPort,
    private val storeGalleryPort: StoreGalleryPort
) : StoreManagementUseCase {
    override fun storeInfo(operatorId: Long): List<Store> =
        storePort.loadByOperatorId(operatorId)

    override fun hasStore(operatorId: Long): Boolean =
        storePort.exist(operatorId)

    override fun validateStore(storeId: Long, operatorId: Long): Boolean =
        storePort.validateStore(storeId, operatorId)

    override fun registerStore(operatorId: Long, request: StoreRegisterRequest) {
        // make openTime
        val openTime: Map<DayOfWeekUnit, String> = DayOfWeekUnit.values().mapIndexed { idx, dowUnit ->
            dowUnit to request.openTime[idx]
        }.toMap()

        val storeDomain = Store(
            operatorId = operatorId,
            businessRegistrationNo = request.businessRegistrationNo.replace("-", ""),
            representativeName = request.representativeName,
            zipCode = request.zipCode,
            baseAddress = request.baseAddress,
            detailAddress = request.detailAddress,
            region = request.region,
            phone = request.phone,
            name = request.name,
            description = request.description,
            openTime = openTime.filter { it.value.isNotEmpty() },
            reservationPeriod = request.reservationPeriod,
            reservationPerPeriodCount = request.reservationPerPeriodCount,
            thumbnailImageUrl = request.thumbnailImageUrl,
            viewCount = 0,
            ratingSum = 0,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )
        val storeId = storePort.save(storeDomain)

        // save dayoff
        val dayoffDomainList = request.fixedDayOffList
            ?.map {
                Dayoff(
                    storeId = storeId,
                    dayoffType = DayoffType.FIXED,
                    dayoffDay = it,
                    createdAt = LocalDateTime.now(),
                    modifiedAt = LocalDateTime.now()
                )
            }
            ?: return
        dayoffPort.saveFixedDayoff(dayoffDomainList)

        // save store gallery
        storeGalleryPort.save(storeId)
    }

    override fun modifyStoreInfo() {
        TODO("Not yet implemented")
    }

}