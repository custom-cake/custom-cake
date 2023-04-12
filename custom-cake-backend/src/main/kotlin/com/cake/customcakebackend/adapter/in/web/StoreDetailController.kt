package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreDetailInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreNotificationIdListResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreNotificationResponse
import com.cake.customcakebackend.application.port.`in`.StoreDetailUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreDetailController(
   private val storeDetailUseCase: StoreDetailUseCase
) {

    /**
     * getDetailInfo method
     * : 매장 디테일 페이지 조회 (디폴트 기본 주문 탭)
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/11
    **/
    @GetMapping("/{storeId}")
    fun getDetailInfo(
        @PathVariable storeId: Long
    ): StoreDetailInfoResponse =
        storeDetailUseCase.storeDetailInfo(storeId)

    /**
     * getNotificationList method
     * : 매장 공지 리스트 조회 (공지 id 값만)
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/12
    **/
    @GetMapping("/{storeId}/notifications")
    fun getNotificationList(
        @PathVariable storeId: Long
    ): StoreNotificationIdListResponse =
        storeDetailUseCase.storeNotificationIdList(storeId)

    @GetMapping("/notifications/{notificationId}")
    fun getNotificationDetailInfo(
        @PathVariable notificationId: Long
    ): StoreNotificationResponse =
        storeDetailUseCase.storeNotificationDetailInfo(notificationId)

}