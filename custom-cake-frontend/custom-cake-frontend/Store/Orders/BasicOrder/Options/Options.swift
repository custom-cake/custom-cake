//
//  Options.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//
/*
import SwiftUI

struct Options: View {
    
    @ObservedObject var cakeItemsAPI = CakeItemsAPI()
    
    @Binding var selectedOptions : Array<Option>
    @Binding var selectedOption1 : Option
    @Binding var selectedOption2 : Option
    @Binding var selectedOption3 : Option
    @Binding var whetherOption1Selected : Bool
    @Binding var whetherOption2Selected : Bool
    @Binding var whetherOption3Selected : Bool

    var optionType : Int
    
    var body: some View {
        VStack {
            if optionType == 1 {
                ForEach(cakeItemsAPI.options) { option in
                    if option.type == 1 {
                        OptionView(whetherOptionsSelected: $whetherOption1Selected, selectedOptions: $selectedOptions, option: option, selectedOptionType: 1)
                            .padding(5)
                    }
                }
            } else if optionType == 2 {
                ForEach(cakeItemsAPI.options) { option in
                    if option.type == 2 {
                        OptionView(whetherOptionsSelected: $whetherOption2Selected, selectedOptions: $selectedOptions, option: option, selectedOptionType: 2)
                            .padding(5)
                    }
                }
            } else {
                ForEach(cakeItemsAPI.options) { option in
                    if option.type == 3 {
                        OptionView(whetherOptionsSelected: $whetherOption3Selected, selectedOptions: $selectedOptions, option: option, selectedOptionType: 3)
                            .padding(5)
                    }
                }
            }
            
        }
    }
    
}
*/
/*
struct Options_Previews: PreviewProvider {
    static var previews: some View {
        Options(optionType: sharedOptions[0].type, selectedOptions: )
    }
}
*/
