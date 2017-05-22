import java.awt.Color;
import java.util.LinkedList;

public class SimpleWindow {
  private int startX, startY, width, height;
  private String name;
  private Color backgroundColor;
  private LinkedList<RATwidget> ratWidget;

  public SimpleWindow(String name, int startX, int startY, int width, int height) {
    this.name = name;
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;
    this.backgroundColor = Color.GRAY;
    this.ratWidget = new LinkedList<RATwidget>();
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public int getStartX() {
    return startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addWidget(RATwidget rw) {
    this.ratWidget.add(rw);
  }

  public LinkedList<RATwidget> getRatWidget() {
    return ratWidget;
  }

}
