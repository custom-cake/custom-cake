//
//  MenuItem.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/13.
//

import SwiftUI

struct MenuItem: View {
    
    //@ObservedObject var cakeItemAPI = CakeItemsAPI(cakeId: )
    
    var data: MenuData
    var storedata: StoreDataAPI
    
    var body: some View {
        NavigationLink (
            destination: BasicOrderOption(cakeItemAPI: CakeItemsAPI(cakeId: data.id), storedata: storedata, data: data)
        ) {
            HStack {
                // Image
                AsyncImage(url: URL(string: data.image))  { image in
                    image
                        .resizable()
                        .scaledToFill()
                } placeholder: {
                    
                }
                .frame(width: 100, height: 100)
                .background(Circle().fill(Color.gray))
                .cornerRadius(10)
                .clipShape(Circle())
                .padding(.trailing, 5)
                
                VStack(alignment: .leading, spacing: 8) {
                    Text(data.name)
                        .font(.title3)
                        .fontWeight(.bold)
                        .lineLimit(1)
                        .foregroundColor(Color.black)
                    
                    Text(data.description ?? " ")
                        .font(.system(size: 15))
                        .lineLimit(1)
                        .foregroundColor(Color.black)
                    
                    Text(String(data.price) + " 원")
                        .font(.title3)
                        .lineLimit(1)
                        .foregroundColor(Color.black)
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
}


/*
struct MenuItem_Previews: PreviewProvider {
    static var previews: some View {
        MenuItem(data: sharedMenus[0])
    }
}
 */

