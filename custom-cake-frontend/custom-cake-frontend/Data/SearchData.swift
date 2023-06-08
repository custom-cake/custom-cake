//
//  SearchData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/06/08.
//

import Foundation

/*
struct SearchDatas: Decodable, Identifiable {
    
    let id = UUID().uuidString
    var name : String
    var x : String
    var y : String
    
    enum CodingKeys: String, CodingKey {
            case id, name, x, y
        }
}
 */

struct SearchData: Decodable, Identifiable /*ObservableObject*/ {
    
    let id : Int
    let name : String
    let x : String
    let y : String
    
    enum CodingKeys: String, CodingKey {
            case id, name, x, y
        }
}

var sharedSearchs = [
    SearchData(id: 1, name: "레이네 케이크", x: "126.972290", y: "37.4856085"),
    SearchData(id: 2, name: "플랑 케이크", x: "126.891633", y: "37.5056805")
]
