public class MyApp {

  public static void main(String[] args) {
    WindowManager wm = new WindowManager(800, 800);
    SimpleWindow sw1 = new SimpleWindow("Window 1", 10, 100, 300, 300);
    SimpleWindow sw2 = new SimpleWindow("Window 2", 50, 50, 300, 300);
    SimpleWindow sw3 = new SimpleWindow("Window 3", 70, 70, 300, 300);
    wm.addComponent(sw1);
    wm.addComponent(sw2);
    wm.addComponent(sw3);
  }

}
