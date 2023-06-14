//
//  Message.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/16.
//

import Foundation
import FirebaseDatabase

struct Message: Codable, Hashable {
    /*
    var id: String
    var text: String
    var received: Bool
    var timestamp: Date
     */
    let sender: String
    let message: String
    let senderType: String
    let timestamp: TimeInterval
    //let timestamp: Date
}


final class MessageViewModel: ObservableObject {
    
    @Published var messages: [Message] = []
    @Published var lastMessage: Message = Message(sender: "황서진", message: "주문서가 전송되었습니다.", senderType: "USER", timestamp: 1684366094167)

    lazy var databasePath: DatabaseReference? = Database.database().reference()
    
    init(userId: Int) {
        /*private*/ databasePath? = {
            let ref = Database.database().reference().child("Messages").child("ROOM-\(userId)")
            return ref
        }()
        
    }
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    /*
    func listentoRealtimeDatabase() {
            guard let databasePath = databasePath else {
                return
            }
            databasePath
            .observe(.childAdded) { [weak self] snapshot  in
                    guard
                        let self = self,
                        var json = snapshot.value as? [String: Any]
                    else {
                        return
                    }
                    json["userId"] = snapshot.key
                    do {
                        let customCakeInfoData = try JSONSerialization.data(withJSONObject: json)
                        let bird = try self.decoder.decode(CustomCakeInfo.self, from: customCakeInfoData)
                        self.customCakeInfos.append(bird)
                    } catch {
                        print("an error occurred", error)
                    }
                }
        }
        
        func stopListening() {
            databasePath?.removeAllObservers()
        }
    */
    
    func loadMessages() {
        //let ref = Database.database().reference().child("Messages").child("ROOM-\(userId)")
        let query = databasePath!.queryOrdered(byChild: "timestamp").queryLimited(toLast: 50)
        
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
            
            self.messages.append(message)
            
            if let lastmessage = self.messages.last {
                self.lastMessage = lastmessage
            }
        }
    }
    
    func stoploadingMessages() {
        self.messages = []
        databasePath!.removeAllObservers()
    }
    
    /*
    func returnLastMessage() -> Message {
        return self.messages[-1]
    }
     */
    
}

