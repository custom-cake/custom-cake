package com.cake.customcakebackend.infrastructure.gateway

interface SmsGateway {

    fun send(target: String, content: String)
}