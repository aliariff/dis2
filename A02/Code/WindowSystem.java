import java.awt.Color;
import java.util.ArrayList;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  private ArrayList<SimpleWindow> simpleWindows;

  public WindowSystem(int i, int j) {
    super(i, j);
    this.width = i;
    this.height = j;
    simpleWindows = new ArrayList<SimpleWindow>();
  }

  @Override
  protected void handlePaint() {
    this.setColor(Color.RED);
    this.drawLine(0.2f, 0.3f, 0.8f, 0.7f);
  }

  public void drawLine(float StartX, float StartY, float EndX, float EndY) {
    super.drawLine(StartX * this.width, StartY * this.height, EndX * this.width,
        EndY * this.height);
  }

}
