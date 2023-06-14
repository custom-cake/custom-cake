//
//  StoreGalleryData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/04.
//

import Foundation

//MARK: - StoreGalleryData
struct StoreGalleryData: Decodable, Identifiable {
    let id : Int
    let storeId : Int
    let imageUrlList : [String]
    
    enum CodingKeys: String, CodingKey {
            case id, storeId, imageUrlList
        }
}

var sharedStoreGallery = [
    StoreGalleryData(id: 1,
                     storeId: 1,
                     imageUrlList: [
                        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_1.jpeg",
                        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_2.jpeg",
                        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_3.jpeg"
                    ]
                )
]
