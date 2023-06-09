package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.DayOfWeekUnit
import com.cake.customcake.common.converter.JsonColumnConverter
import javax.persistence.*

@Table(name = "store")
@Entity
class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @OneToOne
    @Column(name = "operator_id")
    val operatorId: Long,

    @Column(columnDefinition = "CHAR(10)", length = 10, nullable = false, unique = true)
    val businessRegistrationNo: String,  // 사업자 등록 번호 10자리 (인증 필수)

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val representativeName: String,

    @Embedded
    val address: Address,

    @Column(columnDefinition = "String", length = 20)
    val phone: String? = "",

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val name: String,  // 매장 이름

    @Column(columnDefinition = "TEXT")
    val description: String? = "",

    @Convert(converter = JsonColumnConverter.OpenTimeConverter::class)
    @Column(name= "open_time", columnDefinition = "JSON", nullable = false)
    val openTime: Map<DayOfWeekUnit, String>,  // e.g. mapof(MON to "12:00~19:00", THU to "12:00~19:00")

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 30", nullable = false)
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 1", nullable = false)
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수

    @Column(columnDefinition = "String", length = 255, nullable = false)
    var thumbnailImageUrl: String,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val viewCount: Int,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val ratingSum: Int

) : BaseEntity()