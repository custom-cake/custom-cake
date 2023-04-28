//
//  mapView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/03/30.
//

import Foundation
import MapKit
import SwiftUI
import CoreLocation

struct MapView : UIViewRepresentable {
    
    let locationManager = CLLocationManager()
    
    
    func makeUIView(context: Context) -> MKMapView {
        
        let mkMapView = MKMapView()
        
        mkMapView.delegate = context.coordinator
        
        self.locationManager.delegate = context.coordinator
        
        self.locationManager.desiredAccuracy = kCLLocationAccuracyBest
        
        self.locationManager.requestWhenInUseAuthorization()
        
        self.locationManager.startUpdatingLocation()
        
        mkMapView.showsUserLocation = true
        mkMapView.setUserTrackingMode(.follow, animated: true)
        
        let regionRadius : CLLocationDistance = 200
        
        let coordinateRegion = MKCoordinateRegion(center: mkMapView.userLocation.coordinate, latitudinalMeters: regionRadius, longitudinalMeters: regionRadius)
        
        mkMapView.setRegion(coordinateRegion, animated: true)
        
        return mkMapView
    }
    
    func updateUIView(_ uiView: MKMapView, context: Context) {
        
    }
    
    func makeCoordinator() -> MapView.Coordinator {
        return MapView.Coordinator(self)
    }
    
    class Coordinator : NSObject {
        
        var mapView: MapView
        
        init(_ mapView: MapView) {
            self.mapView = mapView
        }
    }
}


extension MapView.Coordinator : MKMapViewDelegate {
    
}

extension MapView.Coordinator : CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let lat = locations.first?.coordinate.latitude,
              let long = locations.first?.coordinate.longitude else {
            return
        }
    }
}
