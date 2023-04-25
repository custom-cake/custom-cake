package com.cake.customcakebackend.adapter.out.persistence

import com.cake.customcakebackend.adapter.out.persistence.mapper.CakeDesignOrderMapper
import com.cake.customcakebackend.adapter.out.persistence.repository.CakeDesignOrderRepository
import com.cake.customcakebackend.application.port.out.SaveCakeDesignOrderPort
import com.cake.customcakebackend.domain.CakeDesignOrder
import org.springframework.stereotype.Component

@Component
class CakeDesignOrderPersistenceAdapter(
    private val cakeDesignOrderRepository: CakeDesignOrderRepository,
    private val cakeDesignOrderMapper: CakeDesignOrderMapper
) : SaveCakeDesignOrderPort {

    override fun save(cakeDesignOrder: CakeDesignOrder) {
        cakeDesignOrderRepository.save(cakeDesignOrderMapper.toEntity(cakeDesignOrder))
    }
}