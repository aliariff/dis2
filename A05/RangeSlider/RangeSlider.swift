//
//  RangeSlider.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/16/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

@IBDesignable

class RangeSlider: NSView {
    @IBInspectable var minimumValue : Int = 0 {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    @IBInspectable var maximumValue : Int = 100 {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    @IBInspectable var lineColor : NSColor = NSColor (srgbRed: 0.0, green: 0.0, blue: 0.0, alpha: 0.0) {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    @IBInspectable var indicatorColor : NSColor = NSColor (srgbRed: 0.0, green: 0.0, blue: 255.0, alpha: 0.0) {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    @IBInspectable var leftIndicator : Int = 50 {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    @IBInspectable var rightIndicator : Int = 75 {
        didSet {
            // redraw!
            needsDisplay = true
        }
    }
    
    var horizontalTrack : RangeSliderHorizontalTrack?
    var leftHandle : RangeSliderHandle?
    var rightHandle : RangeSliderHandle?
    var sliderInfo : RangeSliderInfo?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        horizontalTrack = RangeSliderHorizontalTrack(frame: NSMakeRect(0, 0, frame.width, frame.height), slider: self)
        leftHandle = RangeSliderHandle(frame: NSMakeRect(0, 0, frame.width, frame.height), slider: self, track: horizontalTrack!, symbol: "[")
        rightHandle = RangeSliderHandle(frame: NSMakeRect(0, 0, frame.width, frame.height), slider: self, track: horizontalTrack!, symbol: "]")
        sliderInfo = RangeSliderInfo(frame: NSMakeRect(0, 0, frame.width, frame.height), slider: self, leftHandle: leftHandle!, rigthHandle: rightHandle!)
        leftHandle?.sliderInfo = sliderInfo
        rightHandle?.sliderInfo = sliderInfo
        self.addSubview(horizontalTrack!)
        self.addSubview(sliderInfo!)
        self.addSubview(leftHandle!)
        self.addSubview(rightHandle!)
    }
    
    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)
        horizontalTrack?.setFrameSize(frame.size)
        leftHandle?.setFrameSize(frame.size)
        leftHandle?.needsDisplay = false
        rightHandle?.setFrameSize(frame.size)
        sliderInfo?.setFrameSize(frame.size)
    }

}
