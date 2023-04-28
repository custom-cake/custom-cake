//
//  Map.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/29.
//

import SwiftUI
import MapKit

struct Minimap: View {
    
    @State var myPosition = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 37.331705, longitude: -122.030237), span : MKCoordinateSpan())

    var body: some View {
            MapView()
    }
}

struct Minimap_Previews: PreviewProvider {
    static var previews: some View {
        Minimap()
    }
}
