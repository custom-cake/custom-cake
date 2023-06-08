//
//  CustomOptionView3.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/18.
//

import SwiftUI

struct CustomOptionView3: View {
    @EnvironmentObject var model: DrawingViewModel
    
    @State var whetherSelected = false
    
    @Binding var whetherOption3Selected : Bool
    //@Binding var selectedOptions : Array<Option>
    @Binding var selectedOption3 : Option
    
    var option: Option
    //var selectedOptionType : Int
    
    var body: some View {
        Button(action: {
            //self.
            whetherSelected.toggle()
            //self.
            whetherOption3Selected.toggle()
            if whetherSelected {
                selectedOption3 = option
                model.option3Id = option.id
                model.option3Value = option.value
            } else if whetherOption3Selected == false {
                selectedOption3 = Option(id: 1, type: 1, value: "원형, 1호, 1단, 레터링 10글자 제한", price : 0)
                model.option3Id = 1
                model.option3Value = "원형, 1호, 1단, 레터링 10글자 제한"
            }
        }) {
            VStack(alignment:.leading, spacing: 10) {
                Text(option.value)
                    .font(.title3)
                    .fontWeight(.bold)
                    .foregroundColor(.black)
                
                HStack {
                    Spacer()
                    Text("+" + String(option.price) + "원")
                        .foregroundColor(.black)
                }
            }
            .frame(width: 330, height: 80)
            .padding()
            .background(whetherSelected ? Rectangle().fill(Color.defaultLightGray) : Rectangle().fill(Color.white))
            .cornerRadius(10)
            .shadow(color: .gray, radius: 3, x: 2, y: 2)
        }
        .disabled(!whetherSelected && whetherOption3Selected)
    }
}

/*
struct CustomOptionView3_Previews: PreviewProvider {
    static var previews: some View {
        CustomOptionView3()
    }
}
*/
