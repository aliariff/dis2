//DIS II Assignment 4
//Group 7 :
//	- Andi Heynoum Dala Rifat
//	- Ali Ariff
//	- Zain A. Solail
// RATLabel class

import java.awt.Color;

public class RATbutton extends RATlabel {
  private Color backgroundColor;

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  //constructor for RATbutton that define its width, height and background colour
  public RATbutton(String name, int startX, int startY) {
    super(name, startX, startY);
    this.width = 75;
    this.height = 25;
    this.backgroundColor = Color.DARK_GRAY;
  }

}
