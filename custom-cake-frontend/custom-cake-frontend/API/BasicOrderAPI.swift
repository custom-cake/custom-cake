//
//  BasicOrderData.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/28.
//

import Foundation

func BasicOrderAPI(orderdata: OrderData) async {
            guard let url = URL(string: "http://localhost:8080/api/orders/designs") else {
                return
            }

            
            guard let encoded = try? JSONEncoder().encode(sharedOrders) else {
                print("Failed to encode order")
                return
            }
            
            var request = URLRequest(url: url)
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            request.httpMethod = "POST"
        
            do {
                let (data, _) = try await URLSession.shared.upload(for: request, from: encoded)
                print("upload success")
            } catch {
                print("Checkout failed.")
            }
            
            
}
