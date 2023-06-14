//
//  CustomOrderDesign.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/30.
//

import SwiftUI
import PhotosUI
import FirebaseDatabase

struct CustomOrderDesign: View {
    
    @EnvironmentObject var model: DrawingViewModel
    //@StateObject var model = DrawingViewModel()
    
    @ObservedObject var customCakeOrderAcceptAPI = CustomCakeOrderAcceptAPI(storeId: 1, userId: 2)
    @State var customCakeInfoKey: String = ""
    @State var cakeDesignImage = Image("Circle")
    @State private var selectedItems = [PhotosPickerItem]()
    @State private var selectedImages = [Image]()
    @State private var selectedImageData = [Data]()
    //@State private var text: String = ""
    @State var modifying: Bool
    @State var accepted: Bool
    @State var price: String
    @State var cakeOrderSheetId: Int?

    
    @ObservedObject var storedata: StoreDataAPI
    var selectedOption1: Option
    var selectedOption2: Option
    var selectedOption3: Option
    //var paymentComplete: Bool = false
    
    var columns: [GridItem] = Array(repeating: .init(.flexible()), count: 2)
    
    var body: some View {
        ScrollView {
            VStack(spacing: 10) {
                Spacer(minLength: 50)
                
                NavigationLink (
                    destination: CustomCakeOrderSheet(cakeDesignImage: $cakeDesignImage)
                        .environmentObject(model)
                ) {

                    model.imageData
                    //cakeDesignImage
                        .resizable()
                        .scaledToFill()
                        .padding(10)
                        //.aspectRatio(contentMode: .fill
                        
                        
                }
                .frame(width: 400, height: 400)

                Spacer()
                
                Divider()
                
                Spacer()
                
                VStack {
                    
                    HStack {
                        Text("첨부 이미지")
                            .font(.title)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .padding(5)
                    
                    CustomCakeImageFiles(selectedImages: $model.selectedImages/*$selectedImages*/)
                        .environmentObject(model)
                    
                    /*
                    LazyVGrid(columns: columns) {
                        ForEach(0..<selectedImages.count, id: \.self) { i in
                            selectedImages[i]
                                .resizable()
                                .frame(width:150, height: 150)
                                .scaledToFill()
                                .padding(5)
                                    //.cornerRadius(10)
                        }
                    }
                    .frame(minWidth: 330, minHeight: 200)
                    .padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
                    .background(Rectangle().fill(Color.white))
                        //.cornerRadius(10)
                    .shadow(color: .gray, radius: 3, x: 2, y: 2)
                     */
                }
                .padding(10)
                
                Spacer()
                
                VStack {
                    HStack {
                        Text("추가 설명란")
                            .font(.title)
                            .fontWeight(.bold)
                        Spacer()
                    }
                    .padding(EdgeInsets(top: 0, leading: 5, bottom: 0, trailing: 0))
                    
                    HStack {
                        Text("케이크 디자인에 대해 구체적으로 추가 설명이 필요한 부분을 적어주세요.")
                            .font(.caption)
                            .foregroundColor(Color.gray)
                        Spacer()
                        
                    }
                    .padding(EdgeInsets(top: 0, leading: 5, bottom: 10, trailing: 0))
                    
                    TextEditor(text: $model.specialNote)//$text)
                        .padding()
                        .foregroundColor(Color.black)
                        //.font(.custom("원하는글꼴", size: 20))
                        .lineSpacing(5) //줄 간격
                        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100, maxHeight: 200)
                        .padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
                        .background(Rectangle().fill(Color.white))
                        .cornerRadius(10)
                        .shadow(color: .gray, radius: 3, x: 2, y: 2)
                }
                .padding(10)
                
                Spacer()
                
                if modifying == false {
                    NavigationLink (
                        destination: CustomPaymentComplete(/*imageUploadAPI: ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: model.imageDrawData),*/ /*selectedImageUploadAPI: ImageUploadAPI(imageType: "CUSTOM-CAKE", imageData: data),*/ modifying: false, accepted: false, cakeOrderSheetId: cakeOrderSheetId, price: price, customCakeInfoKey: $customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: model.imageStringData, selectedImages: model.selectedImages, text: model.specialNote/*text*/)
                            .environmentObject(model)
                    ) {
                        Text("\(price) +α 원 디자인 케이크 주문하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 330)
                    .background(Color.black)
                    .cornerRadius(10)
                } else if accepted == false {
                    NavigationLink (
                        destination: CustomPaymentComplete(/*imageUploadAPI: ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: model.imageDrawData),*/ /*selectedImageUploadAPI: ImageUploadAPI(imageType: "CUSTOM-CAKE", imageData: data),*/ modifying: true, accepted: false, cakeOrderSheetId: cakeOrderSheetId, price: price, customCakeInfoKey: $customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: model.imageStringData, selectedImages: model.selectedImages, text: model.specialNote/*text*/)
                            .environmentObject(model)
                    ) {
                        Text("\(price) +α 원 디자인 케이크 수정 주문하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 330)
                    .background(Color.black)
                    .cornerRadius(10)
                } else if accepted == true {
                    NavigationLink (
                        destination: CustomPaymentComplete(/*imageUploadAPI: ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: model.imageDrawData),*/ /*selectedImageUploadAPI: ImageUploadAPI(imageType: "CUSTOM-CAKE", imageData: data),*/ modifying: true, accepted: true, cakeOrderSheetId: cakeOrderSheetId, price: price, customCakeInfoKey: $customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: model.imageStringData, selectedImages: model.selectedImages, text: model.specialNote/*text*/)
                            .environmentObject(model)
                    ) {
                        Text("\(price)원 디자인 케이크 최종 주문서 확인하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 330)
                    .background(Color.black)
                    .cornerRadius(10)
                }
                
                
            }
        }
        .toolbar {
                PhotosPicker("Select images", selection: $selectedItems, matching: .images)
        }
        .onChange(of: selectedItems) { _ in
            Task {
                /*selectedImages*/model.selectedImages.removeAll()
                    
                for item in selectedItems {
                    if let data = try? await item.loadTransferable(type: Data.self) {
                        if let uiImage = UIImage(data: data) {
                            if let imageData = uiImage.resizeImageTo(size: CGSize(width: 150, height: 150))!.pngData() {

                                model.selectedImageData.append(imageData)
                                print("success")
                                print(model.selectedImageData)
                                print(type(of: imageData))
                            }
                            let image = Image(uiImage: uiImage)
                            /*selectedImages*/model.selectedImages.append(image)
                            //model.selectedImageData.append(data)
                        }
                    }
                    
                    
                }
            }
        }
    }
}

/*
 struct CustomOrderDesign_Previews: PreviewProvider {
     static var previews: some View {
         CustomOrderDesign()
     }
 }
 */

