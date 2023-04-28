//
//  StoreData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import Foundation

//MARK: - StoreData
struct StoreData: Decodable, Identifiable {
    let id : Int
    let address : String
    let phone : String
    let name : String
    let description : String
    let openTime : [String : String]
    let reservationPeriod : Int
    let reservationPerPeriodCount : Int
    let thumbnailImageUrl : String
    let reviewScore : Float
    let cakeItemList : [MenuData] //Array<Dictionary<String, Any>>
    let dayoffList :  [DayOffData] //Array<Dictionary<String, Any?>>
    
    enum CodingKeys: String, CodingKey {
            case id, address, phone, name, description, openTime, reservationPeriod, reservationPerPeriodCount, thumbnailImageUrl, reviewScore, cakeItemList, dayoffList
        }
}

//MARK: - DayOffData
struct DayOffData : Decodable, Identifiable {
    let id : Int
    let dayoffType : String
    let dayoffDay : String?
    let dayoffDate : String?
    
    enum CodingKeys: String, CodingKey {
            case id, dayoffType, dayoffDay, dayoffDate
        }
}

//MARK: - MenuData
struct MenuData : Decodable, Identifiable {
    let id : Int
    let name : String
    let description : String?
    let category : String
    let image : String
    let price : Int
    let isOnsale : Bool
    
    enum CodingKeys: String, CodingKey {
        case id, name, description, category, image, price, isOnsale
        }
}

var sharedMenus = [
    MenuData(id: 1,
             name: "카모마일 & 작약 케이크",
             description: "안녕하세요 이건 카모마일 작약케이크에 대한 설명 한줄을 넘어가면 어떻게 되는 기",
             category: "DESIGN_CAKE",
             image: "https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_1.jpeg",
             price: 50000,
             isOnsale: true),
    
    MenuData(id: 2,
             name: "블루 계란꽃 케이크",
             description: nil,
             category: "DESIGN_CAKE",
             image: "https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_2.jpeg",
             price: 50000,
             isOnsale: true)
]

var sharedDayOffs = [
    DayOffData(id: 1,
               dayoffType: "FIXED",
               dayoffDay: "SUN",
               dayoffDate: nil),
    DayOffData(id: 2,
               dayoffType: "FIXED",
               dayoffDay: "MON",
               dayoffDate: nil),
    DayOffData(id: 3,
               dayoffType: "DESIGNATED",
               dayoffDay: nil,
               dayoffDate: "2023-04-11"),
    DayOffData(id: 4,
               dayoffType: "DESIGNATED",
               dayoffDay: nil,
               dayoffDate: "2023-04-25")
]

var sharedStores = [
    StoreData(id: 1,
              address: "07006, 서울 동작구 사당로 271, 2층",
              phone: "",
              name: "레이네 케이크",
              description: "레이네 케이크 주문 채널입니다 :) \n 디자인 케이크는 물론 도시락 케이크도 판매하고 레터링 케이크 클래스도 운영하고 있습니다.",
              openTime: [
                  "FRI": "13:00~22:00",
                  "SAT": "12:00~19:00",
                  "THR": "13:00~22:00",
                  "TUE": "13:00~22:00",
                  "WED": "13:00~22:00"
              ],
              reservationPeriod: 30,
              reservationPerPeriodCount: 1,
              thumbnailImageUrl: "https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png",
              reviewScore: 3.5,
              cakeItemList: sharedMenus
              ,
              dayoffList: sharedDayOffs
            ),
    StoreData(id: 2,
              address: "07006, 서울 동작구 사당로 271, 2층",
              phone: "",
              name: "레이네 케이크",
              description: "레이네 케이크 주문 채널입니다 :) \n 디자인 케이크는 물론 도시락 케이크도 판매하고 레터링 케이크 클래스도 운영하고 있습니다.",
              openTime: [
                  "FRI": "13:00~22:00",
                  "SAT": "12:00~19:00",
                  "THR": "13:00~22:00",
                  "TUE": "13:00~22:00",
                  "WED": "13:00~22:00"
              ],
              reservationPeriod: 30,
              reservationPerPeriodCount: 1,
              thumbnailImageUrl: "https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png",
              reviewScore: 3.5,
              cakeItemList: sharedMenus
              ,
              dayoffList: sharedDayOffs
            )
    ]

