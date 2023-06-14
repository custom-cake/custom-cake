//
//  Map.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/29.
//

import SwiftUI
import MapKit

struct Minimap: View {
    
    @ObservedObject var searchDataAPI: SearchDataAPI
    @State var myPosition = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 37.331705, longitude: -122.030237), span : MKCoordinateSpan())
    //@State var coordinate = CLLocationCoordinate2D(latitude: 37.4856085, longitude: 126.972290)
    //@State var markerCoordinate: CLLocationCoordinate2D//= CLLocationCoordinate2D(latitude: 37.7749, longitude: -122.4194) // 마커 위치
    //@State var centerCoordinate: CLLocationCoordinate2D //= CLLocationCoordinate2D(latitude: 37.4856085, longitude: 126.972290) // 지도의 중심 위치

    var body: some View {
            //MapView(markerCoordinate: markerCoordinate, centerCoordinate: centerCoordinate)
        MapView(searchDataAPI: searchDataAPI/*markerCoordinate: /*markerCoordinate*/CLLocationCoordinate2D(latitude: Double(searchDataAPI.y)!, longitude: Double(searchDataAPI.x)!), centerCoordinate: CLLocationCoordinate2D(latitude: Double(searchDataAPI.y)!, longitude: Double(searchDataAPI.x)!) /*markerCoordinate*/*/)
            //.environmentObject(searchDataAPI)
    }
}

/*
struct Minimap_Previews: PreviewProvider {
    static var previews: some View {
        Minimap()
    }
}
*/
