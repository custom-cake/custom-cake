package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreDetailInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toResponse
import com.cake.customcakebackend.application.port.`in`.StoreDetailUseCase
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.application.port.out.ReviewPort
import com.cake.customcakebackend.application.port.out.StorePort
import org.springframework.stereotype.Service

@Service
class StoreDetailService(
    private val storePort: StorePort,
    private val cakeItemPort: CakeItemPort,
    private val reviewPort: ReviewPort
) : StoreDetailUseCase {
    override fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse {
        // 디자인 케이크 상품 리스트
        val cakeItemList = cakeItemPort.loadList(storeId)

        // 리뷰 평점 계산
        val reviewScore = reviewPort.calculateReviewScore(storeId)

        return storePort.loadByStoreId(storeId).toResponse(reviewScore, cakeItemList)
    }
}