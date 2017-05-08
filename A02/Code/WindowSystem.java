import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
  private int width, height;
  private ArrayList<SimpleWindow> simpleWindows;
  private Point2D startPoint, endPoint;

  public Point2D getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Point2D endPoint) {
    this.endPoint = endPoint;
  }

  public Point2D getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(Point2D startPoint) {
    this.startPoint = startPoint;
  }

  public WindowSystem(int i, int j) {
    super(i, j);
    this.width = i;
    this.height = j;
    simpleWindows = new ArrayList<SimpleWindow>();
  }

  @Override
  protected void handlePaint() {
    if (this.startPoint != null && this.endPoint != null) {
      this.setColor(Color.RED);
      this.drawLine((float) this.startPoint.getX(), (float) this.startPoint.getY(),
          (float) this.endPoint.getX(), (float) this.endPoint.getY());
    }
  }

  public void drawLine(float StartX, float StartY, float EndX, float EndY) {
    super.drawLine(StartX * this.width, StartY * this.height, EndX * this.width,
        EndY * this.height);
  }

}
