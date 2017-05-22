import java.awt.Color;

public class RATbutton extends RATlabel {
  private Color backgroundColor;

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public RATbutton(String name, int startX, int startY) {
    super(name, startX, startY);
    this.width = 75;
    this.height = 25;
    this.backgroundColor = Color.DARK_GRAY;
  }

}
