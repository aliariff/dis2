//
//  RangeSliderHandle.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHandle: NSView {
    private var clicked : Bool = false
    private var currentPos : NSPoint?
    private var slider : RangeSlider?
    private var track : RangeSliderHorizontalTrack?
    private var currentValue : Int! = 0
    private var pixelSize : Double = 0.0
    private var symbol : String = ""
    public var sliderInfo : RangeSliderInfo?

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }
    
    init(frame frameRect: NSRect, slider rangeSlider: RangeSlider, track trackSlider: RangeSliderHorizontalTrack, symbol sym: String) {
        super.init(frame: frameRect)
        slider = rangeSlider
        track = trackSlider
        symbol = sym
        currentPos = frame.origin
        if (leftHandle()) {
            currentValue = (slider?.leftIndicator)!
        } else {
            currentValue = (slider?.rightIndicator)!
        }
    }
    
    func calculate() {
        pixelSize = Double ((track?.getTrackLength())!) / Double ((track?.getRangeValue())!)
        let x = CGFloat (currentValue) * CGFloat (pixelSize)
        let y = (slider?.frame.height)! / 2
        let thisOrigin = NSMakePoint(x + 20, y - 10)
        currentPos = thisOrigin
        setFrameOrigin(thisOrigin)
        setFrameSize(NSMakeSize(10, 20))
    }
    
    func leftHandle() -> Bool {
        return symbol == "["
    }
    
    func rightHandle() -> Bool {
        return symbol == "]"
    }
    
    func getCurrentValue() -> Int! {
        return currentValue
    }
    
    func getCurrentPos() -> NSPoint! {
        return currentPos
    }
    
    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)
        
        calculate()
        var textColor = NSColor.black
        if (clicked) {
            textColor = NSColor.blue
        }
        let handle = NSString.init(string: symbol)
        let font = NSFont(name: "Helvetica Bold", size: 20.0)
        let textFontAttributes = [
            NSFontAttributeName : font!,
            NSForegroundColorAttributeName: textColor,
        ] as [String : Any]
        handle.draw(at: NSMakePoint(0, 0), withAttributes: textFontAttributes)
        Swift.print("draw handle")
        NSDottedFrameRect(dirtyRect)
    }
    
    override func mouseDown(with theEvent: NSEvent) {
        clicked = true
    }
    
    override func mouseUp(with theEvent: NSEvent) {
        clicked = false
        needsDisplay = true
    }
    
    override func mouseDragged(with theEvent: NSEvent) {
//         mouse coordinates within this view's coordinate system
        let newDragLocation = superview!.convert(theEvent.locationInWindow, from:nil)
        if (clicked && currentValue >= (slider?.minimumValue)! && currentValue <= (slider?.maximumValue)!) {
            needsDisplay = true
            sliderInfo?.needsDisplay = true
            currentValue = Int (Double (newDragLocation.x - 20) / pixelSize)
            currentValue = max((slider?.minimumValue)!, currentValue)
            currentValue = min((slider?.maximumValue)!, currentValue)
            Swift.print(currentValue)
        }
    }
}
