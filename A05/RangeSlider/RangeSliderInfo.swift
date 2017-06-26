//
//  RangeSliderInfo.swift
//  RangeSlider
//
//  DIS II Group 7
//  Ali Ariff, Andi Heynoum Dala Rifat, Zain Ahmed S.
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderInfo: NSView {
    private var track : RangeSliderHorizontalTrack?
    private var leftHandle: RangeSliderHandle?
    private var rightHandle: RangeSliderHandle?

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
        rightHandle = track?.rightHandle
    }

    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)

        // initialize the needed variable and value
        let minValue = Int ((leftHandle?.currentValue)!)
        let maxValue = Int ((rightHandle?.currentValue)!)
        let labelMinValue: NSString = NSString.init(string: "\(minValue)")
        let labelMaxValue: NSString = NSString.init(string: "\(maxValue)")
        let rangeValue: NSString = NSString.init(string: "Selected Range: \(maxValue - minValue)")
        let minX = leftHandle?.currentPos?.x
        let maxX = rightHandle?.currentPos?.x

        // draw the slider info
        labelMinValue.draw(at: NSMakePoint(minX!, 20))
        labelMaxValue.draw(at: NSMakePoint(maxX!, 65))
        rangeValue.draw(at: NSMakePoint(frame.width/2 - 55, 0))

        // NSDottedFrameRect(dirtyRect)
    }

}
