import java.util.ArrayList;
import de.rwth.hci.Graphics.GraphicsEventSystem;

// @autor Ali, Dala, Zain

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  protected ArrayList<SimpleWindow> simpleWindows;

  //constructor for WindowSystem
  public WindowSystem(int i, int j) {
    super(i, j);
    this.width = i;
    this.height = j;
    simpleWindows = new ArrayList<SimpleWindow>();
  }

  @Override
  protected void handlePaint() {
	//for every Simple Window, it will get the background colour and draw the rectangle from the given coordinate
    for (SimpleWindow item : simpleWindows) {
      super.setColor(item.getBackgroundColor());
      super.fillRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                     item.getStartY() + item.getHeight());
    }
  }

  //addComponent(SimpleWindow) function is to add a simple window to the array list.
  public void addComponent(SimpleWindow obj) {
    this.simpleWindows.add(obj);
  }

}
