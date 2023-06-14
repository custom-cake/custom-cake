//
//  InfoItem.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/14.
//

import Foundation

struct InfoData: Decodable, Identifiable {
    var id : Int
    var title : String
    var content : String
    var createdAt : String
    var modifiedAt : String
    
    enum CodingKeys: String, CodingKey {
            case id, title, content, createdAt, modifiedAt
        }
}

var sharedInfos = [
    InfoData(id: 1,
             title: "레이네 케이크 공지",
             content: "[4~5월 디자인케이크 클래스]\n\n<총 2주, 매주 월요일 12:00~15:00(최대16:00)>\n* 1차 (4월 10일 / 4월 17일)  <마감임박>\n* 2차 (4월 24일 / 5월 1일)   <마감임박>\n* 3차 (5월 22일 / 5월 29일)\n\n📌 수강대상 : 케이크 샌딩과 아이싱 가능하신 분 or 기존 케이크 매장 영업자\n* 아이싱이 완벽하지 않으시더라도 기본 아이싱이 가능하다는 전제 하에 수업을 진행합니다.\n* 이 수업을 통해 여러분들의 케이크 작업과 디자인이 다양한 스타일로 확대 시킬 수 있는 발판이 되었으면 합니다 ! :)\n\n📌.레이네케이크 유화케이크 클래스는\n  정형화된 케이크 기법이 아닌 다양한 요소와 그림들을 케이크 위에 자유롭게 표현하는 수업입니다.\n  특히 자연물의 형태를 분석하고 그에 맞는 기법을 배워보는 수업입니다.",
             createdAt: "2023-04-12T15:26:45",
             modifiedAt: "2023-04-12T15:26:45")
]
