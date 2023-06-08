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
    
    @ObservedObject var searchDataAPI: SearchDataAPI
    
    let locationManager = CLLocationManager()
    
    //let markerCoordinate: CLLocationCoordinate2D // 마커의 위치
    //let centerCoordinate: CLLocationCoordinate2D // 지도의 중심 위치
    
    /*
    init(markerCoordinate: CLLocationCoordinate2D, centerCoordinate: CLLocationCoordinate2D) {

        self.markerCoordinate = markerCoordinate
        self.centerCoordinate = centerCoordinate

    }
     */
    
    func makeUIView(context: Context) -> MKMapView {
        /*
        self.markerCoordinate = /*markerCoordinate*/CLLocationCoordinate2D(latitude: Double(searchDataAPI.y)!, longitude: Double(searchDataAPI.x)!)//markerCoordinate = markerCoordinate
        self.centerCoordinate = CLLocationCoordinate2D(latitude: Double(searchDataAPI.y)!, longitude: Double(searchDataAPI.x)!) //centerCoordinate = centerCoordinate
        */
        let mkMapView = MKMapView()
        
        mkMapView.delegate = context.coordinator
        
        self.locationManager.delegate = context.coordinator
        
        self.locationManager.desiredAccuracy = kCLLocationAccuracyBest
        
        self.locationManager.requestWhenInUseAuthorization()
        
        self.locationManager.startUpdatingLocation()
        
        mkMapView.showsUserLocation = true
        mkMapView.setUserTrackingMode(.follow, animated: true)
        
        let regionRadius : CLLocationDistance = 200
        
        let coordinateRegion = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: Double(searchDataAPI.searchDatas[0].y)!, longitude: Double(searchDataAPI.searchDatas[0].x)!)/*self.centerCoordinate*//*center: mkMapView.userLocation.coordinate*/, latitudinalMeters: regionRadius, longitudinalMeters: regionRadius)
        
        mkMapView.setRegion(coordinateRegion, animated: true)
        
        // 마커 추가
        let annotation = MKPointAnnotation()
        annotation.coordinate = CLLocationCoordinate2D(latitude: Double(searchDataAPI.searchDatas[0].y)!, longitude: Double(searchDataAPI.searchDatas[0].x)!)//self.markerCoordinate
        mkMapView.addAnnotation(annotation)
        
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
