//
//  Home.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/28.
//

import SwiftUI
import MapKit

struct Home: View {
    
    //@ObservedObject var storeDataAPI = StoreDataAPI()
    //@ObservedObject var searchDataAPI : SearchDataAPI
    @ObservedObject var searchDataAPI1: SearchDataAPI //[SearchData]
    @ObservedObject var searchDataAPI2: SearchDataAPI
    
    @State var offset: CGFloat = 0
    @State var lastOffset: CGFloat = 0
    @GestureState var gestureOffset: CGFloat = 0
    @State var storeName: String = ""
    @Binding var mainStoreName : String
    
    //@Binding var markerCoordinate : CLLocationCoordinate2D //= CLLocationCoordinate2D(latitude: 37.7749, longitude: -122.4194) // 마커 위치
    //@Binding var centerCoordinate : CLLocationCoordinate2D //= CLLocationCoordinate2D(latitude: 37.4856085, longitude: 126.972290) // 지도의 중심 위치
    
    var body: some View {
        
        NavigationStack {
            ZStack {
                
                GeometryReader { proxy in
                    let frame = proxy.frame(in: .global)
                    
                    ZStack {
                        if mainStoreName == "레이네" {
                            Minimap(searchDataAPI: searchDataAPI1)
                                //.environmentObject(searchDataAPI)
                                .edgesIgnoringSafeArea(.top)
                        } else {
                            Minimap(searchDataAPI: searchDataAPI2)
                                //.environmentObject(searchDataAPI)
                                .edgesIgnoringSafeArea(.top)
                        }
                        
                        VStack {
                            HStack {
                                TextField(
                                    "매장 이름",
                                    text: $storeName
                                )
                                .cornerRadius(5)
                                .textFieldStyle(.roundedBorder)
                                
                                
                                Button {
                                    mainStoreName = storeName
                                    //searchDataAPI.searchDatas = SearchDataAPI(storeName: storeName).searchDatas
                                    print(mainStoreName)
                                    //print(searchDataAPI.searchDatas, searchDataAPI.searchDatas[0], searchDataAPI.searchDatas[0].id)
                                    // self.presentation.wrappedValue.dismiss()
                                } label : {
                                    Text("매장 검색")
                                        .foregroundColor(Color.white)
                                }
                                .frame(height: 32)
                                .frame(width: 75)
                                .background(Color.black)
                                .cornerRadius(5)
                                
                                /*
                                NavigationLink ( // BUTTON 으로 수정
                                    destination: Search(),
                                    label : {
                                        Text("매장 검색").foregroundColor(.white) // 수정
                                    })
                                .frame(height: 32)
                                .frame(width: 75)
                                .background(Color.black)
                                .cornerRadius(5)
                                 */
                            }
                            .padding(15)
                            
                            Spacer()
                        }
                    }
                }
                
                // For getting height for drag gesture
                GeometryReader { proxy -> AnyView in
                    
                    let height = proxy.frame(in: .global).height
                    
                    return AnyView(
                        ZStack {
                            if mainStoreName == "레이네" {
                                Storelist(store: StoreDataAPI(storeId: searchDataAPI1.searchDatas[0].id))

                            } else {
                                Storelist(store: StoreDataAPI(storeId: searchDataAPI2.searchDatas[0].id))

                            }

                            
                            VStack {
                                Capsule().fill(Color.gray)
                                    .frame(width: 40, height: 4)
                                    .padding(.top)
                            }
                            .frame(maxHeight: .infinity, alignment: .top)
                        }
                            .offset(y: height - 200)
                            .offset(y: -offset > 0 ? -offset <= (height-200) ? offset : -(height - 200) : 0)
                            .gesture(DragGesture().updating($gestureOffset, body: {
                                value, out, _ in
                                
                                out = value.translation.height
                                onChange()
                            }).onEnded({ value in
                                
                                let maxHeight = height - 100
                                
                                withAnimation {
                                    if -offset > 200 && -offset < maxHeight / 2 + 100 {
                                        offset = -(maxHeight / 2) + 100
                                    } else if -offset > maxHeight / 2 + 100 {
                                        offset = -maxHeight
                                    } else {
                                        offset = 0
                                    }
                                }
                                
                                // Storing Last Offset
                                // So that the gesture can continue from the last position
                                lastOffset = offset
                            })
                                    )
                    )
                }
            }
            .navigationBarHidden(true)
        }
    }
    
    func onChange() {
        DispatchQueue.main.async {
            self.offset = gestureOffset + lastOffset
        }
    }
    
}

/*
struct Home_Previews: PreviewProvider {
    static var previews: some View {
        Home()
    }
}
*/
