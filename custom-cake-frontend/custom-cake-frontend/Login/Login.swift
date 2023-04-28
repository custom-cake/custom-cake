//
//  Login.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/27.
//

import SwiftUI

import NaverThirdPartyLogin

struct Login: View {
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 80) {
            Loginlogo()
                .padding(EdgeInsets(top: -50, leading: 0, bottom: 0, trailing: 0))
            
            VStack (alignment: .leading, spacing: 10){
                KakaoButton()
                NaverButton()
            }
        }
    }
}

struct Login_Previews: PreviewProvider {
    static var previews: some View {
        Login()
    }
}
