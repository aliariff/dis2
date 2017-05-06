import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ExtraCreditApp {
  private JFrame mainFrame;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new ExtraCreditApp().createAndShowGUI();
      }
    });

  }

  protected void createAndShowGUI() {
    mainFrame = new JFrame("ExtraCreditApp");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(500, 500);
    mainFrame.add(new MyComponent());
    mainFrame.setVisible(true);
  }

}
