
public class HelloWorldGraphical {
  public static void main(String[] args) {
    WindowSystem ws = new WindowSystem(800, 800);
    SimpleWindow sw = ws.createSimpleWindow("Hello GUI", 200, 200, 400, 400);

    RATbutton deutsch = new RATbutton("deutsch", 360, 250);
    RATbutton english = new RATbutton("english", 360, 300);
    RATbutton francais = new RATbutton("francais", 360, 350);
    RATbutton beenden = new RATbutton("Beenden", 500, 550);
    RATlabel label = new RATlabel("Hello", 250, 450);

    sw.addWidget(english);
    sw.addWidget(deutsch);
    sw.addWidget(francais);
    sw.addWidget(beenden);
    sw.addWidget(label);

    beenden.addListener(new RATmouseListener() {

      @Override
      public void mouseClicked(String name) {
        System.exit(0);
      }

    });

    deutsch.addListener(new RATmouseListener() {

      @Override
      public void mouseClicked(String name) {
        label.setName("Guten Tag!");
      }

    });

    english.addListener(new RATmouseListener() {

      @Override
      public void mouseClicked(String name) {
        label.setName("Good Day!");
      }

    });

    francais.addListener(new RATmouseListener() {

      @Override
      public void mouseClicked(String name) {
        label.setName("Bonne journée!");
      }

    });
  }
}
