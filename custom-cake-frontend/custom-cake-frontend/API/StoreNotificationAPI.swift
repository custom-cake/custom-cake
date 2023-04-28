//
//  File.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/25.
//

import Foundation

import Foundation

class StoreNotificationAPI: ObservableObject {
    @Published var id : Int = 0
    @Published var title : String = ""
    @Published var content : String = ""
    @Published var createdAt : String = ""
    @Published var modifiedAt : String = ""
    
    init() {
            guard let url = URL(string: "http://localhost:8080/api/stores/notifications/1") else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode(InfoData.self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.id = result.id
                        self.title = result.title
                        self.content = result.content
                        self.createdAt = result.createdAt
                        self.modifiedAt = result.modifiedAt
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
