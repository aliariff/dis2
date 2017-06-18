//
//  RangeSliderInfo.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderInfo: NSView {
    private var track : RangeSliderHorizontalTrack?
    private var leftHandle: RangeSliderHandle?
    private var rigthHandle: RangeSliderHandle?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }
    
    init(frame frameRect: NSRect, track trackSlider: RangeSliderHorizontalTrack) {
        super.init(frame: frameRect)
        track = trackSlider
        leftHandle = track?.leftHandle
        rigthHandle = track?.rightHandle
    }
    
    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)

        let minValue = Int ((track?.currentMinValue)!)
        let maxValue = Int ((track?.currentMaxValue)!)
        let labelMinValue: NSString = NSString.init(string: "\(minValue)")
        let labelMaxValue: NSString = NSString.init(string: "\(maxValue)")
        let minX = leftHandle?.getCurrentPos().x
        let maxX = rigthHandle?.getCurrentPos().x
        
        labelMinValue.draw(at: NSMakePoint(minX!, 20))
        labelMaxValue.draw(at: NSMakePoint(maxX!, 65))
        
        NSDottedFrameRect(dirtyRect)
    }
}
