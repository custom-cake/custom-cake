//
//  OptionButtonStyle.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/08.
//

import Foundation
import SwiftUI

/*
 struct OptionButtonStyle: ButtonStyle {
 
 func makeBody(configuration: Configuration) -> some View {
 
 configuration.label
 .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
 .scaleEffect(configuration.isPressed ? 1.3 : 1.0)
 Image(systemName: "xmark")
 }
 }
 */

struct OptionLabel/*<Content: View>*/: View {
    var text: String

    var body: some View {
        HStack{
            Text(text)
        }
        .padding(EdgeInsets(top: 8, leading: 10, bottom: 8, trailing: 10))
        .background(Color.defaultLightGray)
        .cornerRadius(50)
    }
}

extension Color {
    static let defaultLightGray = Color("defaultgrayColor")
}

