//
//  PaymentOrderOption.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import UIKit
import SwiftUI

struct PaymentComplete: View {
    @Environment(\.calendar) var calendar
    @Environment(\.presentationMode) var presentation
    
    @State private var selectedDate = Date()
    @State var letteringText: String = " "
    
    @State private var showingAlert = false
    
    var data: MenuData
    
    @ObservedObject var storedata: StoreDataAPI
    var selectedOption1: Option
    var selectedOption2: Option
    var selectedOption3: Option
    var totalPrice: Int
    
    var dateFormatter: DateFormatter {
            let formatter = DateFormatter()
            formatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
            //formatter.dateStyle = .long
            return formatter
        }
    
    var textDateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm"
        //formatter.dateStyle = .long
        return formatter
    }
    
    var body: some View {
            VStack {
                //Spacer()
                
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
                    
                    VStack(alignment: .leading, spacing: 10) {
                        Text(data.name)
                            .font(.title3)
                            .fontWeight(.bold)
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                        
                        Text(data.description ?? " ")
                            .font(.system(size: 15))
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                        
                        Text(selectedOption1.value)
                        Text(selectedOption2.value)
                        Text(selectedOption3.value)
                        
                        Text(String(totalPrice) + " 원")
                            .font(.title3)
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                    }
                    
                    Spacer()
                    
                }
                .frame(width: 330, height: 180)
                .padding()
                .background(Rectangle().fill(Color.white))
                .cornerRadius(10)
                .shadow(color: .gray, radius: 3, x: 2, y: 2)
                
                Spacer()
                
                Divider()
                
                VStack {
                    Text("픽업 날짜 검색")
                        .font(.title)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    DatePicker(selection: $selectedDate, in: Date()... /*, displayedComponents: .date*/) {
                        Text("픽업 날짜를 선택해 주세요")
                        
                        
                    }
                    
                    Spacer()
                    
                    Text(selectedDate, formatter: textDateFormatter)
                }
                .frame(height: 150)
                .padding(10)
                
                Divider()
                
                VStack {
                    Text("레터링 글자")
                        .font(.title)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    TextField(
                        "레터링글자",
                        text: $letteringText
                    )
                    .cornerRadius(5)
                    .textFieldStyle(.roundedBorder)
                }
                .frame(height: 150)
                .padding(10)
                
                Divider()
                
                Spacer()
                
                Button {
                    Task {
                        await BasicOrderAPI(orderdata: OrderData(id: 1,
                                                      storeId: storedata.id,
                                                      cakeItemId: data.id,
                                                      optionByCake1Id: selectedOption1.id,
                                                      optionByCake2Id: selectedOption2.id,
                                                      optionByCake3Id: selectedOption3.id,
                                                      requirements: letteringText,
                                                      paymentAmount: totalPrice,
                                                      pickupDatetime: dateFormatter.string(from:selectedDate)))
                    }
                    self.showingAlert.toggle()
                } label : {
                    Text(String(totalPrice) + " 원 결제하기")
                        .foregroundColor(Color.white)
                }
                .frame(height: 50)
                .frame(width: 350)
                .background(Color.black)
                .cornerRadius(10)
                .padding(10)
                .disabled(letteringText.trimmingCharacters(in: .whitespaces) == "")
                .alert("주문 완료되었습니다", isPresented: $showingAlert) {
                            Button("확인") {
                                NavigationUtil.popToRootView()
                            }
                        } message: {
                            Text("메인 화면으로 돌아갑니다")
                        }
            }
            .padding(10)
    }
}

/*
struct PaymentComplete_Previews: PreviewProvider {
    static var previews: some View {
        PaymentComplete(data: sharedMenus[0])
    }
}
*/
