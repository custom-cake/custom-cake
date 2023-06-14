//
//  DrawingViewModel.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/08.
//

import SwiftUI
import PencilKit

class DrawingViewModel: ObservableObject {
    //@Published var showImagePicker = false
    /*@Published*/
    //var imageData = Image("Circle")/*.resizable().aspectRatio(contentMode: .fit)*/
    
    
    init(userId: Int, storeId: Int) {
        //imageData = Image("Circle")
        self.userId = userId
        self.storeId = storeId
    }
    
    // Canvas for drawing
    
    @Published var customCakeInfoKey : String = ""
    
    @Published var userId : Int = 2
    
    @Published var storeId : Int = 1
    
    @Published var option1Id : Int = 0
    
    @Published var option1Value : String = ""
    
    @Published var option2Id : Int = 0
    
    @Published var option2Value : String = ""
    
    @Published var option3Id : Int = 0
    
    @Published var option3Value : String = ""
    
    @Published var canvas = PKCanvasView()
    
    //Tool picker
    @Published var toolPicker = PKToolPicker()
    
    // List of TextBoxes
    @Published var textBoxes: [TextBox] = []
    
    @Published var addNewBox = false
    
    // Current Index
    @Published var currentIndex: Int = 0
    
    // Saving the View Frame Size
    @Published var rect: CGRect = .zero
    
    @Published var imageData: Image = Image("Circle")
    
    @Published var asyncImageData: String = ""
    
    @Published var imageStringData: String = ""
    
    @Published var imageDrawData: Data = Data()
    
    @Published var selectedDate: Date = Date()
    
    @Published var letteringText: String = ""
    
    @Published var specialNote: String = ""
    
    @Published var selectedImages: [Image] = []
    
    @Published var selectedImageData: [Data] = []
    
    @Published var asyncSelectedImageData: [String] = []
    
    @Published var paymentAccepted: Bool = false
    
    @Published var uiImage: UIImage = UIImage()
    
    //@Binding var cakeDesignImage : Image//= Image("Circle")
    
    // Alert
    //@Published var showAlert
    
    // cancel function
    func cancelImageEditing() {
        /*
        imageData = Data(count: 0)
        canvas = PKCanvasView()
         */
        //imageData = Image("Circle")
        canvas = PKCanvasView()
        textBoxes.removeAll()
    }
    
    func cancelTextView() {
        // showing again the tool bar
        toolPicker.setVisible(true, forFirstResponder: canvas)
        canvas.becomeFirstResponder()
        
        withAnimation {
            addNewBox = false
        }
        
        // removing if cancelled
        //avoiding the removal of already added
        if !textBoxes[currentIndex].isAdded {
            textBoxes.removeLast()
        }
        
    }
    
    func saveImage() {
        
        // generating Image from both canvas and our textboxes View
        UIGraphicsBeginImageContextWithOptions(CGSize(width: 400, height: 600)/*rect.size*/, false, 0)
        //UIGraphicsBeginImageCon
        
        //canvas
        canvas.drawHierarchy(in: CGRect(origin: .zero, size: CGSize(width: 400, height: 600)/*rect.size*/), afterScreenUpdates: true)
        
        // drawing text boxes
        let SwiftUIView = ZStack {
            
            ForEach(textBoxes) { [self] box in
                
                Text(textBoxes[currentIndex].id == box.id && addNewBox ? "" : box.text)
                    .font(.system(size: 20))
                    .fontWeight(box.isBold ? .bold : .none)
                    .foregroundColor(box.textColor)
                    .offset(box.offset)
            }
        }
            
        let controller = UIHostingController(rootView: SwiftUIView).view!
        controller.frame = CGRect(x:0, y:0, width: 400, height: 600) //rect
        
        // clearing bg
        controller.backgroundColor = .clear
        canvas.backgroundColor = .clear
        
        controller.drawHierarchy(in: CGRect(origin: .zero, size: CGSize(width: 400, height: 600)/*rect.size*/), afterScreenUpdates: true)
        
        //getting image
        let generatedImage = UIGraphicsGetImageFromCurrentImageContext()
        
        // ending render
        UIGraphicsEndImageContext()
        
        if let image = generatedImage?.pngData() {
            
            //UIImageWriteToSavedPhotosAlbum(image, nil, nil, nil)
            self.imageDrawData = UIImage(data: image)!.resizeImageTo(size: CGSize(width: 400, height: 400))!.pngData()!
            print(type(of: self.imageDrawData))//image
            self.imageData = Image(uiImage: UIImage(data: image)!)
            self.uiImage = UIImage(data: image)!
            self.imageStringData = image.base64EncodedString(options: Data.Base64EncodingOptions.lineLength64Characters)
            print("success")
            print(self.imageDrawData)
        }
    }
    
    /*
    func sendAndGetImage() {
        @ObservedObject var drawImageUploadAPI = ImageUploadAPI(imageType: "CUSTOM-CAKE", imageData: self.imageDrawData)
        
        self.asyncImageData = drawImageUploadAPI.urlImage
        
        //return self.asyncImageData
    }
    
    func sendAndGetAdditionalImage() {
        
        @ObservedObject var selectedImageUploadAPI : ImageUploadAPI
        
        for data in self.selectedImageData {
            @ObservedObject var selectedImageUploadAPI = ImageUploadAPI(imageType: "CUSTOM-CAKE", imageData: data)
            
            self.asyncSelectedImageData.append(selectedImageUploadAPI.urlImage)
        }
        
    }
     */
}
