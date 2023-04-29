//
//  CakeData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/25.
//

import Foundation
import SwiftUI

//MARK: - CakeData
struct CakeData: Decodable, Identifiable {
    let id : Int
    let options : [Option]
    
    enum CodingKeys: String, CodingKey {
            case id = "cakeItemId"
            case options
        }
}


//MARK: - Option
struct Option : Decodable, Identifiable {
    let id : Int
    let type : Int
    let value : String
    let price : Int
    
    enum CodingKeys: String, CodingKey {
            case id, type, value, price
        }
}


var sharedCakes = [
    CakeData(id: 1,
             options: sharedOptions),
    
    CakeData(id: 1,
             options: sharedOptions)
]

var sharedOptions = [
    Option(id: 1, type: 1, value: "원형, 1호, 1단, 레터링 10글자 제한", price : 0),
    Option(id: 2, type: 1, value: "원형, 2호, 1단, 레터링 15글자 제한", price : 11000),
    Option(id: 3, type: 2, value: "바닐라시트, 우유생크림, 크림치즈", price : 0),
    Option(id: 4, type: 2, value: "초코시트, 우유생크림, 크림치즈", price : 2000),
    Option(id: 7, type: 2, value: "바닐라시트, 초코크림, 크림치즈", price : 0),
    Option(id: 8, type: 2, value: "바닐라시트, 딸기생크림, 크림치즈", price : 0),
    Option(id: 9, type: 3, value: "아이스팩", price : 3000),
]
