import java.util.ArrayList;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  protected ArrayList<SimpleWindow> simpleWindows;

  public WindowSystem(int i, int j) {
    super(i, j);
    this.width = i;
    this.height = j;
    simpleWindows = new ArrayList<SimpleWindow>();
  }

  @Override
  protected void handlePaint() {
    for (SimpleWindow item : simpleWindows) {
      super.setColor(item.getBackgroundColor());
      super.fillRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                     item.getStartY() + item.getHeight());
    }
  }

  public void addComponent(SimpleWindow obj) {
    this.simpleWindows.add(obj);
  }
}
