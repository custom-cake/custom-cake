//
//  Date.swift
//  custom-cake-frontend
//
//  Created by 황서진 on 2023/04/04.
//

import SwiftUI

struct Schedule: View {
    @Environment(\.calendar) var calendar
    @State private var selectedDates: Set<DateComponents> = []
    @State var columnCount: Int = 3
   
    var body: some View {
        VStack {
            Text("픽업 날짜 검색")
                .font(.title)
                .fontWeight(.bold)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            MultiDatePicker(
                "DatePicker", selection: $selectedDates,
                      in: Date()...
            )
            
            LazyVGrid(columns: Array(repeating: GridItem(.flexible()), count: columnCount) ) {
                    ForEach (selectedDatesTotal, id: \.self) {
                        OptionLabel(text: $0)
                    }
            }
            
        }
        .padding(15)
    }
    

    var selectedDatesTotal: Array<String> {
        selectedDates.compactMap { components in
            calendar.date(from: components)!.formatted(date: .numeric, time: .omitted)
        }
    }

}

struct Schedule_Previews: PreviewProvider {
    static var previews: some View {
        Schedule()
    }
}
