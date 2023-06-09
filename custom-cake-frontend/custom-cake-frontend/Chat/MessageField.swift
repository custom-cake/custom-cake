//
//  MessageField.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/16.
//

import SwiftUI
import FirebaseDatabase

struct MessageField: View {
    //@EnvironmentObject var messagesManager: MessagesManager
    
    @EnvironmentObject var model: DrawingViewModel
    @ObservedObject var customCakeOrderAcceptAPI = CustomCakeOrderAcceptAPI(storeId: 1, userId: 2)
    
    @State private var message = ""
    
    var customCakeInfoKey: String
    @ObservedObject var storedata: StoreDataAPI
    var selectedOption1: Option
    var selectedOption2: Option
    var selectedOption3: Option
    //var cakeDesignImage: Image
    //var selectedImages: Array<Image>
    //var text: String

    var body: some View {
            HStack {
                // Custom text field created below
                CustomTextField(placeholder: Text("Enter your message here"), text: $message)
                    .frame(height: 52)
                    .disableAutocorrection(true)

                Button {
                    //messagesManager.sendMessage(text: message)
                    sendMessage(message: message, roomId: 2, currOperatorId: storedata.id)
                    message = ""
                } label: {
                    Image(systemName: "paperplane.fill")
                        .foregroundColor(.white)
                        .padding(10)
                        .background(Color("Peach"))
                        .cornerRadius(50)
                }
                
                NavigationLink (
                    destination: CustomOrderDesign(modifying: true, accepted: customCakeOrderAcceptAPI.approve, price: customCakeOrderAcceptAPI.price ?? "\(selectedOption1.price + selectedOption2.price + selectedOption3.price)", cakeOrderSheetId: customCakeOrderAcceptAPI.id, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3)
                        .environmentObject(model)
                ) {
                    Image(systemName: "doc.fill")
                        .foregroundColor(.white)
                        .padding(10)
                        .background(Color("Peach"))
                        .cornerRadius(10)
                }
            }
            .padding(.horizontal)
            .padding(.vertical, 10)
            .background(Color("defaultgrayColor"))
            .cornerRadius(20)
            .padding()
    }
    
    func sendMessage(message: String, roomId: Int, currOperatorId: Int) {
        let database = Database.database().reference()
        
        let messageRefKey = database.child("Messages").child("ROOM-\(roomId)").childByAutoId().key
        let timestamp = ServerValue.timestamp() as NSDictionary
        let memberRoomRef = database.child("MemberRooms").child("OPERATOR-\(currOperatorId)").child("ROOM-\(roomId)")
        
               
        memberRoomRef.observeSingleEvent(of: .value) { snapshot in
            if snapshot.exists(), let val = snapshot.value as? [String: Any] {
                var multiUpdates: [String: Any] = [:]
                
                let messageData: [String: Any] = [
                    "memberUid": "USER-\(roomId)",
                    "sender": val["roomUserName"] as? String ?? "",
                    "senderType": "USER",
                    "message": message,
                    "timestamp": timestamp
                ]
                
                multiUpdates["Messages/ROOM-\(roomId)/\(messageRefKey!)"] = messageData
                
                let memberRoomData: [String: Any] = [
                    "chatStatus": "IN_PROGRESS",
                    "lastMessage": message,
                    "roomOperatorId": currOperatorId, //as? String ?? "",
                    "roomOperatorName": val["roomOperatorName"] as? String ?? "",
                    "roomUserId": roomId, //as? String ?? "",
                    "roomUserName": val["roomUserName"] as? String ?? "",
                    "customCakeInfoId": customCakeInfoKey as? String ?? "",
                    "timestamp": timestamp
                ]
                
                multiUpdates["MemberRooms/OPERATOR-\(currOperatorId)/ROOM-\(roomId)"] = memberRoomData
                
                database.updateChildValues(multiUpdates)
                
            } else {
                
                print("Failed to load chat room data")
                
            }
        }
    }
}

/*
struct MessageField_Previews: PreviewProvider {
    static var previews: some View {
        MessageField()
            //.environmentObject(MessagesManager())
    }
}
 */

struct CustomTextField: View {
    var placeholder: Text
    @Binding var text: String
    var editingChanged: (Bool)->() = { _ in }
    var commit: ()->() = { }

    var body: some View {
        ZStack(alignment: .leading) {
            // If text is empty, show the placeholder on top of the TextField
            if text.isEmpty {
                placeholder
                .opacity(0.5)
            }
            TextField("", text: $text, onEditingChanged: editingChanged, onCommit: commit)
        }
    }
}

