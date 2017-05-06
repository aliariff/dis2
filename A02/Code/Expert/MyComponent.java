import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class MyComponent extends JComponent implements MouseMotionListener, MouseListener {
  int x, y, length, mouseY;

  public MyComponent() {
    this.x = 100;
    this.y = 250;
    this.length = 100;
    addMouseMotionListener(this);
    addMouseListener(this);
  }

  public void paint(Graphics g) {
    g.drawLine(this.x, this.y, this.x + this.length, this.y);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.mouseY = e.getY();

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (this.mouseY > e.getY()) {
      this.length += 1;
    } else if (this.mouseY < e.getY()) {
      if (this.length > 0)
        this.length -= 1;
    }
    this.repaint();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
