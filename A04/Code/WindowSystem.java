import java.util.LinkedList;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  private LinkedList<SimpleWindow> simpleWindows;
  private WindowManager wm;

  public WindowSystem(int i, int j) {
    super(i, j);
    this.width = i;
    this.height = j;
    simpleWindows = new LinkedList<SimpleWindow>();
    if (wm == null) {
      wm = new WindowManager(this);
    }
  }

  @Override
  protected void handlePaint() {
    for (SimpleWindow item : simpleWindows) {
      this.setColor(item.getBackgroundColor());
      this.fillRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                    item.getStartY() + item.getHeight());

      if (wm != null) {
        wm.decorate(item);
      }

      for (RATwidget widget : item.getRatWidget()) {
        if (widget instanceof RATbutton) {
          draw((RATbutton) widget);
        } else if (widget instanceof RATlabel) {
          draw((RATlabel) widget);
        }
      }
    }
  }

  public SimpleWindow createSimpleWindow(String name, int startX, int startY, int width,
                                         int height) {
    SimpleWindow sw = new SimpleWindow(name, startX, startY, width, height);
    this.simpleWindows.add(sw);
    return sw;
  }

  public void handleMouseClicked(int x, int y) {


  }

  private void draw(RATbutton button) {
    this.setColor(button.getBackgroundColor());
    this.fillRect(button.getX(), button.getY(), button.getX() + button.getWidth(),
                  button.getY() + button.getHeight());

    this.setColor(button.getColor());
    this.drawString(button.getName(), button.getX() + 15, button.getY() + 17);

    if (wm != null) {
      wm.decorate(button);
    }
  }

  private void draw(RATlabel label) {
    this.setColor(label.getColor());
    this.drawString(label.getName(), label.getX(), label.getY());

    if (wm != null) {
      wm.decorate(label);
    }
  }

}
