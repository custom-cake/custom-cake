//
//  CustomPaymentComplete.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/16.
//

import SwiftUI
import FirebaseDatabase

struct CustomPaymentComplete: View {
    @Environment(\.calendar) var calendar
    @Environment(\.presentationMode) var presentation
    
    @EnvironmentObject var model: DrawingViewModel
    
    //@ObservedObject var imageUploadAPI : ImageUploadAPI
    //@ObservedObject var selectedImageUploadAPI : ImageUploadAPI
    
    @State private var selectedDate = Date()
    @State var letteringText: String = " "
    
    @State private var showingAlert = false
    @State private var paymentComplete = false
    @State var modifying: Bool
    @State var accepted: Bool
    @State var cakeOrderSheetId: Int?
    @State var price: String
    
    @Binding var customCakeInfoKey: String
    
    @ObservedObject var storedata: StoreDataAPI
    var selectedOption1: Option
    var selectedOption2: Option
    var selectedOption3: Option
    var cakeDesignImage: String
    var selectedImages: Array<Image>
    var text: String
    //var key: String = ""
    
    var dateFormatter: DateFormatter {
            let formatter = DateFormatter()
            formatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
            //formatter.dateStyle = .long
            return formatter
        }
    
    var textDateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm"
        //formatter.dateStyle = .long
        return formatter
    }
    
    var body: some View {
            VStack {
                //Spacer()
                
                HStack {
                    // Image
                    /*
                    AsyncImage(url: URL(string: storedata.thumbnailImageUrl))  { image in
                        image
                            .resizable()
                            .scaledToFill()
                    } placeholder: {
                        
                    }
                     */
                    Image(uiImage: UIImage(data: Data(base64Encoded: cakeDesignImage,
                                                      options: Data.Base64DecodingOptions.ignoreUnknownCharacters)!)!) 
                        .resizable()
                        .scaledToFill()
                        .frame(width: 100, height: 100)
                        //.background(Circle().fill(Color.gray))
                        .cornerRadius(10)
                        .clipShape(Circle())
                        .padding(.trailing, 5)
                    
                    VStack(alignment: .leading, spacing: 10) {

                        Text(storedata.name)
                            .font(.title3)
                            .fontWeight(.bold)
                            .lineLimit(1)
                            .foregroundColor(Color.black)
  
                        Text(" ")
                            .font(.system(size: 15))
                            .lineLimit(1)
                            .foregroundColor(Color.black)

                        Text(model.option1Value)
                        Text(model.option2Value)
                        Text(model.option3Value)

                        Text("☝🏻" + model.specialNote)
                            .font(.system(size: 15))
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                        
                        /*
                        
                        Text(String(totalPrice) + " 원")
                            .font(.title3)
                            .lineLimit(1)
                            .foregroundColor(Color.black)
                         */
                    }
                    
                    Spacer()
                    
                }
                .frame(width: 330, height: 180)
                .padding()
                .background(Rectangle().fill(Color.white))
                .cornerRadius(10)
                .shadow(color: .gray, radius: 3, x: 2, y: 2)
                
                Spacer()
                
                Divider()
                
                VStack {
                    Text("픽업 날짜 검색")
                        .font(.title)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    DatePicker(selection: $model.selectedDate, in: Date()... /*, displayedComponents: .date*/) {
                        Text("픽업 날짜를 선택해 주세요")
                        
                        
                    }
                    
                    Spacer()
                    
                    Text(model.selectedDate, formatter: textDateFormatter)
                }
                .frame(height: 150)
                .padding(10)
                
                Divider()
                
                VStack {
                    Text("레터링 글자")
                        .font(.title)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    TextField(
                        "레터링글자",
                        text: $model.letteringText
                    )
                    .cornerRadius(5)
                    .textFieldStyle(.roundedBorder)
                }
                .frame(height: 150)
                .padding(10)
                
                Divider()
                
                Spacer()
                
                //if paymentComplete == false {
                if modifying == false {
                    Button {
                        //model.asyncImageData = imageUploadAPI.urlImage
                        
                        //model.asyncSelectedImageData = selectedImageUploadAPI.urlImage

                        //Task {
                            
                        /*model.asyncImageData = *//*await*/ DesignImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: model.imageDrawData, storeId: model.storeId, userId: model.userId, completionHandler: {(s1: String) -> Void in model.asyncImageData = s1}/*, fileName = designImage*/)
                            
                        for data in model.selectedImageData {
                                //var currentImage : String = ""
                                //print(data)
                                //print(type(of: data))
                            /*currentImage =*/ /*await*/ ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: data, storeId: model.storeId, userId: model.userId, completionHandler: {(s1: String) -> Void in model.asyncSelectedImageData.append(s1)})
                                //print(currentImage)
                                //model.asyncSelectedImageData.append(currentImage)
                                print(model.asyncSelectedImageData)
                            }
                        //}
                        
                        /*
                        for data in model.selectedImageData {
                            Task {
                                //for data in model.selectedImageData {
                                var currentImage : String = ""
                                print(data)
                                print(type(of: data))
                                currentImage = await ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: data)
                                model.asyncSelectedImageData.append(currentImage)
                                //}
                            }
                        }
                         */
                        
                        sendCustomCakeInfo(customcakeInfo: CustomCakeInfo(userId: 2, storeId: storedata.id, option1Id: selectedOption1.id, option1Value: selectedOption1.value, option2Id: selectedOption2.id, option2Value: selectedOption2.value, option3Id: selectedOption3.id, option3Value: selectedOption3.value, designImage: model.asyncImageData /*"https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg"*//*cakeDesignImage*/, additionalImageList: model.asyncSelectedImageData /*["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"]*/ /*selectedImages*/, requirements: text + "\n레터링 글자: " + model.letteringText, pickupDatetime: dateFormatter.string(from: model.selectedDate)), currOperatorId: storedata.id, roomId: 2)
                        self.showingAlert.toggle()
                    } label : {
                        Text("\(price) +α 원 디자인 케이크 주문서 전송하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 350)
                    .background(Color.black)
                    .cornerRadius(10)
                    .padding(10)
                    //.disabled(letteringText.trimmingCharacters(in: .whitespaces) == "")
                    .alert("주문 진행하시겠습니까?", isPresented: $showingAlert) {
                        NavigationLink (
                            destination: Chat(customCakeInfoKey: customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: cakeDesignImage, selectedImages: selectedImages, text: text)
                                .environmentObject(model)
                        ) {
                            Text("확인")
                        }
                    }
                }
                else if accepted == false {
                    Button {
                        
                        model.asyncSelectedImageData = []
                        //model.asyncImageData = imageUploadAPI.urlImage
                        //model.sendAndGetImage()
                        //model.sendAndGetAdditionalImage()
                        
                        //Task {
                            
                            /*model.asyncImageData = await*/ DesignImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: model.imageDrawData, storeId: model.storeId, userId: model.userId, completionHandler: {(s1: String) -> Void in model.asyncImageData = s1}/*, fileName = designImage*/)
                            
                        for data in model.selectedImageData {
                                //var currentImage : String = ""
                                //print(data)
                                //print(type(of: data))
                            /*currentImage =*/ /*await*/ ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: data, storeId: model.storeId, userId: model.userId, completionHandler: {(s1: String) -> Void in model.asyncSelectedImageData.append(s1)})
                                //print(currentImage)
                                //model.asyncSelectedImageData.append(currentImage)
                                print(model.asyncSelectedImageData)
                            }
                        //}
                        
                        /*
                        Task {
                            for data in model.selectedImageData {
                                var currentImage : String = ""
                                print(data)
                                print(type(of: data))
                                currentImage = await ImageUploadAPI(imageType: "CUSTOM_CAKE", imageData: data)
                                model.asyncSelectedImageData.append(currentImage)
                            }
                        }
                        */
                        resendCustomCakeInfo(customcakeInfo: CustomCakeInfo(userId: 2, storeId: storedata.id, option1Id: selectedOption1.id, option1Value: selectedOption1.value, option2Id: selectedOption2.id, option2Value: selectedOption2.value, option3Id: selectedOption3.id, option3Value: selectedOption3.value, designImage: model.asyncImageData /*"https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg"*//*cakeDesignImage*/, additionalImageList: model.asyncSelectedImageData /*["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"]*/ /*selectedImages*/, requirements: text + "\n레터링 글자: " + model.letteringText, pickupDatetime: dateFormatter.string(from:model.selectedDate)), customCakeInfoKey: model.customCakeInfoKey, currOperatorId: storedata.id, roomId: 2)
                        self.showingAlert.toggle()
                    } label : {
                        Text("\(price) +α 원 디자인 케이크 주문서 전송하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 350)
                    .background(Color.black)
                    .cornerRadius(10)
                    .padding(10)
                    //.disabled(letteringText.trimmingCharacters(in: .whitespaces) == "")
                    .alert("주문 진행하시겠습니까?", isPresented: $showingAlert) {
                        NavigationLink (
                            destination: Chat(customCakeInfoKey: customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: cakeDesignImage, selectedImages: selectedImages, text: text)
                                .environmentObject(model)
                        ) {
                            Text("확인")
                        }
                    }
                } else if accepted == true {
                    Button {
                        /*
                        sendCustomCakeInfo(customcakeInfo: CustomCakeInfo(userId: String(2), storeId: String(storedata.id), option1Id: String(selectedOption1.id), option1Value: selectedOption1.value, option2Id: String(selectedOption2.id), option2Value: selectedOption2.value, option3Id: String(selectedOption3.id), option3Value: selectedOption3.value, designImage: "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg"/*cakeDesignImage*/, additionalImageList: ["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"] /*selectedImages*/, requirements: text + "\n레터링 글자: " + model.letteringText, pickupDatetime: dateFormatter.string(from: model.selectedDate)), currOperatorId: storedata.id, roomId: 2)
                         */
                        Task {
                            await PayAPI(payData: PayData(cakeCustomSheetId: cakeOrderSheetId!))
                        }
                        self.showingAlert.toggle()
                    } label : {
                        Text("\(price)원 디자인 케이크 결제하기")
                            .foregroundColor(Color.white)
                    }
                    .frame(height: 50)
                    .frame(width: 350)
                    .background(Color.black)
                    .cornerRadius(10)
                    .padding(10)
                    //.disabled(letteringText.trimmingCharacters(in: .whitespaces) == "")
                    .alert("채팅 화면으로 돌아갑니다.", isPresented: $showingAlert) {
                        NavigationLink (
                            destination: Chat(customCakeInfoKey: customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3, cakeDesignImage: cakeDesignImage, selectedImages: selectedImages, text: text)
                                .environmentObject(model)
                        ) {
                            Text("확인")
                        }
                    }
                }
                
            }
            .padding(10)
    }

    
    func sendCustomCakeInfo(customcakeInfo: CustomCakeInfo, currOperatorId: Int, roomId: Int) {
        let database = Database.database().reference()
        
        let customOrderSheetRefKey = database.child("CustomCakeInfos").childByAutoId().key
        let messageRefKey = database.child("Messages").child("ROOM-\(roomId)").childByAutoId().key
        let timestamp = ServerValue.timestamp() as NSDictionary
        //let customOrderInfoRef = database.child("CustomCakeInfos")
        let memberRoomRef = database.child("MemberRooms").child("OPERATOR-\(currOperatorId)").child("ROOM-\(roomId)")
        
               
        /*customOrderInfoRef*/memberRoomRef.observeSingleEvent(of: .value) { snapshot in
            if snapshot.exists(), let val = snapshot.value as? [String: Any] {
                var multiUpdates: [String: Any] = [:]
                
                let customOrderSheetData: [String: Any] = [
                    "userId": model.userId, //as? String ?? "",
                    "storeId": model.storeId, //as? String ?? "",
                    "option1Id": model.option1Id, //as? String ?? "",
                    "option1Value": model.option1Value ?? "",
                    "option2Id": model.option2Id, //as? String ?? "",
                    "option2Value": model.option2Value ?? "",
                    "option3Id": model.option3Id, //as? String ?? "",
                    "option3Value": model.option3Value ?? "",
                    "designImage": /*"https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-01-09.png"*/ model.asyncImageData ?? "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg",
                    "additionalImageList": model.asyncSelectedImageData /*["https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-02-30.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-02-31.png"]*/ ?? ["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"],
                    "requirements": customcakeInfo.requirements ?? "Happy Birthday❤️ 로 부탁드려요!",
                    "pickupDatetime": customcakeInfo.pickupDatetime ?? "2023-05-19 15:00:00"
                ]
                
                multiUpdates["CustomCakeInfos/\(customOrderSheetRefKey!)"] = customOrderSheetData
                
                let messageData: [String: Any] = [
                    "memberUid": "USER-\(roomId)",
                    "sender": val["roomUserName"] as? String ?? "",
                    "senderType": "USER",
                    "message": "주문서가 전송되었습니다.",
                    "timestamp": timestamp
                ]
                
                multiUpdates["Messages/ROOM-\(roomId)/\(messageRefKey!)"] = messageData

                let memberRoomData: [String: Any] = [
                    "chatStatus": "IN_PROGRESS",
                    "lastMessage": "주문서가 전송되었습니다.",
                    "roomOperatorId": val["roomOperatorId"], //as? String ?? "",
                    "roomOperatorName": val["roomOperatorName"] as? String ?? "",
                    "roomUserId": val["roomUserId"], //as? String ?? "",
                    "roomUserName": val["roomUserName"] as? String ?? "",
                    "customCakeInfoId": customOrderSheetRefKey as? String ?? "",
                    "timestamp": timestamp
                ]
                
                multiUpdates["MemberRooms/OPERATOR-\(currOperatorId)/ROOM-\(roomId)"] = memberRoomData

                database.updateChildValues(multiUpdates)
                customCakeInfoKey = customOrderSheetRefKey!
                model.customCakeInfoKey = customOrderSheetRefKey!
                //print(customCakeInfoKey)
                
            } else {
                
                print("Failed to upload customOrder data")
                
            }
        }
    }
    
    func resendCustomCakeInfo(customcakeInfo: CustomCakeInfo, customCakeInfoKey: String, currOperatorId: Int, roomId: Int) {
        let database = Database.database().reference()
        
        //let customOrderSheetRefKey = customCakeInfoKey//database.child("CustomCakeInfos").childByAutoId().key
        let messageRefKey = database.child("Messages").child("ROOM-\(roomId)").childByAutoId().key
        let timestamp = ServerValue.timestamp() as NSDictionary
        //let customOrderInfoRef = database.child("CustomCakeInfos").child(customCakeInfoKey)
        let memberRoomRef = database.child("MemberRooms").child("OPERATOR-\(currOperatorId)").child("ROOM-\(roomId)")
               
        /*customOrderInfoRef*/memberRoomRef.observeSingleEvent(of: .value) { snapshot in
            if snapshot.exists(), let val = snapshot.value as? [String: Any] {
                var multiUpdates: [String: Any] = [:]
                
                let customOrderSheetData: [String: Any] = [
                    "userId": model.userId, //as? String ?? "",
                    "storeId": model.storeId, //as? String ?? "",
                    "option1Id": model.option1Id, //as? String ?? "",
                    "option1Value": model.option1Value ?? "",
                    "option2Id": model.option2Id, //as? String ?? "",
                    "option2Value": model.option2Value ?? "",
                    "option3Id": model.option3Id, //as? String ?? "",
                    "option3Value": model.option3Value ?? "",
                    "designImage": /*"https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-01-09.png"*/model.asyncImageData ?? "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_2.jpeg",
                    "additionalImageList": model.asyncSelectedImageData /*["https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-02-30.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-custom-image/store_1/user_2/KakaoTalk_Photo_2023-05-19-13-02-31.png"]*/ ?? ["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg", "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_3.jpeg"],
                    "requirements": customcakeInfo.requirements ?? "Happy Birthday❤️ 로 부탁드려요!",
                    "pickupDatetime": customcakeInfo.pickupDatetime ?? "2023-05-19 15:00:00"
                ]
                
                multiUpdates["CustomCakeInfos/" + customCakeInfoKey] = customOrderSheetData
                
                let messageData: [String: Any] = [
                    "memberUid": "USER-\(roomId)",
                    "sender": val["roomUserName"] as? String ?? "",
                    "senderType": "USER",
                    "message": "수정한 주문서가 재전송되었습니다.",
                    "timestamp": timestamp
                ]
                
                multiUpdates["Messages/ROOM-\(roomId)/\(messageRefKey!)"] = messageData

                let memberRoomData: [String: Any] = [
                    "chatStatus": "IN_PROGRESS",
                    "lastMessage": "수정한 주문서가 재전송되었습니다.",
                    "roomOperatorId": val["roomOperatorId"], // as? String ?? "",
                    "roomOperatorName": val["roomOperatorName"] as? String ?? "",
                    "roomUserId": val["roomUserId"], //as? String ?? "",
                    "roomUserName": val["roomUserName"] as? String ?? "",
                    "customCakeInfoId": customCakeInfoKey as? String ?? "",
                    "timestamp": timestamp
                ]
                
                multiUpdates["MemberRooms/OPERATOR-\(currOperatorId)/ROOM-\(roomId)"] = memberRoomData
                
                database.updateChildValues(multiUpdates)
                
            } else {
                
                print("Failed to upload customOrder data")
                
            }
        }
    }
}

func convertImageToBase64(image: UIImage) -> String {
        let imageData = image.pngData()!
        return imageData.base64EncodedString(options: Data.Base64EncodingOptions.lineLength64Characters)
}

/*
 struct CustomPaymentComplete_Previews: PreviewProvider {
 static var previews: some View {
 CustomPaymentComplete()
 }
 }
 */
