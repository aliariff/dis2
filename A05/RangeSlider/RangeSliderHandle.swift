//
//  RangeSliderHandle.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHandle: NSView {
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
    }
}
