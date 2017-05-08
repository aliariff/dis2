import java.awt.Color;
import java.util.ArrayList;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  private int StartX, StartY, EndX, EndY;
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
    super.drawLine(StartX, StartY, EndX, EndY);
  }

  public void drawLine(float StartX, float StartY, float EndX, float EndY) {
    this.StartX = (int) (StartX * this.width);
    this.StartY = (int) (StartY * this.height);
    this.EndX = (int) (EndX * this.width);
    this.EndY = (int) (EndY * this.height);
  }

}
