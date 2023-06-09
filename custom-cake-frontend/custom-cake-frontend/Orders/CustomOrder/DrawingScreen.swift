//
//  DrawingScreen.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/08.
//

import SwiftUI
import PencilKit

struct DrawingScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    @EnvironmentObject var model: DrawingViewModel
    
    @Binding var cakeBgColor : Color
    @Binding var cakeDesignImage : Image
    
    var body: some View {
        ZStack {
            
            GeometryReader { proxy -> AnyView in
                
                let size = proxy.frame(in: .global)
                
                DispatchQueue.main.async {
                    if model.rect == .zero {
                        model.rect = size
                    }
                }
                
                return AnyView (
                    
                    
                    ZStack {
                        
                        // UIKit Pencil Kit Drawing View
                        CanvasView(canvas: $model.canvas,
                                   /*imageData: $model.imageData,
                                    */toolPicker: $model.toolPicker, cakeBgColor: $cakeBgColor, rect: size.size)
                        
                        // Custom Texts
                        // displaying text boxes
                        ForEach(model.textBoxes) { box in
                            
                            Text(model.textBoxes[model.currentIndex].id == box.id && model.addNewBox ? "" : box.text)
                                .font(.system(size: 20))
                                .fontWeight(box.isBold ? .bold : .none)
                                .foregroundColor(box.textColor)
                                .offset(box.offset)
                            
                            // drag gesture
                                .gesture(DragGesture().onChanged({ (value) in
                                    
                                    let current = value.translation
                                    
                                    //Adding with last Offset
                                    let lastOffset = box.lastOffset
                                    let newTranslation = CGSize(width: lastOffset.width + current.width, height: lastOffset.height + current.height)
                                    
                                    model.textBoxes [getIndex(textBox: box)].offset = newTranslation
                                    
                                }).onEnded({(value) in
                                    
                                    // saving the last offset for exact drag position
                                    model.textBoxes[getIndex(textBox: box)].lastOffset = value.translation
                                }))
                            
                            // editing the typed one
                                .onLongPressGesture {
                                    
                                    // closing the toolbar
                                    model.toolPicker.setVisible(false, forFirstResponder: model.canvas)
                                    model.canvas.resignFirstResponder()
                                    
                                    model.currentIndex = getIndex(textBox: box)
                                    withAnimation {
                                        model.addNewBox = true
                                    }
                                }
                        }
                    }
                )
            }
        }
        .toolbar(content: {
            ToolbarItem(placement: .navigationBarTrailing) {
                
                Button(action: {
                    cakeDesignImage = Image(uiImage: CanvasView(canvas: $model.canvas,
                                                                /*imageData: $model.imageData,
                                                                 */toolPicker: $model.toolPicker, cakeBgColor: $cakeBgColor, rect: model.rect.size).frame(width: 400, height: 600).snapshot())
                    
                    model.saveImage()
                    self.presentationMode.wrappedValue.dismiss()
                }, label: {
                    Text("Save")
                })
            }
            
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: {
                    
                    // creating One New Box
                    model.textBoxes.append(TextBox())
                    
                    // updating index
                    model.currentIndex = model.textBoxes.count - 1
                    
                    withAnimation {
                        model.addNewBox.toggle()
                    }
                    
                    //closing the toolbar
                    model.toolPicker.setVisible(false, forFirstResponder: model.canvas)
                    model.canvas.resignFirstResponder()
                }, label: {
                    Image(systemName: "plus")
                })
            }
        })
    }
    
    func getIndex(textBox: TextBox) -> Int {
        
        let index = model.textBoxes.firstIndex { (box) -> Bool in
            return textBox.id == box.id
        } ?? 0
        
        return index
    }
    
}
/*
struct DrawingScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
 */

struct CanvasView: UIViewRepresentable {
    
    @Binding var canvas: PKCanvasView
    /*@Binding var imageData: UIImage*/
    @Binding var toolPicker: PKToolPicker
    
    @Binding var cakeBgColor : Color
    
    // View Size
    var rect: CGSize
    
    var imageData = UIImage(named: "Circle")
    
    func makeUIView(context: Context) -> PKCanvasView {
        
        
        
        canvas.isOpaque = false
        canvas.backgroundColor = .clear
        canvas.drawingPolicy = .anyInput
        
        /*
        if let image = UIImage(data: imageData) {
         */
            /*
            let imageView = UIImageView(image: imageData)
            imageView.frame = CGRect(x: 0, y: 0, width: rect.width, height: rect.height)
            imageView.contentMode = .scaleAspectFit
            imageView.clipsToBounds = true
            
            let subView = canvas.subviews[0]
            subView.addSubview(imageView)
            subView.sendSubviewToBack(imageView)
             */
        
        
        let cakeCanvasView = CakeCanvas(cakeBgColor: $cakeBgColor)
        let hostingController = UIHostingController(rootView: cakeCanvasView)
        
        let cakeCanvas = hostingController.view!
        cakeCanvas.frame = CGRect(x: 0, y: 0, width: rect.width, height: rect.height)
        let subView = canvas.subviews[0]
        
        subView.addSubview(cakeCanvas)
        subView.sendSubviewToBack(cakeCanvas)
        
        /*
        let subView = canvas.subviews[0]
        subView.addSubview(canvasView)
        subView.sendSubviewToBack(canvasView)
         */
        
        /*
             .frame(width: 350, height: 350, alignment: .center)
             //.border(Color.black, width: 1)
             .cornerRadius(175)
         */
                    
                /*
                    for line in lines {
                        var path = Path()
                        path.addLines(line.points)
                        context.stroke(path, with: .color(line.color), lineWidth: line.lineWidth)
                    }
                        
                    }
                    .frame(width: 350, height: 350, alignment: .center)
                    //.border(Color.black, width: 1)
                    .cornerRadius(175)
                    .gesture(DragGesture(minimumDistance: 0, coordinateSpace: .local)
                    .onChanged({ value in
                        let newPoint = value.location
                        currentLine.points.append(newPoint)
                        self.lines.append(currentLine)
                      })
                    .onEnded({ value in
                        self.lines.append(currentLine)
                        self.currentLine = Line(points: [], color: penColor/*currentLine.color*/, lineWidth: penThickness/*thickness*/)
                    })
                    )
                 */
            
        toolPicker.setVisible(true, forFirstResponder: canvas)
        toolPicker.addObserver(canvas)
        canvas.becomeFirstResponder()
        //}
        
        return canvas
    }
    
    
    func updateUIView(_ uiView: PKCanvasView, context: Context) {
        
        
    }
    
    func snapshot() -> UIImage {
        let controller = UIHostingController(rootView: self)
        let view = controller.view

        let targetSize = controller.view.intrinsicContentSize
        view?.bounds = CGRect(origin: .zero, size: targetSize)
        view?.backgroundColor = .clear

        let renderer = UIGraphicsImageRenderer(size: targetSize)

        return renderer.image { _ in
            view?.drawHierarchy(in: controller.view.bounds, afterScreenUpdates: true)
        }
    }
    
}


 extension View {
     func snapshot() -> UIImage {
         let controller = UIHostingController(rootView: self)
         let view = controller.view
 
         let targetSize = controller.view.intrinsicContentSize
         view?.bounds = CGRect(origin: .zero, size: targetSize)
         view?.backgroundColor = .clear
 
         let renderer = UIGraphicsImageRenderer(size: targetSize)
 
         return renderer.image { _ in
             view?.drawHierarchy(in: controller.view.bounds, afterScreenUpdates: true)
         }
     }
 }

