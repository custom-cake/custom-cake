//
//  ImageUploadAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/19.
//

import Foundation
import Alamofire

func ImageUploadAPI(imageType: String, imageData: Data, storeId: Int, userId: Int, completionHandler: @escaping (String) -> Void) {
    guard let url = URL(string: "http://43.201.13.139:8080//api/image/custom/\(storeId)/\(userId)") else {
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
