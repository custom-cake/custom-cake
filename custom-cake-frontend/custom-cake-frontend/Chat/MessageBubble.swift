//
//  MessageBubble.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/16.
//

import SwiftUI
import Firebase

struct MessageBubble: View {
    
    var message: Message
    //@State private var showTime = true
    
    var body: some View {
        VStack(alignment: message.senderType == "OPERATOR" ? .leading : .trailing) {
            HStack {
                Text(message.message)
                    .padding()
                    .background(message.senderType == "OPERATOR" ? Color("defaultgrayColor") : Color("Peach"))
                    .cornerRadius(30)
            }
            .frame(maxWidth: 300, alignment: message.senderType == "OPERATOR" ? .leading : .trailing)

            /*
            .onTapGesture {
                showTime.toggle()
            }
             */


            Text("\(convertTimestampToDate(timestamp: message.timestamp)! .formatted(.dateTime.month().day().hour().minute()))")
                .font(.caption2)
                .foregroundColor(.gray)
                .padding(message.senderType == "OPERATOR" ? .leading : .trailing, 25)


        }
        .frame(maxWidth: .infinity, alignment: message.senderType == "OPERATOR" ? .leading : .trailing)
        .padding(message.senderType == "OPERATOR" ? .leading : .trailing)
        .padding(.horizontal, 10)
    }
    
    private func convertTimestampToDate(timestamp: Any) -> Date? {
            if let seconds = timestamp as? TimeInterval {
                return Date(timeIntervalSince1970: seconds/1000)
            }
            return nil
        }
}

struct MessageBubble_Previews: PreviewProvider {
    static var previews: some View {
        MessageBubble(message: Message(sender: "김영기", message:"넵! 확인 완료했습니다.", senderType: "USER", timestamp: 1682430649000))
    }
}

struct TimestampString {
    static func dateString(_ timestamp: Timestamp) -> String {
        let formatter = DateComponentsFormatter()
        formatter.allowedUnits = [.second, .minute, .hour, .day, .weekOfMonth] // 시간 단위 설정
        formatter.maximumUnitCount = 1 // 시간 단위를 몇개를 나타낼 것인가
        formatter.unitsStyle = .abbreviated // 단위의 가장 앞글자 약어(s, m, h, d, w 등)으로 설정
        
        // 한글로 변환
        var calender = Calendar.current
        calender.locale = Locale(identifier: "ko")
        formatter.calendar = calender
        
        // 만들어진 시간부터 지금(Date())까지 얼마만큼의 시간이 걸렸는지 계산해서 차이(difference)를 반환
        return formatter.string(from: timestamp.dateValue(), to: Date()) ?? ""
    }
}
