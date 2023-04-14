package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.*
import com.cake.customcakebackend.application.port.`in`.StoreDetailUseCase
import com.cake.customcakebackend.application.port.out.*
import org.springframework.stereotype.Service

@Service
class StoreDetailService(
    private val userPort: LoadUserPort,
    private val storePort: StorePort,
    private val storeNotificationPort: StoreNotificationPort,
    private val storeReviewPort: ReviewPort,
    private val dayOffPort: DayoffPort,
    private val cakeItemPort: CakeItemPort,
    private val reviewPort: ReviewPort,
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

}