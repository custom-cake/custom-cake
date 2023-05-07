package com.cake.customcake.adapter.out.persistence.entity

import com.cake.customcake.common.converter.JsonColumnConverter
import javax.persistence.*

@Table(name = "store_gallery")
@Entity
class StoreGalleryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Convert(converter = JsonColumnConverter.ListConverter::class)
    @Column(name = "image_url_list", columnDefinition = "JSON")
    val imageUrlList: List<String>
) : BaseEntity()