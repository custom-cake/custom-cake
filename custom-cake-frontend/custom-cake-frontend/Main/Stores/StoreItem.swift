//
//  StoreItem.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import SwiftUI

struct StoreItem: View {
    
    @ObservedObject var storeDataAPI = StoreDataAPI()
    
    var data: StoreData
    
    var body: some View {
        HStack {
            // Image
            AsyncImage(url: URL(string: storeDataAPI.thumbnailImageUrl/*data.thumbnailImageUrl*/))  { image in
                image
                    .resizable()
                    .scaledToFill()
            } placeholder: {
                
            }
            .frame(width: 100, height: 100)
            .background(Circle().fill(Color.gray))
            .cornerRadius(10)
            .padding(.trailing, 5)
            
            VStack(alignment: .leading, spacing: 10) {
                Text(storeDataAPI.name/*data.name*/)
                    .font(.title3)
                    .fontWeight(.bold)
                    .lineLimit(1)
                    .foregroundColor(Color.black)
                
                Text(storeDataAPI.description/*data.description*/ ?? " ")
                    .font(.system(size: 15))
                    .lineLimit(1)
                    .foregroundColor(Color.black)
                
                /*
                HStack {
                    ForEach(0..<Int(floor(requestAPI.reviewScore /*data.reviewScore*/))) {_ in
                        Image(systemName: "star.fill")
                    }
                    ForEach(0..<5-Int(floor(requestAPI.reviewScore/*data.reviewScore*/))) {_ in
                        Image(systemName: "star")
                    }
                }
                 */
                HStack {
                    ForEach(0..<Int(floor(storeDataAPI.reviewScore/*data.reviewScore*/))) {_ in
                        Image(systemName: "star.fill")
                            .foregroundColor(Color.black)
                    }
                    ForEach(0..<5-Int(floor(storeDataAPI.reviewScore/*data.reviewScore*/))) {_ in
                        Image(systemName: "star")
                            .foregroundColor(Color.black)
                    }
                }
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 5, trailing: 10))
                
            }
            
            Spacer()
            
        }
        .frame(width: 330, height: 100)
        .padding()
        .background(Rectangle().fill(Color.white))
        .cornerRadius(10)
        .shadow(color: .gray, radius: 3, x: 2, y: 2)
    }
}

struct StoreItem_Previews: PreviewProvider {
    static var previews: some View {
        StoreItem(data: sharedStores[0])
    }
}
