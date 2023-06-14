//
//  NaverButton.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/27.
//

import SwiftUI
import NaverThirdPartyLogin

struct NaverButton: View {
    var body: some View {
        Button (action: {
            if NaverThirdPartyLoginConnection
                .getSharedInstance()
                .isPossibleToOpenNaverApp() // Naver App이 깔려있는지 확인하는 함수
            {
                /*NaverThirdPartyLoginConnection.getSharedInstance().delegate = viewModel.self*/
                NaverThirdPartyLoginConnection
                    .getSharedInstance()
                    .requestThirdPartyLogin()
            } else { // 네이버 앱 안깔려져 있을때
                // Appstore에서 네이버앱 열기
                NaverThirdPartyLoginConnection.getSharedInstance().openAppStoreForNaverApp()

            }
        }, label : {
            HStack{
                Text("")
                Image("btnG_아이콘사각")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 40, height: 40)
                Text("                 ")
                Text("네이버 로그인")
                    .font(.body)
                    .fontWeight(.bold)
                    .foregroundColor(.white)
                    .frame(height: 52)
                    .frame(width: 100)
                    .background(.green)
                    .cornerRadius(5)
                Text("                          ")
            }
            .frame(height: 52)
            .frame(width: 354)
            .background(Color.green)
            .cornerRadius(5)
        })
    }
}

struct NaverButton_Previews: PreviewProvider {
    static var previews: some View {
        NaverButton()
    }
}
