package com.cake.customcakebackend.application.port.out

interface LoadAllRegionsPort {
    fun load(): List<String>
}