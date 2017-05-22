import java.awt.Color;
import java.util.LinkedList;

public class WindowManager {
  private WindowSystem ws;
  private int constant = 30;
  private int magicNumber = 23;

  public WindowManager(WindowSystem aWs) {
    this.ws = aWs;
  }

  public void decorate(SimpleWindow item) {
    // title bar
    ws.setColor(Color.BLUE);
    ws.fillRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                item.getStartY() + constant);

    // close btn
    ws.setColor(Color.RED);
    ws.fillRect(item.getStartX() + item.getWidth() - constant, item.getStartY(),
                item.getStartX() + item.getWidth(), item.getStartY() + constant);

    // title name
    ws.setColor(Color.YELLOW);
    ws.drawString(item.getName(), item.getStartX() + (item.getWidth() / 2) - constant,
                  item.getStartY() + (constant / 2));

    // x inside cls btn
    ws.setColor(Color.WHITE);
    ws.drawString("X", item.getStartX() + item.getWidth() - constant + (constant / 2),
                  item.getStartY() + constant - (constant / 2));

    // border
    ws.setColor(Color.GREEN);
    ws.drawRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                item.getStartY() + item.getHeight());
  }

  public void decorate(RATbutton button) {
    ws.setColor(Color.GREEN);
    ws.drawRect(button.getX(), button.getY(), button.getX() + button.getWidth(),
                button.getY() + button.getHeight());
  }

  public void decorate(RATlabel label) {

  }

}
