import java.awt.Color;

public class WindowManager {
  private WindowSystem ws;
  private int constant = 30;

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

  public void handleMouseClicked(int x, int y) {
    clickedInCloseButton(x, y);
    clickedInWidget(x, y);
  }

  public void handleMousePressed(int x, int y) {
    if (clicedInTitleBar(x, y)) {
      ws.initialX = x;
      ws.initialY = y;
    }
  }

  public void handleMouseReleased(int x, int y) {
    ws.currentWindow = null;
    ws.initialX = -1;
    ws.initialY = -1;
    ws.finalX = -1;
    ws.finalY = -1;
  }

  public void handleMouseMoved(int x, int y) {
  }

  public void handleMouseDragged(int x, int y) {
    if (ws.initialX > -1 && ws.initialY > -1 && ws.currentWindow != null) {
      ws.finalX = x;
      ws.finalY = y;

      int translateX = ws.finalX - ws.initialX;
      int translateY = ws.finalY - ws.initialY;

      ws.currentWindow.moveTo(ws.currentWindow.getStartX() + translateX,
                              ws.currentWindow.getStartY() + translateY);

      for (RATwidget widget : ws.currentWindow.getRatWidget()) {
        widget.moveTo(widget.getX() + translateX, widget.getY() + translateY);
      }

      ws.initialX = x;
      ws.initialY = y;
    }
  }

  private void clickedInCloseButton(int x, int y) {
    SimpleWindow item;
    for (int i = 0; i < ws.simpleWindows.size(); i++) {
      item = ws.simpleWindows.get(i);
      if (item.getStartX() + item.getWidth() - constant < x
          && item.getStartX() + item.getWidth() > x && item.getStartY() < y
          && item.getStartY() + constant > y) {
        ws.simpleWindows.remove(i);
      }
    }
  }

  private Boolean clicedInTitleBar(int x, int y) {
    SimpleWindow item;
    for (int i = 0; i < ws.simpleWindows.size(); i++) {
      item = ws.simpleWindows.get(i);
      if (item.getStartX() < x && item.getStartX() + item.getWidth() - constant > x
          && item.getStartY() < y && item.getStartY() + constant > y) {
        ws.currentWindow = item;
        ws.simpleWindows.remove(i);
        ws.simpleWindows.add(item);
        return true;
      }
    }
    return false;
  }

  private void clickedInWidget(int x, int y) {
    for (SimpleWindow item : ws.simpleWindows) {
      for (RATwidget widget : item.getRatWidget()) {
        if (widget instanceof RATbutton && widget.getX() < x
            && widget.getX() + widget.getWidth() > x && widget.getY() < y
            && widget.getY() + widget.getHeight() > y) {
          for (RATmouseListener listener : widget.getListener()) {
            listener.mouseClicked(widget.getName());
          }
        }
      }
    }
  }

}
