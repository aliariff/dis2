import java.awt.geom.Point2D;

public class MyApp {

  public static void main(String[] args) {
    WindowSystem ws = new WindowSystem(700, 700);
    ws.setStartPoint(new Point2D.Float(0.2f, 0.3f));
    ws.setEndPoint(new Point2D.Float(0.8f, 0.7f));
  }

}
