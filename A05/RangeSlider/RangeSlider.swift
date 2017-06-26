//
//  RangeSlider.swift
//  RangeSlider
//
//  DIS II Group 7
//  Ali Ariff, Andi Heynoum Dala Rifat, Zain Ahmed S.
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

    @IBInspectable var leftIndicator : Int = 10 {
        didSet {
            // basic validation
            if (minimumValue > leftIndicator) {
                leftIndicator = minimumValue
            }
            // set the left handle current value and calculate
            horizontalTrack?.leftHandle?.currentValue = leftIndicator
            horizontalTrack?.leftHandle?.calculate()
            // redraw!
            needsDisplay = true
        }
    }

    @IBInspectable var rightIndicator : Int = 80 {
        didSet {
            // basic validation
            if (maximumValue < rightIndicator) {
                rightIndicator = maximumValue
            }
            // set the right handle current value and calculate
            horizontalTrack?.rightHandle?.currentValue = rightIndicator
            horizontalTrack?.rightHandle?.calculate()
            // redraw!
            needsDisplay = true
        }
    }

    var horizontalTrack : RangeSliderHorizontalTrack?

    required init?(coder: NSCoder) {
        super.init(coder: coder)

        // create horizontalTrack object and add as subview
        horizontalTrack = RangeSliderHorizontalTrack(frame: NSMakeRect(0, 0, frame.width, frame.height), slider: self)
        self.addSubview(horizontalTrack!)
    }

    override func draw(_ dirtyRect: NSRect) {
        super.draw(dirtyRect)
        // handle when resizing window happens, pass the new window size to the horizontalTrack object
        horizontalTrack?.setFrameSize(frame.size)
    }

}
