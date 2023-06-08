//
//  CustomCakeImageFiles.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/07.
//

import SwiftUI

struct CustomCakeImageFiles: View {
    
    @EnvironmentObject var model: DrawingViewModel
    @Binding var selectedImages : [Image]
    
    var columns: [GridItem] = Array(repeating: .init(.flexible()), count: 2)
    
    var body: some View {
        LazyVGrid(columns: columns) {
            ForEach(0..<selectedImages.count, id: \.self) { i in
                selectedImages[i]
                    .resizable()
                    .frame(width:150, height: 150)
                    .scaledToFill()
                    .padding(5)
                    .cornerRadius(10)
            }
        }
        .frame(minWidth: 330, minHeight: 200)
        .padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
        .background(Rectangle().fill(Color.white))
        .cornerRadius(10)
        .shadow(color: .gray, radius: 3, x: 2, y: 2)
    }
}

/*
 struct CustomCakeImageFiles_Previews: PreviewProvider {
 static var previews: some View {
 CustomCakeImageFiles()
 }
 }
 */
