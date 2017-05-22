import java.awt.Color;
import java.util.LinkedList;

public abstract class RATwidget {
  protected int x, y, height, width;
  protected Color color;
  protected String name;
  protected LinkedList<RATmouseListener> listener = new LinkedList<RATmouseListener>();

  public LinkedList<RATmouseListener> getListener() {
    return listener;
  }

  public void addListener(RATmouseListener listener) {
    this.listener.add(listener);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
