//
//  CustomeCakeInfo.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/17.
//

import Foundation
import FirebaseDatabase

struct CustomCakeInfo: Codable, Hashable {
    
    let userId : Int
    let storeId : Int
    let option1Id : Int
    let option1Value : String
    let option2Id : Int
    let option2Value : String
    let option3Id : Int
    let option3Value : String
    let designImage : String
    let additionalImageList : [String]
    let requirements : String
    let pickupDatetime : String
}

/*
final class CustomCakeInfoViewModel: ObservableObject {
    @Published var customCakeInfos: [CustomCakeInfo] = []
    
    private lazy var databasePath: DatabaseReference? = {
        let ref = Database.database().reference().child("CustomCakeInfos")
        return ref
    }()
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    /*
     func listentoRealtimeDatabase() {
     guard let databasePath = databasePath else {
     return
     }
     databasePath
     .observe(.childAdded) { [weak self] snapshot  in
     guard
     let self = self,
     var json = snapshot.value as? [String: Any]
     else {
     return
     }
     json["userId"] = snapshot.key
     do {
     let customCakeInfoData = try JSONSerialization.data(withJSONObject: json)
     let customCakeInfo = try self.decoder.decode(CustomCakeInfo.self, from: customCakeInfoData)
     self.customCakeInfos.append(customCakeInfo)
     } catch {
     print("an error occurred", error)
     }
     }
     }
     
     func stopListening() {
     databasePath?.removeAllObservers()
     }
     */
}
*/
