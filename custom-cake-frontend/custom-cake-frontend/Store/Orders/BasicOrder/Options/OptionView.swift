//
//  Option.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/25.
//

import SwiftUI

struct OptionView: View {
    
    @State var whetherSelected = false
    
    @Binding var whetherOptionsSelected : Bool
    //@Binding var selectedOptions : Array<Option>
    @Binding var selectedOption : Option
    
    var option: Option
    var selectedOptionType : Int
    
    var body: some View {
        Button(action: {
            //self.
            whetherSelected.toggle()
            //self.
            whetherOptionsSelected.toggle()
            if whetherSelected {
                selectedOption = option
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
        .disabled(!whetherSelected && whetherOptionsSelected)
        
    }
}
/*
struct OptionView_Previews: PreviewProvider {
    static var previews: some View {
        OptionView(option: sharedOptions[0], whetherOptionsSelected: Binding(true))
    }
}
*/
