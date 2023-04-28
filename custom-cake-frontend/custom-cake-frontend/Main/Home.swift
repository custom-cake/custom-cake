//
//  Home.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/28.
//

import SwiftUI

struct Home: View {
    
    @ObservedObject var storeDataAPI = StoreDataAPI()
    @State var offset: CGFloat = 0
    @State var lastOffset: CGFloat = 0
    @GestureState var gestureOffset: CGFloat = 0
    @State var storeName: String = ""
    
    var body: some View {
        
        NavigationStack {
            ZStack {
                
                GeometryReader { proxy in
                    let frame = proxy.frame(in: .global)
                    
                    ZStack {
                        Minimap().edgesIgnoringSafeArea(.top)
                        
                        VStack {
                            HStack {
                                TextField(
                                    "매장 이름",
                                    text: $storeName
                                )
                                .cornerRadius(5)
                                .textFieldStyle(.roundedBorder)
                                
                                
                                NavigationLink (
                                    destination: Search(),
                                    label : {
                                        Text("옵션 검색").foregroundColor(.white)
                                    })
                                .frame(height: 32)
                                .frame(width: 75)
                                .background(Color.black)
                                .cornerRadius(5)
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
                            Storelist(stores: sharedStores)
                            
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

struct Home_Previews: PreviewProvider {
    static var previews: some View {
        Home()
    }
}
