//
//  TextBox.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/08.
//

import SwiftUI
import PencilKit

struct TextBox: Identifiable {
    
    var id = UUID().uuidString
    var text: String = ""
    var isBold: Bool = false
    
    // For Dragging the view over the screen
    var offset: CGSize = .zero
    var lastOffset: CGSize = .zero
    var textColor: Color = .white
    var isAdded: Bool = false
}
