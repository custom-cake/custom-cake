//
//  CustomCakeOrderAcceptAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/18.
//

import Foundation


class CustomCakeOrderAcceptAPI: ObservableObject {
    @Published var id: Int? = nil
    @Published var approve: Bool = false
    @Published var price: String? = nil

    
    init(storeId: Int, userId: Int) {
            guard let url = URL(string: "http://43.201.13.139:8080/api/orders/customs/status?storeId=\(storeId)&userId=\(userId)") else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode(CustomCakeOrderAcceptData.self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.id = result.id ?? nil
                        self.approve = result.approve
                        self.price = result.price ?? nil
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
