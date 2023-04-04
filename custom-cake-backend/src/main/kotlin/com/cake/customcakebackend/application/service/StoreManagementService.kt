package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.application.port.out.DayoffPort
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.common.DayoffType
import com.cake.customcakebackend.domain.Dayoff
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StoreManagementService(
    private val storePort: StorePort,
    private val dayoffPort: DayoffPort
) : StoreManagementUseCase {
    override fun storeInfo(operatorId: Long): List<Store> {
        return storePort.load(operatorId)
    }

    override fun registerStore(request: StoreRegisterRequest) {
        val storeDomain = Store(
            operatorId = request.operatorId,
            businessRegistrationNo = request.businessRegistrationNo,
            representativeName = request.representativeName,
            zipCode = request.zipCode,
            baseAddress = request.baseAddress,
            detailAddress = request.detailAddress,
            phone = request.phone,
            name = request.name,
            description = request.description,
            openTime = request.openTime,
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
        val dayoffDomainList = request.dayoffDayList
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
    }

    override fun modifyStoreInfo() {
        TODO("Not yet implemented")
    }

}