//
//  RangeSliderHorizontalTrack.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHorizontalTrack: NSView {
    private var slider : RangeSlider?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }
    
    init(frame frameRect: NSRect, slider rangeSlider: RangeSlider) {
        super.init(frame: frameRect)
        slider = rangeSlider
    }
    
    
    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)
        
        let horizontalTrack = NSBezierPath()
        let w = self.frame.width
        let h = self.frame.height / 2
        let offset: CGFloat = 6.0
        let min: Int = (slider?.minimumValue)!
        let max: Int = (slider?.maximumValue)!
        let minValue: NSString = NSString.init(string: "\(min)")
        let maxValue: NSString = NSString.init(string: "\(max)")
        let minValuePoint = CGPoint(x: 0, y: h - offset)
        let maxValuePoint = CGPoint(x: w - 50, y: h - offset)
        
        // draw min and value at the end of the line
        minValue.draw(at: minValuePoint)
        maxValue.draw(at: maxValuePoint)
        
        // draw the horizontal line
        horizontalTrack.lineWidth = 2.0
        horizontalTrack.move(to: CGPoint(x: 20, y: h))
        horizontalTrack.line(to: CGPoint(x: w - 60, y: h))
        slider?.lineColor.set()
        horizontalTrack.close()
        horizontalTrack.stroke()
        horizontalTrack.fill()
        Swift.print("RangeSliderHorizontalTrack size is \(self.frame.width) \(self.frame.height)")
    }
}
