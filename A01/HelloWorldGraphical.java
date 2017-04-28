import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

import javax.swing.*;

public class HelloWorldGraphical implements ActionListener {
  private JFrame mainFrame;
  private JLabel welcomeMessage;
  private JButton button;
  private Box box;
  private LinkedHashMap<String, String> supportedLanguages;

  public HelloWorldGraphical() {
    supportedLanguages = new LinkedHashMap<String, String>();
    supportedLanguages.put("deutsch", "Guten Tag!");
    supportedLanguages.put("english", "Good Day!");
    supportedLanguages.put("francais", "Bonne journée!");
  }

  private void prepareWindow() {
    mainFrame = new JFrame("Hello GUI");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(400, 400);
    mainFrame.getRootPane().setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
  }

  private void prepareMenu() {
    box = Box.createVerticalBox();
    for (String language : supportedLanguages.keySet()) {
      String name = Character.toString(language.charAt(0)).toUpperCase() + language.substring(1);
      button = new JButton(name);
      button.setAlignmentX(Component.CENTER_ALIGNMENT);
      button.setMaximumSize(new Dimension(100, button.getMinimumSize().height));
      button.addActionListener(this);
      box.add(button);
    }
    box.add(Box.createRigidArea(new Dimension(0, 100)));
    mainFrame.add(box, BorderLayout.NORTH);

    box = Box.createVerticalBox();
    welcomeMessage = new JLabel();
    box.add(welcomeMessage);
    mainFrame.add(box, BorderLayout.CENTER);

    box = Box.createVerticalBox();
    button = new JButton("Beenden");
    button.setAlignmentX(Component.RIGHT_ALIGNMENT);
    button.addActionListener(this);
    box.add(button);
    mainFrame.add(box, BorderLayout.SOUTH);
  }

  private void createAndShowGUI() {
    prepareWindow();
    prepareMenu();

    mainFrame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    command = command.toLowerCase();
    String language = supportedLanguages.get(command);
    if (language != null) {
      welcomeMessage.setText(language);
    } else {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new HelloWorldGraphical().createAndShowGUI();
      }
    });
  }

}
