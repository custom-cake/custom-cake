//
//  BasicOrderData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/28.
//

import Foundation

func BasicOrderAPI(orderdata: OrderData) async {
            guard let url = URL(string: "http://43.201.13.139:8080/api/orders/designs") else {
                return
            }

            
            guard let encoded = try? JSONEncoder().encode(orderdata) else {
                print("Failed to encode order")
                return
            }
            
            var request = URLRequest(url: url)
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            request.httpMethod = "POST"
        
            do {
                let (data, response) = try await URLSession.shared.upload(for: request, from: encoded)
                
                if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode != 200 {
                    // 200이 아닐 경우 문제 발생할 수도 있다.
                    print("Request failed with status code: \(httpResponse.statusCode)")
                } else {
                    print("upload success")
                }
            } catch {
                print("Checkout failed.")
            }
}
