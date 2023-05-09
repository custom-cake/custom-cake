//
//  Main.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/28.
//

import SwiftUI

struct Main: View {
    @ObservedObject var storeDataAPI = StoreDataAPI()
    
    @State var stack = NavigationPath()
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor.white
    }
    
    var body: some View {
        NavigationStack(path: $stack) {
            TabView {
                Home()
                    .tabItem {
                        Image(systemName: "house")
                        Text("Home")
                    }
                
                Chat()
                    .tabItem {
                        Image(systemName: "message")
                        Text("message")
                    }
                
                Profile()
                    .tabItem {
                        Image(systemName: "person")
                        Text("profile")
                    }
            }
        }
    }
}

struct Main_Previews: PreviewProvider {
    static var previews: some View {
        Main()
    }
}
