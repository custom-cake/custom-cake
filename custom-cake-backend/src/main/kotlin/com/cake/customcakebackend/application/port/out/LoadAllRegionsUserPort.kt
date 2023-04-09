package com.cake.customcakebackend.application.port.out

interface LoadAllRegionsUserPort {
    fun load(): List<String>
}