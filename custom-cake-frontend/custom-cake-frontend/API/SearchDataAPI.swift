//
//  SearchDataAPI.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/06/08.
//

import Foundation

class SearchDataAPI: ObservableObject {
    @Published var searchDatas: [SearchData] =  [
        SearchData(id: 1, name: "레이네 케이크", x: "126.972290", y: "37.4856085")
    ]
    
    @Published var id: Int = 1
    @Published var name: String = "레이네 케이크"
    @Published var x: String = "126.972290"
    @Published var y: String = "37.4856085"
    //@Published var classifiedOption1 = [Option]()
    //@Published var classifiedOption2 = [Option]()
    //@Published var classifiedOption3 = [Option]()
    
    init(/*cakeId: CakeData.ID*/storeName: String) {
            let query = storeName
            var components = URLComponents()
            components.scheme = "http"
            components.host = "43.201.13.139"
            components.port = 8080
            components.path = "/api/stores/search"
            components.queryItems = [
                    URLQueryItem(name: "query", value: query)
            ]
        
            guard let url = components.url/*URL(string: "http://43.201.13.139:8080/api/stores/search")*/ else {
                return
            }
            
            URLSession.shared.dataTask(with: url) { data, _, _ in
                guard let data = data else {
                    return
                }
                
                do {
                    // 3
                    let result = try JSONDecoder().decode([SearchData].self, from: data)
                    
                    // 4
                    DispatchQueue.main.async {
                        self.searchDatas = result
                        /*
                        self.id = result[0].id
                        self.name = result[0].name
                        self.x = result[0].x
                        self.y = result[0].y
                         */
                        //print(self.searchDatas[0].id, result[0].id)
                    }
                } catch {
                    print("\(error.localizedDescription)\n\(error)")
                }
                
            }.resume()
        }
}
