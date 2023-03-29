package com.cake.customcakebackend.adapter.out.persistence.entity

import lombok.Getter
import javax.persistence.*

@Getter
@Table(name = "store")
@Entity
class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operator_id")
    val operator: OperatorEntity,

    @Column(columnDefinition = "CHAR(10)", length = 10, nullable = false, unique = true)
    val businessRegistrationNo: String,  // 사업자 등록 번호 10자리 (인증 필수)

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val representativeName: String,

    @Embedded
    val address: Address,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val phone: String,

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val name: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = "",

//    @Column(columnDefinition = "String", length = 50, nullable = false)
//    val openTime: String,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 30", nullable = false)
    val reservationPeriod: Int,  // 예약 주기

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 1", nullable = false)
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val thumbnailImageUrl: String,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val viewCount: Int,

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    val ratingSum: Int

) : BaseEntity()