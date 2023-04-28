//
//  RequestAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/24.
//

import Foundation

class StoreDataAPI: ObservableObject {
    @Published var id : Int = 0
    @Published var address : String = ""
    @Published var phone : String = ""
    @Published var name : String = ""
    @Published var description : String?
    @Published var openTime : [String : String] = ["" : ""]
    @Published var reservationPeriod : Int = 0
    @Published var reservationPerPeriodCount : Int = 0
    @Published var thumbnailImageUrl : String = ""
    @Published var reviewScore : Float = 5.0
    @Published var cakeItemList = [MenuData]() //Array<Dictionary<String, Any>>
    @Published var dayoffList =  [DayOffData]() //Array<Dictionary<String, Any?>>

    
    init() {
            guard let url = URL(string: "http://localhost:8080/api/stores/1") else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode(StoreData.self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.id = result.id
                        self.address = result.address
                        self.phone = result.phone
                        self.name = result.name
                        self.description = result.description
                        self.openTime = result.openTime
                        self.reservationPeriod = result.reservationPeriod
                        self.reservationPerPeriodCount = result.reservationPerPeriodCount
                        self.thumbnailImageUrl = result.thumbnailImageUrl
                        self.reviewScore = result.reviewScore
                        self.dayoffList = result.dayoffList ?? [DayOffData]()
                        self.cakeItemList = result.cakeItemList ?? [MenuData]()
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
