//
//  StoreInfoView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/10.
//

import SwiftUI

struct StoreInfoView: View {
    
    @ObservedObject var storeNotificationAPI = StoreNotificationAPI()
    
    var InfoTabs: storeInfo
    var menuItems: [MenuData]
    
    var body: some View {
            ScrollView(.vertical, showsIndicators: false) {
                switch InfoTabs {
                case .menu:
                    VStack {
                        ForEach(menuItems) { menuItem in
                            MenuItem(data: menuItem)
                        }
                    }
                    .padding(20)
                    
                case .info:
                    Text(storeNotificationAPI.content/*sharedInfos[0].content*/)
                        .padding(20)
                    
                case .customcake:
                    NavigationLink (
                        destination: CustomOrderOption()
                    ) {
                        Text("커스텀 케이크 디자인")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 40)
                    .frame(width: 200)
                    .background(Color.black)
                    .cornerRadius(5)
                    .padding(20)
                    
                case .review:
                    Text("리뷰")
                        .padding(20)
             }
        }
    }
}

struct StoreInfoView_Previews: PreviewProvider {
    static var previews: some View {
        StoreInfoView(InfoTabs: .menu, menuItems: sharedMenus)
    }
}
