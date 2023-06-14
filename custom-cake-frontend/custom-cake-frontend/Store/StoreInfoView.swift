//
//  StoreInfoView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/10.
//

import SwiftUI

struct StoreInfoView: View {
    
    @ObservedObject var storeNotificationAPI: StoreNotificationAPI
    //@ObservedObject var storeDataAPI = StoreDataAPI()
    @ObservedObject var storeGalleryAPI: StoreGalleryAPI
    
    var InfoTabs: storeInfo
    @ObservedObject var storeDataAPI : StoreDataAPI //StoreData
    var menuItems: [MenuData]
    var columns: [GridItem] = Array(repeating: .init(.flexible()), count: 2)
    
    var imageUrlList = [
        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg",
        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg",
        "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"
    ]
    
    var body: some View {
            ScrollView(.vertical, showsIndicators: false) {
                switch InfoTabs {
                case .menu:
                    VStack {
                        ForEach(storeDataAPI.cakeItemList) { menuItem in
                            MenuItem(data: menuItem, storedata: storeDataAPI)
                        }
                    }
                    .padding(20)
                    
                case .info:
                    Text(storeNotificationAPI.content/*sharedInfos[0].content*/)
                        .padding(20)
                    
                case .gallery:
                    LazyVGrid(columns: columns) {
                        ForEach(imageUrlList) { imageUrl in
                            AsyncImage(url: URL(string: imageUrl)){ image in
                                image
                                    .resizable()
                            } placeholder: {
                                
                            }
                            .cornerRadius(10)
                            .frame(width: 170, height: 170)
                            //.padding()
                            }
                        /*
                        ForEach(storeGalleryAPI.imageUrlList) { imageUrl in
                            AsyncImage(url: URL(string: imageUrl)){ image in
                                     image
                                         .resizable()
                                 } placeholder: {
                                     
                                 }
                                .cornerRadius(15)
                                .frame(width: 150, height: 150)
                                .padding()
                            }
                         */
                          }
                        .padding(20)
                    
                case .customcake:
                    CustomOrderOption(cakeItemAPI: CakeItemsAPI(cakeId: storeDataAPI.cakeItemList[0].id), storedata: storeDataAPI, model: DrawingViewModel(userId: 2, storeId: storeDataAPI.id))
                        .padding(20)
                    /*
                    NavigationLink (
                        destination:  //BasicOrderOption()
                    ) {
                        Text("커스텀 케이크 디자인")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 40)
                    .frame(width: 200)
                    .background(Color.black)
                    .cornerRadius(5)
                    .padding(20)
                     */
                
                /*
                case .review:
                    Text("리뷰")
                        .padding(20)
                 */
             }
        }
    }
}

/*
struct StoreInfoView_Previews: PreviewProvider {
    static var previews: some View {
        StoreInfoView(InfoTabs: .menu, storeDataAPI: StoreDataAPI(), menuItems: sharedMenus)
    }
}
*/

extension String: Identifiable {
    public typealias ID = Int
    public var id: Int {
        return hash
    }
}
