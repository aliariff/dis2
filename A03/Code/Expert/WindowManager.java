import java.awt.Color;

//@author Ali, Dala, Zain
//In this expert folder, basically the window manager class is the same.
//The addition is we add the function to bring the windows to front if its title bar is clicked
//and we add a simple border around the simple windows.

public class WindowManager extends WindowSystem {
	//constant and magicNumber is variable to help positioning the window
  private int constant = 30;
  private int magicNumber = 23;
  private int initialX, initialY, finalX, finalY;
  private SimpleWindow currentWindow;

  public WindowManager(int i, int j) {
    super(i, j);
  }

  @Override
  protected void handlePaint() {
    super.handlePaint();
    for (SimpleWindow item : this.simpleWindows) {
      // to add title bar to simple window
      super.setColor(Color.BLUE);
      super.fillRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                       item.getStartY() + constant);
      // to add close button to simple window
      super.setColor(Color.RED);
      super.fillRect(item.getStartX() + item.getWidth() - constant, item.getStartY(),
                       item.getStartX() + item.getWidth(), item.getStartY() + constant);

      // to add window name
      super.setColor(Color.YELLOW);
      super.drawString(item.getName(), item.getStartX() + (item.getWidth() / 2) - constant,
                         item.getStartY() + (constant / 2));

      // to add the "X" inside close btn
      super.setColor(Color.WHITE);
      super.drawString("X", item.getStartX() + item.getWidth() - constant + (constant / 2),
                       item.getStartY() + constant - (constant / 2));

      // to add border around the window
      super.setColor(Color.MAGENTA);
      super.drawRect(item.getStartX(), item.getStartY(), item.getStartX() + item.getWidth(),
                     item.getStartY() + item.getHeight());
    }
  }

//if mouse is clicked  
  public void handleMouseClicked(int x, int y) {
    y -= magicNumber;
    clickedInCloseButton(x, y);
  }

  //if mouse is pressed (dragging the mouse)
  public void handleMousePressed(int x, int y) {
    y -= magicNumber;
    if (clickedInTitleBar(x, y)) {
      this.initialX = x;
      this.initialY = y;
    }
  }

  //if mouse click is released
  public void handleMouseReleased(int x, int y) {
    y -= magicNumber;
    if (this.initialX > -1 && this.initialY > -1 && this.currentWindow != null) {
      this.finalX = x;
      this.finalY = y;

      int translateX = this.finalX - this.initialX;
      int translateY = this.finalY - this.initialY;

      this.currentWindow.setStartX(this.currentWindow.getStartX() + translateX);
      this.currentWindow.setStartY(this.currentWindow.getStartY() + translateY);

      this.requestRepaint();
    }
    this.initialX = -1;
    this.initialY = -1;
    this.finalX = -1;
    this.finalY = -1;
  }

  public void handleMouseMoved(int x, int y) {
    y -= magicNumber;
  }

  public void handleMouseDragged(int x, int y) {
    y -= magicNumber;
  }

  //if the "X" (close) button is clicked, the clicked simple window will be remove from the array list
  //and the desktop will be repainted
  private void clickedInCloseButton(int x, int y) {
    SimpleWindow item;
    for (int i = 0; i < this.simpleWindows.size(); i++) {
      item = this.simpleWindows.get(i);
      if (item.getStartX() + item.getWidth() - constant < x
          && item.getStartX() + item.getWidth() > x && item.getStartY() < y
          && item.getStartY() + constant > y) {
        this.simpleWindows.remove(i);
        this.requestRepaint();
      }
    }
  }

  //This function is to reorder the window position if its title bar is clicked.
  //The clicked window will be removed and added again to the ArrayList so when the
  // requestRepaint() function is called, the clicked window will be on top
  private Boolean clickedInTitleBar(int x, int y) {
    SimpleWindow item;
    for (int i = 0; i < this.simpleWindows.size(); i++) {
      item = this.simpleWindows.get(i);
      if (item.getStartX() < x && item.getStartX() + item.getWidth() - constant > x
          && item.getStartY() < y && item.getStartY() + constant > y) {
        this.currentWindow = item;
        this.simpleWindows.remove(i);
        this.simpleWindows.add(item);
        this.requestRepaint();
        return true;
      }
    }
    return false;
  }

}
