//
//  Search.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/29.
//

import SwiftUI

struct Search: View {
    
    @Environment(\.presentationMode) var presentation
    @State var location: String = " "
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical) {
                VStack (spacing: 30) {                    
                    VStack {
                        
                        Text("주소 검색")
                            .font(.title)
                            .fontWeight(.bold)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .padding(15)
                        
                        HStack {
                            Label {
                                Text(location)
                            } icon : {
                                    
                            }
                            .frame(width: 280, height: 40)
                            .overlay(
                                RoundedRectangle(cornerRadius: 3)
                                    .stroke(Color.gray, lineWidth: 0.5)
                            )
                            
                            NavigationLink (
                                destination: WebView(url: "https://swiftie1230.github.io/Kakao-address/")
                            ) {
                                Text("주소 검색")
                                    .foregroundColor(Color.white)
                            }
                            .frame(height: 40)
                            .frame(width: 75)
                            .background(Color.black)
                            .cornerRadius(5)
                        }
                        .padding(15)
                    }
                    
                    Divider()
                    
                    Schedule()
                    
                    Button {
                        self.presentation.wrappedValue.dismiss()
                    } label : {
                        Text("주소 검색")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 350)
                    .background(Color.black)
                    .cornerRadius(10)
                }
                .navigationBarHidden(true)
            }
       }
    }
}

struct Search_Previews: PreviewProvider {
    static var previews: some View {
        Search()
    }
}
