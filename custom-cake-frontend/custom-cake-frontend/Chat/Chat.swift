//
//  Chat.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/16.
//

import SwiftUI
import FirebaseDatabase

struct Chat: View {
    
    @Environment(\.presentationMode) var presentation
    
    @EnvironmentObject var model: DrawingViewModel
    
    
    @State var messages: [Message] = []
    @StateObject var messageviewModel = MessageViewModel(userId: 2)
    
    var customCakeInfoKey: String
    @ObservedObject var storedata: StoreDataAPI
    var selectedOption1: Option
    var selectedOption2: Option
    var selectedOption3: Option
    var cakeDesignImage: String
    var selectedImages: Array<Image>
    var text: String
    
    var messageArray = ["Hello you", "How are you doing?", "I've been building SwiftUI applications from scratch and it's so much fun!"]
    
    var body: some View {
        VStack {
            VStack {
                TitleRow()
                
                ScrollViewReader { proxy in
                    ScrollView {
                        ForEach(messageviewModel.messages/*messages*/, id: \.self) { message in
                            MessageBubble(message: message /*Message(id: "12345", text: text, received: true, timestamp: Date())*/)
                        }
                    }.onAppear {
                        messageviewModel.loadMessages()
                    }
                    .onDisappear {
                        messageviewModel.stoploadingMessages()
                    }
                    .padding(.top, 10)
                    .background(.white)
                    .onChange(of: messageviewModel.lastMessage) { message in
                        withAnimation {
                            proxy.scrollTo(message, anchor: .bottom)
                        }
                        
                    }
                }

            }
            .background(Color("Peach"))
            
            MessageField(customCakeInfoKey: model.customCakeInfoKey, storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3 /*cakeDesignImage: cakeDesignImage, selectedImages: selectedImages text: text*/)
                .environmentObject(model)
        }
    }
    
/*
    func loadMessages(userId: Int) {
        let ref = Database.database().reference().child("Messages").child("ROOM-\(userId)")
        let query = ref.queryOrdered(byChild: "timestamp").queryLimited(toLast: 50)
        
        var loadedMessages: [Message] = []
        
        query.observe(/*.value*/.childAdded) { snapshot in
            
            /*
            if let data = snapshot.value as? [String: Any]
            {
                
                var loadedMessages: [Message] = []
                
                for (_, value) in data {
                    
                    if let messageData = value as? [String: Any],
                       let sender = messageData["sender"] as? String,
                       let message = messageData["message"] as? String,
                       let senderType = messageData["senderType"] as? String {
                        let message = Message(sender: sender, message: message, senderType: senderType)
                        loadedMessages.append(message)
                    }
                }
                
                messages = loadedMessages
                
            } else {
                
                print("Failed to load chat messages")
            }*/
            guard let value = snapshot.value as? [String: Any],
                  let messageData = try? JSONSerialization.data(withJSONObject: value),
                  let message = try? JSONDecoder().decode(Message.self, from: messageData) else {
                    print("Failed to fetch message")
                    return
                    }
            
            loadedMessages.append(message)
        }
        
        messages = loadedMessages
    }
    
    func stoploadingMessages(userId: Int) {
        let ref = Database.database().reference().child("Messages").child("ROOM-\(userId)")
        let query = ref.queryOrdered(byChild: "timestamp").queryLimited(toLast: 50)
        
        query.removeAllObservers()
    }
 */

}

/*
 struct Chat_Previews: PreviewProvider {
 static var previews: some View {
 Chat(storedata: storedata, selectedOption1: selectedOption1, selectedOption2: selectedOption2, selectedOption3: selectedOption3)
 }
 }
 */
