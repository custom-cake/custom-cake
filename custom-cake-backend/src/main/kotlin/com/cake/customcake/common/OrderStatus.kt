package com.cake.customcake.common

enum class OrderStatus {
    NEW,            // 신규주문(채팅에서 주문완료된 주문이 신규주문으로 들어옴)
    IN_PROGRESS,    // 주문진행중(결제완료된 주문을 승낙한 경우)
    PICK_UP,        // 픽업 완료
    CANCELED        // 주문 취소
}