//
//  StoreGalleryAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/04.
//

import Foundation

class StoreGalleryAPI: ObservableObject {
    @Published var id : Int = 0
    @Published var storeId : Int = 0
    @Published var imageUrlList : [String] = [" "]

    
    init(storeId: StoreData.ID) {
            guard let url = URL(string: "http://43.201.13.139:8080/api/stores/\(storeId)/galleries") else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode(StoreGalleryData.self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.id = result.id
                        self.storeId = result.storeId
                        self.imageUrlList = result.imageUrlList
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
