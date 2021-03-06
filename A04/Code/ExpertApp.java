import java.util.ArrayList;
import java.util.Arrays;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

//DIS II Assignment 4
//Group 7 :
//	- Andi Heynoum Dala Rifat
//	- Ali Ariff
//	- Zain A. Solail
//RATulator using the widget and listener class.

public class ExpertApp {
  public static void main(String[] args) {
    //create the desktop and the RATulator window
    WindowSystem ws = new WindowSystem(800, 800);
    SimpleWindow sw = ws.createSimpleWindow("RAT Calc", 200, 200, 400, 400);
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");

    //add the button into list
    ArrayList<String> btn = new ArrayList<String>();
    btn.addAll(Arrays.asList("0", ".", "=", "+", "1", "2", "3", "-", "4", "5", "6", "*", "7", "8",
                             "9", "/"));

    //label for the result
    RATlabel label = new RATlabel("0", 300, 300);
    sw.addWidget(label);

    //mouse listener if the button is clicked which will change the label value.
    RATmouseListener listener = new RATmouseListener() {

      @Override
      public void mouseClicked(String name) {
        if (name == "c") {
          label.setName("0");
        } else if (name == "=") {
          try {
            label.setName(engine.eval(label.getName()).toString());
          } catch (ScriptException e) {
            label.setName("error");
          }
        } else if (label.getName().length() == 1 && label.getName() == "0") {
          label.setName(name);
        } else {
          label.setName(label.getName() + name);
        }
      }

    };

    //clear button (c) that erase the whole string
    RATbutton clearBtn = new RATbutton("c", 385, 520);
    clearBtn.setHeight(30);
    clearBtn.setWidth(30);
    clearBtn.addListener(listener);
    sw.addWidget(clearBtn);

    int x = 0;
    int y = 520;
    int ctr = 0;
    for (String c : btn) {
      if (ctr % 4 == 0) {
        x = 310;
        y -= 50;
      }
      RATbutton b = new RATbutton(c, x, y);
      b.setHeight(30);
      b.setWidth(30);
      b.addListener(listener);
      sw.addWidget(b);
      x += 50;
      ctr++;
    }

  }
}
