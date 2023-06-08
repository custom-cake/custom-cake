//
//  Main.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/28.
//

import SwiftUI
import MapKit

struct Main: View {
    //@ObservedObject var storeDataAPI = StoreDataAPI(storeId: 1)
    @ObservedObject var searchDataAPI1 = SearchDataAPI(storeName: "레이네")
    @ObservedObject var searchDataAPI2 = SearchDataAPI(storeName: "플랑")
    
    @State var markerCoordinate = CLLocationCoordinate2D(latitude: 37.7749, longitude: -122.4194) // 마커 위치
    @State var centerCoordinate = CLLocationCoordinate2D(latitude: 37.4856085, longitude: 126.972290) // 지도의 중심 위치
    @State var stack = NavigationPath()
    @State var chatExist : Bool = false
    @State var mainStoreName : String = "레이네"
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor.white
        //chatExist = false
    }
    
    var body: some View {
        NavigationStack(path: $stack) {
            //TabView {
            Home(searchDataAPI1: searchDataAPI1, searchDataAPI2: searchDataAPI2/*markerCoordinate: $markerCoordinate, centerCoordinate: $centerCoordinate*/, mainStoreName: $mainStoreName)
                    //.environmentObject(searchDataAPI1,)
                    .tabItem {
                        Image(systemName: "house")
                        Text("Home")
                    }
                
                /*
                Chat()
                    .tabItem {
                        Image(systemName: "message")
                        Text("message")
                    }
                 */
                /*
                Profile()
                    .tabItem {
                        Image(systemName: "person")
                        Text("profile")
                    }
                 */
           // }
        }
    }
}

struct Main_Previews: PreviewProvider {
    static var previews: some View {
        Main()
    }
}
