//
//  CakeStoreTopTabView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/10.
//

import SwiftUI

struct StoreTopTab: View {
    
    @ObservedObject var storeDataAPI = StoreDataAPI()
    @ObservedObject var storeNotificationAPI = StoreNotificationAPI()
    @State private var selectedPicker: storeInfo = .menu
    @Namespace private var animation
    
    var body: some View {
            VStack {
                animate()
                StoreInfoView(InfoTabs: selectedPicker, menuItems: storeDataAPI.cakeItemList/*sharedMenus*/)
            }
        }
        
    @ViewBuilder
    private func animate() -> some View {
        HStack {
            ForEach(storeInfo.allCases, id: \.self) { item in
                VStack {
                    Text(item.rawValue)
                        .frame(maxWidth: .infinity/4, minHeight: 50)
                        .font(.headline)
                        .foregroundColor(selectedPicker == item ? .black: .gray)
                    
                    if selectedPicker == item {
                        Capsule()
                            .foregroundColor(.black)
                            .frame(height: 1)
                            .matchedGeometryEffect(id: "info", in: animation)
                    }
                }
                .onTapGesture {
                    withAnimation(.easeInOut) {
                        self.selectedPicker = item
                    }
                }
            }
        }
    }
}

struct StoreTopTab_Previews: PreviewProvider {
    static var previews: some View {
        StoreTopTab()
    }
}
