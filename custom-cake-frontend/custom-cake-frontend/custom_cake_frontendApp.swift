//
//  custom_cake_frontendApp.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/21.
//

import SwiftUI

import KakaoSDKCommon
import KakaoSDKAuth

import NaverThirdPartyLogin
import Firebase
import FirebaseCore

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        return true
    }
}

@main
struct custom_cake_frontendApp: App {
    
    // register app delegate for Firebase setup
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        
        // Firebase
                //FirebaseApp.configure()
        
        // Kakao SDK 초기화
                KakaoSDK.initSDK(appKey: "kakaoe3cff36cf0a5ff8a68dc23709267b740")
        
        // Naver SDK Initializing
        
                // 네이버 앱으로 로그인 허용
                NaverThirdPartyLoginConnection.getSharedInstance()?.isNaverAppOauthEnable = true
                // 브라우저 로그인 허용
                NaverThirdPartyLoginConnection.getSharedInstance()?.isInAppOauthEnable = true
         
                // 네이버 로그인 세로모드 고정
                NaverThirdPartyLoginConnection.getSharedInstance().setOnlyPortraitSupportInIphone(true)
                
                // NaverThirdPartyConstantsForApp.h에 선언한 상수 등록
                NaverThirdPartyLoginConnection.getSharedInstance().serviceUrlScheme = kServiceAppUrlScheme
                NaverThirdPartyLoginConnection.getSharedInstance().consumerKey = kConsumerKey
                NaverThirdPartyLoginConnection.getSharedInstance().consumerSecret = kConsumerSecret
                NaverThirdPartyLoginConnection.getSharedInstance().appName = kServiceAppName
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView().onOpenURL { url in
                if (AuthApi.isKakaoTalkLoginUrl(url)) {
                    _ = AuthController.handleOpenUrl(url: url)
                }
                
                else
                {
                    // Token 발급 요청
                    NaverThirdPartyLoginConnection
                        .getSharedInstance()
                        .receiveAccessToken(url)
                }
            }
        }
    }
}
