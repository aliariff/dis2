//
//  RangeSliderInfo.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderInfo: NSView {
    private var slider : RangeSlider?
    private var leftHandle: RangeSliderHandle?
    private var rigthHandle: RangeSliderHandle?

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }
    
    init(frame frameRect: NSRect, slider rangeSlider: RangeSlider, leftHandle lh: RangeSliderHandle, rigthHandle rh: RangeSliderHandle) {
        super.init(frame: frameRect)
        slider = rangeSlider
        leftHandle = lh
        rigthHandle = rh
    }
    
    override func draw(_ dirtyRect: NSRect) {
        if (false) {
            
        }
        super.draw(dirtyRect)
        
        let minValue = Int ((leftHandle?.getCurrentValue())!)
        let maxValue = Int ((rigthHandle?.getCurrentValue())!)
        let labelMinValue: NSString = NSString.init(string: "\(minValue)")
        let labelMaxValue: NSString = NSString.init(string: "\(maxValue)")
        let minX = leftHandle?.getCurrentPos().x
        let maxX = rigthHandle?.getCurrentPos().x
        let y = (slider?.frame.height)! / 2
        
        labelMinValue.draw(at: NSMakePoint(minX!, y - 30))
        labelMaxValue.draw(at: NSMakePoint(maxX!, y + 15))
        
        NSDottedFrameRect(dirtyRect)
    }
}
