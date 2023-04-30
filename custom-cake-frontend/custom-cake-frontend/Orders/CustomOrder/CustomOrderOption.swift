//
//  CustomOrderOption.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import SwiftUI

struct CustomOrderOption: View {
    
    @ObservedObject var cakeItemAPI : CakeItemsAPI
    
    var storedata: StoreDataAPI
    
    @State var selectedOption1 = Option(id: 1, type: 1, value: "원형, 1호, 1단, 레터링 10글자 제한", price : 0)
    @State var selectedOption2 = Option(id: 3, type: 2, value: "바닐라시트, 우유생크림, 크림치즈", price : 0)
    @State var selectedOption3 = Option(id: 3, type: 3, value: "바닐라시트, 우유생크림, 크림치즈", price : 0)
    
    @State var whetherOption1Selected = false
    @State var whetherOption2Selected = false
    @State var whetherOption3Selected = false
    
    
    //var data: MenuData
    var options = [1, 2, 3]
    
    var body: some View {
        
        //ScrollView {
            VStack {
                Text("🍰 디자인 케이크 주문 🍰")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
                    
                Divider()
                /*
                AsyncImage(url: URL(string: data.image)) { image in
                    image
                        .resizable()
                        .scaledToFill()
                } placeholder: {
                    
                }
                
                Text(data.name)
                    .font(.title)
                    .bold()
                    .padding(EdgeInsets(top: 15, leading: 10, bottom: 5, trailing: 10))
                
                
                Text(data.description ?? " ")
                    .multilineTextAlignment(.center)
                    .padding(EdgeInsets(top: 0, leading: 30, bottom: 5, trailing: 30))
                
                HStack {
                    Spacer()
                    
                    Text(String(data.price) + " 원")
                        .font(.title)
                }
                .padding(EdgeInsets(top: 0, leading: 10, bottom: 10, trailing: 20))
                
                Divider()
                */
                
                VStack(spacing: 10) {
                    HStack {
                        Text("옵션 1")
                            .font(.title)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                    Spacer()
                    /*
                    Option1(selectedOption1: $selectedOption1, whetherOption1Selected: $whetherOption1Selected)
                     */
                    ForEach(cakeItemAPI.options) { option in
                        if option.type == 1 {
                            OptionView1(whetherOption1Selected: $whetherOption1Selected, selectedOption1: $selectedOption1, option: option)
                                .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                        }
                    }
                    Spacer()
                    Divider()
                }
                //.padding(10)
                
                VStack(spacing: 10) {
                    HStack {
                        Text("옵션 2")
                            .font(.title)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                    Spacer()
                    /*
                    Option2(selectedOption2: $selectedOption2, whetherOption2Selected: $whetherOption2Selected)
                     */
                    ForEach(cakeItemAPI.options) { option in
                        if option.type == 2 {
                            OptionView2(whetherOption2Selected: $whetherOption2Selected, selectedOption2: $selectedOption2, option: option)
                                .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                        }
                    }
                    Spacer()
                    Divider()
                }
                //.padding(10)
                
                VStack(spacing: 10) {
                    HStack {
                        Text("옵션 3")
                            .font(.title)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                    Spacer()
                    /*
                    Option3(selectedOption3: $selectedOption3, whetherOption3Selected: $whetherOption3Selected)
                     */
                    ForEach(cakeItemAPI.options) { option in
                        if option.type == 3 {
                            OptionView3(whetherOption3Selected: $whetherOption3Selected, selectedOption3: $selectedOption3, option: option)
                                .padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 0))
                        }
                    }
                    Spacer()
                    Divider()
                }
                //.padding(10)
                
                /*
                ForEach(options, id: \.self) { option in
                    VStack(spacing: 10) {
                        HStack {
                            Text("옵션 \(option)")
                                .font(.title)
                                .fontWeight(.bold)
                            Spacer()
                        }
                        .padding(5)
                        Spacer()
                        Options(selectedOption1: $selectedOption1,
                                selectedOption2: $selectedOption2,
                                selectedOption1: $selectedOption1,
                                whetherOption1Selected: $whetherOption1Selected,
                                whetherOption2Selected:
                                    $whetherOption2Selected,
                                whetherOption3Selected:
                                    $whetherOption3Selected,
                                optionType: option)
                        Spacer()
                        Divider()
                    }
                    .padding(10)
                }
                 */
                
                NavigationLink (
                    destination: CustomOrderDesign(storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3)
                    /*PaymentComplete(data: data, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, totalPrice: data.price + selectedOption1.price + selectedOption2.price + selectedOption3.price)*/
                ) {
                    Text("디자인 케이크 커스텀 주문하기")
                        .foregroundColor(Color.white)
                }
                .frame(height: 50)
                .frame(width: 330)
                .background(Color.black)
                .cornerRadius(10)
                .padding(EdgeInsets(top: 5, leading: 0, bottom: 0, trailing: 0))
                .disabled(whetherOption1Selected == false || whetherOption2Selected == false || whetherOption3Selected == false)
            }
            //.padding()
        //}
        //.ignoresSafeArea(edges: .top)
    }
}

/*
struct CustomOrderOption_Previews: PreviewProvider {
    static var previews: some View {
        CustomOrderOption()
    }
}
*/
