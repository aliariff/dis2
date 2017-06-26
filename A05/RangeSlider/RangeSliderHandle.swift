//
//  RangeSliderHandle.swift
//  RangeSlider
//
//  DIS II Group 7
//  Ali Ariff, Andi Heynoum Dala Rifat, Zain Ahmed S.
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHandle: NSView {
    private var track : RangeSliderHorizontalTrack?
    private var slider : RangeSlider?
    private var clicked : Bool = false
    public var currentPos : NSPoint?
    public var currentValue : Int = 0
    public var pixelSize : Double = 0.0
    private var symbol : String = ""

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }

    init(frame frameRect: NSRect, track trackSlider: RangeSliderHorizontalTrack, symbol sym: String) {
        super.init(frame: frameRect)
        track = trackSlider
        slider = track?.slider
        symbol = sym
        currentPos = frame.origin
    }

    func calculate() {
        // set the pixel's size based on the length of horizontalTrack (in pixel unit) divided by range value between mix and max
        pixelSize = Double ((track?.getTrackLength())!) / Double ((track?.getRangeValue())!)
        // get current x coordinate from the currentValue of the handle
        let x = CGFloat (currentValue - (slider?.minimumValue)!) * CGFloat (pixelSize)
        let y = (slider?.frame.height)! / 2
        let thisOrigin = NSMakePoint(x + 20, y - 10)
        currentPos = thisOrigin
        // change the frame origin based on the handle
        setFrameOrigin(thisOrigin)
        // set global value min or max property in horizontalTrack
        if (leftHandle()) {
            track?.currentMinValue = currentValue
        } else {
            track?.currentMaxValue = currentValue
        }
    }

    func leftHandle() -> Bool {
        return symbol == "["
    }

    func rightHandle() -> Bool {
        return symbol == "]"
    }

    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)

        calculate()
        // set default color of the handle as black
        var textColor = NSColor.black
        // if the handle get clicked change it to blue
        if (clicked) {
            textColor = NSColor.blue
        }
        let handle = NSString.init(string: symbol)
        let font = NSFont(name: "Helvetica Bold", size: 20.0)
        let textFontAttributes = [
            NSFontAttributeName : font!,
            NSForegroundColorAttributeName: textColor,
        ] as [String : Any]
        // draw the handle
        handle.draw(at: NSMakePoint(0, 0), withAttributes: textFontAttributes)
        // NSDottedFrameRect(dirtyRect)
    }

    override func mouseDown(with theEvent: NSEvent) {
        clicked = true
        needsDisplay = true
    }

    override func mouseUp(with theEvent: NSEvent) {
        clicked = false
        needsDisplay = true
    }

    override func mouseDragged(with theEvent: NSEvent) {
        // mouse coordinates within this view's coordinate system
        let newDragLocation = superview!.convert(theEvent.locationInWindow, from: nil)
        if (clicked && currentValue >= (slider?.minimumValue)! &&
            currentValue <= (slider?.maximumValue)! &&
            (track?.currentMinValue)! <= (track?.currentMaxValue)!) {

            // calculate the new value based on current position
            var newValue = Int (Double (newDragLocation.x - 20) / pixelSize) + (slider?.minimumValue)!
            newValue = max((slider?.minimumValue)!, newValue)
            newValue = min((slider?.maximumValue)!, newValue)

            // set the global value and currentValue based on the left or right handle
            if (leftHandle() && newValue < (track?.currentMaxValue)!) {
                track?.currentMinValue = newValue
                currentValue = newValue
            } else if (rightHandle() && newValue > (track?.currentMinValue)!) {
                track?.currentMaxValue = newValue
                currentValue = newValue
            }

            // redraw
            needsDisplay = true
            track?.needsDisplay = true
        }
    }

}
