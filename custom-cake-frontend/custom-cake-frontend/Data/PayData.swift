//
//  PayData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/19.
//

import Foundation

//MARK: - PayData
struct PayData: Codable {
    var cakeCustomSheetId : Int
    
    enum CodingKeys: String, CodingKey {
            case cakeCustomSheetId
        }
}
