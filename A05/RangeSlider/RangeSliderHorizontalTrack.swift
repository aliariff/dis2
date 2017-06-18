//
//  RangeSliderHorizontalTrack.swift
//  RangeSlider
//
//  Created by Ali Ariff on 6/17/17.
//  Copyright © 2017 Ali Ariff. All rights reserved.
//

import Cocoa

class RangeSliderHorizontalTrack: NSView {
    public var slider : RangeSlider?
    public var leftHandle : RangeSliderHandle?
    public var rightHandle : RangeSliderHandle?
    public var sliderInfo : RangeSliderInfo?
    public var indicator : RangeSliderIndicator?
    public var currentMinValue : Int!
    public var currentMaxValue : Int!

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
    }

    init(frame frameRect: NSRect, slider rangeSlider: RangeSlider) {
        super.init(frame: frameRect)
        slider = rangeSlider
        leftHandle = RangeSliderHandle(frame: NSMakeRect(0, 0, 10, 20), track: self, symbol: "[")
        rightHandle = RangeSliderHandle(frame: NSMakeRect(0, 0, 10, 20), track: self, symbol: "]")
        sliderInfo = RangeSliderInfo(frame: NSMakeRect(0, frame.height/2 - 50, frame.width, 100), track: self)
        indicator = RangeSliderIndicator(frame: NSMakeRect(15, frame.height/2 - 15, frame.width - 30, 30), track: self)

        self.addSubview(sliderInfo!)
        self.addSubview(indicator!)
        self.addSubview(leftHandle!)
        self.addSubview(rightHandle!)
    }

    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)

        let horizontalTrack = NSBezierPath()
        let w = frame.width
        let h = frame.height / 2
        let offset: CGFloat = 6.0
        let min: Int = (slider?.minimumValue)!
        let max: Int = (slider?.maximumValue)!
        let minValue: NSString = NSString.init(string: "\(min)")
        let maxValue: NSString = NSString.init(string: "\(max)")
        let minValuePoint = CGPoint(x: 0, y: h - offset)
        let maxValuePoint = CGPoint(x: w - 20, y: h - offset)

        // draw min and value at the end of the line
        minValue.draw(at: minValuePoint)
        maxValue.draw(at: maxValuePoint)

        // draw the horizontal line
        horizontalTrack.lineWidth = 2.0
        horizontalTrack.move(to: CGPoint(x: 20, y: h))
        horizontalTrack.line(to: CGPoint(x: w - 30, y: h))
        slider?.lineColor.set()
        horizontalTrack.close()
        horizontalTrack.stroke()
        horizontalTrack.fill()

        // NSDottedFrameRect(dirtyRect)

        autoReize()

        // redraw
        leftHandle?.needsDisplay = true
        rightHandle?.needsDisplay = true
        sliderInfo?.needsDisplay = true
    }

    func autoReize() {
        leftHandle?.calculate()
        rightHandle?.calculate()

        let sliderFrame = NSMakeRect(0, frame.height/2 - 50, frame.width, 100)
        let w = (rightHandle?.currentPos?.x)! - (leftHandle?.currentPos?.x)!
        let indicatorFrame = NSMakeRect((leftHandle?.currentPos?.x)! + 3, (frame.height)/2 - 10, w, 20)

        sliderInfo?.setFrameSize(sliderFrame.size)
        sliderInfo?.setFrameOrigin(sliderFrame.origin)
        indicator?.setFrameSize(indicatorFrame.size)
        indicator?.setFrameOrigin(indicatorFrame.origin)
    }

    func getTrackLength() -> Int {
        // substract with left and right offset
        return Int (slider!.frame.width - CGFloat (50))
    }

    func getRangeValue() -> Int {
        return (slider?.maximumValue)! - (slider?.minimumValue)!
    }

}
