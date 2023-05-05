package com.cake.customcake.adapter.`in`.web

import com.cake.customcake.adapter.`in`.web.dto.response.*
import com.cake.customcake.application.port.`in`.StoreDetailUseCase
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

    @GetMapping("/cake-items/{cakeItemId}")
    fun getCakeItemDetailInfo(
        @PathVariable cakeItemId: Long
    ): OptionByCakeListResponse =
        storeDetailUseCase.storeCakeItemDetailInfo(cakeItemId)

    /**
     * getNotificationList method
     * : 매장 공지 리스트 조회
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/12
    **/
    @GetMapping("/{storeId}/notifications")
    fun getNotificationList(
        @PathVariable storeId: Long
    ): StoreNotificationListResponse =
        storeDetailUseCase.storeNotificationList(storeId)

    /**
     * getNotificationDetailInfo method
     * : 매장 공지 디테일 조회
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/12
    **/
    @GetMapping("/notifications/{notificationId}")
    fun getNotificationDetailInfo(
        @PathVariable notificationId: Long
    ): StoreNotificationResponse =
        storeDetailUseCase.storeNotificationDetailInfo(notificationId)

    /**
     * getReviewList method
     * : 매장 리뷰 리스트 조회
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/13
    **/
    @GetMapping("/{storeId}/reviews")
    fun getReviewList(
        @PathVariable storeId: Long
    ): ReviewListResponse =
        storeDetailUseCase.storeReviewList(storeId)

    @GetMapping("/{storeId}/galleries")
    fun getGalleryList(
        @PathVariable storeId: Long
    ): GalleryListResponse =
        storeDetailUseCase.storeGalleryList(storeId)
}