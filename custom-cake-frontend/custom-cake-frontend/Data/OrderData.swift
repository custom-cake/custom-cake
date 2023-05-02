//
//  OrderData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/28.
//

import Foundation

struct OrderData: Codable, Identifiable {
    
    var id : Int
    var storeId : Int
    var cakeItemId : Int
    var optionByCake1Id : Int
    var optionByCake2Id : Int
    var optionByCake3Id : Int
    var requirements : String
    var paymentAmount : Int
    var pickupDatetime : String
    
    enum CodingKeys: String, CodingKey {
            case id = "userId"
            case storeId, cakeItemId, optionByCake1Id,optionByCake2Id, optionByCake3Id, requirements, paymentAmount, pickupDatetime
        }
}

var sharedOrders = [
    OrderData(id: 1,
              storeId: 1,
              cakeItemId: 1,
              optionByCake1Id: 2,
              optionByCake2Id: 6,
              optionByCake3Id: 7,
              requirements: "Happy Birthday❤️",
              paymentAmount: 67000,
              pickupDatetime: "2023-05-01T15:00:00")
]
