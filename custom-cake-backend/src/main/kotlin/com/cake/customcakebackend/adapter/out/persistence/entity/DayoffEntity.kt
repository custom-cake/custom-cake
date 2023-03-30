package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.DayoffType
import java.time.LocalDate
import javax.persistence.*

@Table(name = "dayoff")
@Entity
class DayoffEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    val store: StoreEntity,

    @Enumerated(EnumType.STRING)  // ENUM("FIXED", "DESIGNATED")
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val dayoffType: DayoffType,

    @Enumerated(EnumType.STRING)  // ENUM("MON"~"SUN")
    @Column(columnDefinition = "CHAR(3)", length = 3)
    val dayoffDay: DayOfWeekUnit,

    val dayoffDate: LocalDate

) : BaseEntity()