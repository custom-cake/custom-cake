package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.response.*
import com.cake.customcake.application.port.`in`.StoreDetailUseCase
import com.cake.customcake.application.port.out.*
import org.springframework.stereotype.Service

@Service
class StoreDetailService(
    private val storePort: StorePort,
    private val storeNotificationPort: StoreNotificationPort,
    private val dayOffPort: DayoffPort,
    private val cakeItemPort: CakeItemPort,
    private val optionByCakePort: OptionByCakePort,
    private val reviewPort: ReviewPort,
    private val storeGalleryPort: StoreGalleryPort
) : StoreDetailUseCase {
    override fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse {
        // 디자인 케이크 상품 리스트
        val cakeItemList = cakeItemPort.loadList(storeId)

        // 휴무일 리스트
        val dayoffList = dayOffPort.loadDayOff(storeId)

        // 리뷰 평점 계산
        val reviewScore = reviewPort.calculateReviewScore(storeId)

        return storePort.loadByStoreId(storeId)
            .toResponse(
                reviewScore = reviewScore,
                cakeItemList = cakeItemList,
                dayoffList = dayoffList
            )
    }

    override fun storeCakeItemDetailInfo(cakeItemId: Long): OptionByCakeListResponse =
        OptionByCakeListResponse(
            cakeItemId = cakeItemId,
            options = optionByCakePort.loadList(cakeItemId).map { it.toResponse() }
        )

    override fun storeNotificationList(storeId: Long): StoreNotificationListResponse =
        StoreNotificationListResponse(
            storeId = storeId,
            notificationList = storeNotificationPort.loadNotificationList(storeId).map { it.toResponse() }
        )

    override fun storeNotificationDetailInfo(notificationId: Long): StoreNotificationResponse =
        storeNotificationPort.loadNotification(notificationId).toResponse()

    override fun storeReviewList(storeId: Long): ReviewListResponse {
        val nickNameAndReviewList = reviewPort.loadNickNameAndReviewList(storeId)

        return ReviewListResponse(storeId, nickNameAndReviewList.map { it.value.toResponse(it.key) })
    }

    override fun storeGalleryList(storeId: Long): GalleryListResponse {
        val storeGallery = storeGalleryPort.load(storeId)

        return storeGallery.toResponse()
    }

}