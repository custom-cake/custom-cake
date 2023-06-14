//
//  CanvasView.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/05/08.
//

import SwiftUI

struct CakeCanvas: View {
    
    @Binding var cakeBgColor : Color
    
    var body: some View {
        Canvas { context, size in
            
            let circleRect = CGRect(x:size.width/2 - 175, y: size.width/2 - 175,  width: 350, height: 350)
            let circlePath = Path(ellipseIn: circleRect)
            
            context.fill(circlePath, with: .color(cakeBgColor))
            context.stroke(circlePath, with: .color(.black), lineWidth: 1.0)
        }
        .frame(width: 350, height: 350, alignment: .center)
        //.border(Color.black, width: 1)
        .cornerRadius(175)
    }
}
/*
struct CakeCanvas_Previews: PreviewProvider {
    static var previews: some View {
        CakeCanvas()
    }
}
*/
