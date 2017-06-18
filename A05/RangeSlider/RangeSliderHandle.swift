//
//  RangeSliderHandle.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHandle: NSView {
    private var lastDragLocation : NSPoint?
    private var clicked : Bool = false
    private var slider : RangeSlider?
    private var track : RangeSliderHorizontalTrack?
    private var currentValue : Int = 0
    private var currentPos : NSPoint?
    private var pixelSize : Double = 0.0

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }
    
    init(frame frameRect: NSRect, slider rangeSlider: RangeSlider, track trackSlider: RangeSliderHorizontalTrack) {
        super.init(frame: frameRect)
        slider = rangeSlider
        track = trackSlider
        currentValue = (slider?.leftIndicator)!
    }
    
    func calculate() {
        pixelSize = Double ((track?.getTrackLength())!) / Double ((track?.getRangeValue())!)
    }
    
    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)
        
        calculate()
        var textColor = NSColor.black
        if (clicked) {
            textColor = NSColor.blue
        }
        let y = (slider?.frame.height)! / 2
        let handle: NSString = "["
        let font = NSFont(name: "Helvetica Bold", size: 20.0)
        let textFontAttributes = [
            NSFontAttributeName : font!,
            NSForegroundColorAttributeName: textColor,
        ] as [String : Any]
        let x = CGFloat (currentValue) * CGFloat (pixelSize)
        currentPos = NSMakePoint(x + 20, y - 10)
        handle.draw(at: currentPos!, withAttributes: textFontAttributes)
        
        let labelValue: NSString = NSString.init(string: "\(currentValue)")
        labelValue.draw(at: NSMakePoint(x + 17, y - 30))
        NSDottedFrameRect(dirtyRect)
    }
    
    override func mouseDown(with theEvent: NSEvent) {
//         mouse coordinates within this view's coordinate system
        lastDragLocation = superview!.convert(theEvent.locationInWindow, from: nil)
        let y = (lastDragLocation?.y)! - 10
        lastDragLocation?.y -= 10
        if ((currentPos?.x)! + 7 > (lastDragLocation?.x)! &&
            (currentPos?.x)! - 3 < (lastDragLocation?.x)! &&
            (currentPos?.y)! + 15 > y &&
            (currentPos?.y)! - 10 < y) {
            clicked = true
        }
    }
    
    override func mouseUp(with theEvent: NSEvent) {
        clicked = false
        needsDisplay = true
    }
    
    override func mouseDragged(with theEvent: NSEvent) {
//         mouse coordinates within this view's coordinate system
        let newDragLocation = superview!.convert(theEvent.locationInWindow, from:nil)
        if (clicked && newDragLocation.x >= 20 && newDragLocation.x <= (slider?.frame.width)! - 29) {
            needsDisplay = true
            
            lastDragLocation = newDragLocation
            currentValue = Int (Double (newDragLocation.x - 20) / pixelSize)
        }
    }
}
