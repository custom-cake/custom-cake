//
//  PaymentOrderOption.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import SwiftUI

struct PaymentComplete: View {
    @Environment(\.calendar) var calendar
    @Environment(\.presentationMode) var presentation
    
    @State private var selectedDate = Date()
    @State var letteringText: String = " "
    
    var data: MenuData
    
    var body: some View {
        ScrollView {
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
                        
                        Text("선택한 옵션 1 : 1호")
                        Text("선택한 옵션 2 : 시트")
                        
                        Text(String(data.price) + " 원")
                            .font(.title3)
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                    }
                    
                    Spacer()
                    
                }
                .frame(width: 330, height: 150)
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
                    
                    DatePicker(selection: $selectedDate, in: Date()..., displayedComponents: .date) {
                        Text("픽업 날짜를 선택해 주세요")
                    }
                    
                    Text(selectedDate.formatted(date: .numeric, time: .omitted))
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
                
                VStack {
                    Text("결제 수단")
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
                
                Button {
                    self.presentation.wrappedValue.dismiss()
                } label : {
                    Text(String(data.price) + " 원 결제하기")
                        .foregroundColor(Color.white)
                }
                .frame(height: 50)
                .frame(width: 350)
                .background(Color.black)
                .cornerRadius(10)
                .padding(10)
            }
            .padding(10)
        }
    }
}

struct PaymentComplete_Previews: PreviewProvider {
    static var previews: some View {
        PaymentComplete(data: sharedMenus[0])
    }
}
