package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.domain.CakeOption2Type
import lombok.Getter
import javax.persistence.*

@Getter
@Table(name = "cake_option3")
@Entity
class CakeOption3Entity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val name: String,

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    val price: Int,

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    val isUsed: Boolean

) : BaseEntity()
