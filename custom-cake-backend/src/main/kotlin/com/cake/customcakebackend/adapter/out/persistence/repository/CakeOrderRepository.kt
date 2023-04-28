package com.cake.customcakebackend.adapter.out.persistence.repository

import com.cake.customcakebackend.adapter.out.persistence.entity.CakeDesignOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CakeDesignOrderRepository : JpaRepository<CakeDesignOrderEntity, Long> {
}