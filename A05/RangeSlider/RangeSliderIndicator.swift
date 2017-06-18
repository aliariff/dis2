//
//  RangeSliderIndicator.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderIndicator: NSView {
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
        let w = (rigthHandle?.getCurrentPos().x)! - (leftHandle?.getCurrentPos().x)!
        let thisFrame = NSMakeRect((leftHandle?.getCurrentPos().x)! + 3, (track?.frame.height)!/2 - 10, w, 20)
        setFrameSize(thisFrame.size)
        setFrameOrigin(thisFrame.origin)
        
        track?.slider?.indicatorColor.set()
        NSRectFill(dirtyRect)
    }
}
