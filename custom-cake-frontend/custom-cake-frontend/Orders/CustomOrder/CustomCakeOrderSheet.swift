//
//  HomeView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/08.
//

import SwiftUI

struct CustomCakeOrderSheet: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    //@StateObject var model = DrawingViewModel()
    @EnvironmentObject var model: DrawingViewModel
    
    @Binding var cakeDesignImage : Image
    
    @State private var cakeBgColor = Color.white
    
    var body: some View {
        VStack {
            
            ColorPicker("케이크 배경 색상", selection: $cakeBgColor)
                .padding()
                .labelsHidden()
            
            ZStack {
                //NavigationView {
                VStack {
                    /*
                     Image("Circle")
                     .resizable()
                     .aspectRatio(contentMode: .fit)
                     */
                    
                    DrawingScreen(cakeBgColor: $cakeBgColor ,cakeDesignImage: $cakeDesignImage)
                        .environmentObject(model)
                    
                    Spacer()
                    
                }
                .frame(height: 500)
                
                if model.addNewBox {
                    Color.black.opacity(0.75)
                        .ignoresSafeArea()
                    
                    // TextField
                    TextField("Text Box", text: $model.textBoxes[model.currentIndex].text)
                        .font(.system(size: 35, weight: model.textBoxes[model.currentIndex].isBold ? .bold : .regular))
                        .colorScheme(.dark)
                        .foregroundColor(model.textBoxes[model.currentIndex].textColor)
                        .padding()
                    
                    // add and cancel button
                    HStack {
                        Button(action: {
                            // toggling the isAdded
                            model.textBoxes[model.currentIndex].isAdded = true
                            
                            // closing the view
                            model.toolPicker.setVisible(true, forFirstResponder: model.canvas)
                            model.canvas.becomeFirstResponder()
                            withAnimation {
                                model.addNewBox = false
                            }
                        }, label: {
                            Text("Add")
                                .fontWeight(.heavy)
                                .foregroundColor(.white)
                                .padding()
                        })
                        
                        Spacer()
                        
                        Button(action: {
                            model.cancelTextView()
                        }, label: {
                            Text("Cancel")
                                .fontWeight(.heavy)
                                .foregroundColor(.white)
                                .padding()
                        })
                    }
                    .overlay(
                        HStack(spacing: 15) {
                            
                            // Color Picker
                            ColorPicker("", selection: $model.textBoxes[model.currentIndex].textColor)
                                .labelsHidden()
                            
                            Button(action: {
                                model.textBoxes[model.currentIndex].isBold.toggle()
                            }, label: { Text(model.textBoxes[model.currentIndex].isBold ? "Normal" : "Bold")
                                    .fontWeight(.bold)
                                    .foregroundColor(.white)
                            }
                            )
                        }
                    )
                    .frame(maxHeight: .infinity, alignment: .top)
                }
            }
            
            Spacer()
        }
    }
}
