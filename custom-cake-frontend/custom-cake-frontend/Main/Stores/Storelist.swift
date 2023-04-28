//
//  StoreList.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/29.
//

import SwiftUI

struct Storelist: View {
    
    @ObservedObject var storeDataAPI = StoreDataAPI()
    @State var translation: CGSize = .zero
    
    var stores : [StoreData]

    
    var body: some View {
        //NavigationView {
            VStack {
                ScrollView {
                    /*
                    NavigationLink (
                        destination: StoreMain(storeName: storeName, isSet: 4),
                        label : {
                            Text(storeName).foregroundColor(.white)
                        }
                    )
                    .frame(height: 32)
                    .frame(width: 90)
                    .background(Color.black)
                    .cornerRadius(5)
                     */
                    
                    ForEach(stores) { store in
                        NavigationLink (
                            destination: StoreMain(data: store)
                        ) {
                            StoreItem(data: store)
                        }
                    }
                    .padding(10)
                    
                }
                .padding(EdgeInsets(top: 30, leading: 0, bottom: 0, trailing: 0))
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(.white)
        .mask(RoundedRectangle(cornerRadius: 30, style: .continuous))
        .ignoresSafeArea(edges: .bottom)
    }
}

struct Storelist_Previews: PreviewProvider {
    static var previews: some View {
        Storelist(stores: sharedStores)
    }
}
