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

    // @ManyToOne
    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Enumerated(EnumType.STRING)  // ENUM("FIXED", "DESIGNATED")
    @Column(columnDefinition = "String", length = 20, nullable = false)
    val dayoffType: DayoffType,

    @Enumerated(EnumType.STRING)  // ENUM("MON"~"SUN")
    @Column(columnDefinition = "CHAR(3)", length = 3)
    val dayoffDay: DayOfWeekUnit? = null,

    val dayoffDate: LocalDate? = null

) : BaseEntity()