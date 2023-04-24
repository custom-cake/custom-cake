//
//  Login.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/27.
//

import SwiftUI
import KakaoSDKUser

struct Loginlogo: View {
    var body: some View {
        VStack(alignment: .leading) {
            Text("LOGIN")
                .font(.largeTitle)
                .fontWeight(.bold)
                //.foregroundColor(Color.purple)
            Text("로그인")
                .font(.body)
        }
    }
}

struct Loginlogo_Previews: PreviewProvider {
    static var previews: some View {
        Loginlogo()
    }
}
