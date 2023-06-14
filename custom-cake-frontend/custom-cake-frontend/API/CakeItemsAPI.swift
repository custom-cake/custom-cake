//
//  CakeItemsAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/25.
//

import Foundation

class CakeItemsAPI: ObservableObject {
    @Published var id: Int = 0
    @Published var options = [Option]()
    //@Published var classifiedOption1 = [Option]()
    //@Published var classifiedOption2 = [Option]()
    //@Published var classifiedOption3 = [Option]()
    
    init(cakeId: CakeData.ID) {
            guard let url = URL(string: "http://43.201.13.139:8080/api/stores/cake-items/\(cakeId)") else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode(CakeData.self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.id = result.id
                        self.options = result.options
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
