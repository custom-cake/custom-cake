//
//  WebView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/04.
//

import SwiftUI
import WebKit
import Combine
import UIKit

protocol WebViewHandlerDelegate {
    func receivedJsonValueFromWebView(value: [String: Any?])
}

struct WebView: UIViewRepresentable {

    var url: String
    
    func makeUIView(context: Context) -> WKWebView {
        guard let url = URL(string: self.url) else {
            return WKWebView()
        }
        
        let webView = WKWebView()
        webView.load(URLRequest(url: url))
        return webView
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        
    }
}

struct WebView_Previews: PreviewProvider {
    static var previews: some View {
        WebView(url: "https://swiftie1230.github.io/Kakao-address/")
    }
}
