package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.domain.DayoffDayType
import com.cake.customcakebackend.domain.DayoffType
import lombok.Getter
import java.time.LocalDate
import javax.persistence.*

@Getter
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
    val socialType: DayoffType,

    @Enumerated(EnumType.STRING)  // ENUM("MON"~"SUN")
    @Column(columnDefinition = "CHAR(3)", length = 3)
    val dayoffDay: DayoffDayType,

    val dayoffDate: LocalDate

): BaseEntity()