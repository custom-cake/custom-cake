//
//  ImageUploadAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/18.
//

import Foundation
import Alamofire


func DesignImageUploadAPI(imageType: String, imageData: Data, storeId: Int, userId: Int, completionHandler: @escaping (String) -> Void) {
    guard let url = URL(string: "http://43.201.13.139:8080/api/image/custom/\(storeId)/\(userId)") else {
        print("ERROR from URL")
        return
    }
    
    let header: HTTPHeaders = ["Content-Type": "multipart/form-data"]
    
    AF.upload(multipartFormData: { multipartFormData in
        multipartFormData.append(Data(imageType.utf8 ?? "".utf8), withName: "type")
        
        multipartFormData.append(imageData ?? Data(), withName: "image", fileName: "image.png", mimeType: "image/png")
    }, to: url, method: .post, headers: header)
    .responseData { response in
        guard let statusCode = response.response?.statusCode else { return }
        
        switch statusCode {
        case 200:
            print("이미지 등록 성공")
            if let data = response.data, let stringData = String(data: data, encoding: .utf8) {
                //return stringData
                completionHandler(stringData)
                //return result
                
            }
        default:
            print("이미지 등록 실패 : statusCode \(statusCode)")
        }
    }
}

/*
func ImageUploadAPI(imageType: String, imageData: Data) async -> String {
    var imageUrl = ""
    
    guard let url = URL(string: "http://43.201.13.139:8080/image/upload") else {
        return "error"
    }
    
    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    
    let boundary = UUID().uuidString
    let lineBreak = "\r\n"
    
    let contentType = "multipart/form-data; boundary=\(boundary)"
    request.setValue(contentType, forHTTPHeaderField: "Content-Type")
    
    var body = Data()
    
    let imageTypeField = "type"
    let imageTypeString = imageType
    body.append(Data("--\(boundary + lineBreak)".utf8))
    body.append(Data("Content-Disposition: form-data; name=\"\(imageTypeField)\"\(lineBreak + lineBreak)".utf8))
    body.append(Data("\(imageTypeString + lineBreak)".utf8))
    
    let imageDataField = "image"
    let imageData = imageData
    body.append(Data("--\(boundary + lineBreak)".utf8))
    body.append(Data("Content-Disposition: form-data; name=\"\(imageDataField)\"; filename=\"image.png\"\(lineBreak)".utf8))
    body.append(Data("Content-Type: image/jpeg\(lineBreak + lineBreak)".utf8))
    body.append(imageData)
    body.append(Data("\(lineBreak)".utf8))
    
    body.append(Data("--\(boundary)--\(lineBreak)".utf8))
    request.httpBody = body
    
    do {
        let (data, response) = try await URLSession.shared.data(for: request)
        if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode != 200 {
            // 200이 아닐 경우 문제 발생할 수도 있다.
            print("Request failed with status code: \(httpResponse.statusCode)")
            imageUrl = "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_image_1.jpeg"
        } else {
            print("upload success")
            if let stringData = String(data: data, encoding: .utf8) {
                imageUrl = stringData
            }
        }
    } catch {
        print("Checkout failed.")
    }
    
    return imageUrl
}

*/
