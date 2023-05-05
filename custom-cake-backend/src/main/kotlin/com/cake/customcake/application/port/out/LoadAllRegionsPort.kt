package com.cake.customcake.application.port.out

interface LoadAllRegionsPort {
    fun load(): List<String>
}