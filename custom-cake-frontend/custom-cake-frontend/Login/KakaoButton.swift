//
//  KakaoButton.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/27.
//

import SwiftUI
import KakaoSDKUser

struct KakaoButton: View {
    var body: some View {
        Button {
            if (UserApi.isKakaoTalkLoginAvailable()) {
            UserApi.shared.loginWithKakaoTalk {(oauthToken, error) in
                print(oauthToken as Any)
                print(error as Any)
                }
            } else {
                UserApi.shared.loginWithKakaoAccount {(oauthToken, error) in
                    print(oauthToken as Any)
                    print(error as Any)
                }
            }
        } label : {
            Image("kakao_login_large_wide")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width : UIScreen.main.bounds.width * 0.9)
        }
    }
}

struct KakaoButton_Previews: PreviewProvider {
    static var previews: some View {
        KakaoButton()
    }
}
