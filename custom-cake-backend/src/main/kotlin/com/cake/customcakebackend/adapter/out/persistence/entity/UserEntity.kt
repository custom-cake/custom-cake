package com.cake.customcakebackend.adapter.out.persistence.entity

import com.cake.customcakebackend.domain.SocialType
import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email

@Getter
@Table(name = "user")
@Entity
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "String", length = 50, nullable = false)
    val name: String,

    @Column(columnDefinition = "String", length = 20, nullable = false, unique = true)
    val nickname: String,

    @Column(columnDefinition = "String", length = 20, nullable = false)
    val phone: String,

    @Enumerated(EnumType.STRING)  // ENUM("KAKAO", "NAVER")
    @Column(columnDefinition = "String", length = 10, nullable = false)
    val socialType: SocialType,

    @Column(columnDefinition = "String", length = 255, nullable = false)
    val socialAccountId: String,

    @Column(columnDefinition = "TINYINT", nullable = false)
    val isAuthentication: Boolean,

    @Column(nullable = false)
    val lastConnDate: LocalDateTime
)