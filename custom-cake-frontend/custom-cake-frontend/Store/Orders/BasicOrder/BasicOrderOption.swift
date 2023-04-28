//
//  BasicOrderOption.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/11.
//

import SwiftUI

struct BasicOrderOption: View {
    @State var storeName: String
    
    var data: MenuData
    
    var body: some View {
        ScrollView {
            VStack {
                // Thumbnail Image
                AsyncImage(url: URL(string: data.image)) { image in
                    image
                        .resizable()
                        .scaledToFill()
                } placeholder: {
                    
                }
                
                // Cake Name
                Text(data.name)
                    .font(.title)
                    .bold()
                    .padding(EdgeInsets(top: 15, leading: 10, bottom: 5, trailing: 10))
                
                
                // Description
                Text(data.description ?? " ")
                    .multilineTextAlignment(.center)
                    .padding(EdgeInsets(top: 0, leading: 30, bottom: 5, trailing: 30))
                
                HStack {
                    Spacer()
                    
                    Text(String(data.price) + " 원")
                        .font(.title)
                }
                .padding(EdgeInsets(top: 0, leading: 10, bottom: 10, trailing: 20))
                
                Divider()
                
                ForEach (0..<3) {_ in
                    Options()
                        .frame(height: 200)
                    
                    Divider()
                }
                
                NavigationLink (
                    destination: PaymentComplete(data: data)
                ) {
                    Text(String(data.price) + " 원 주문하기")
                        .foregroundColor(Color.white)
                }
                .frame(height: 50)
                .frame(width: 350)
                .background(Color.black)
                .cornerRadius(10)
                .padding(20)
            }
            
        }
        .ignoresSafeArea(edges: .top)
    }
}

struct BasicOrderOption_Previews: PreviewProvider {
    static var previews: some View {
        BasicOrderOption(storeName: "레이네 케이크", data: sharedMenus[0])
    }
}
