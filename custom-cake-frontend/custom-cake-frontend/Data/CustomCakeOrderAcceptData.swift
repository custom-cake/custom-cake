//
//  CustomCakeOrderAcceptData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/18.
//

import Foundation

//MARK: - CustomCakeOrderAcceptData
struct CustomCakeOrderAcceptData: Decodable, Identifiable {
    let id : Int?
    let approve : Bool
    let price: String?
    
    enum CodingKeys: String, CodingKey {
            case id = "cakeCustomSheetId"
            case approve
            case price
        }
}
