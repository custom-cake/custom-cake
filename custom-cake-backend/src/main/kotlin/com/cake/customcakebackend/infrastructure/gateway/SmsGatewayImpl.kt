package com.cake.customcakebackend.infrastructure.gateway

import org.springframework.stereotype.Component

@Component
class SmsGatewayImpl : SmsGateway {

    override fun send(target: String, content: String) {
        println("send sms!! target : $target, content : $content")
    }
}