import java.util.HashMap;
import java.util.Scanner;

public class HelloWorldConsole {

  public static void main(String[] args) {
    HashMap<String,String[]> supportedLanguages = new HashMap<String,String[]>();
    supportedLanguages.put("d", new String[] { "deutsch", "Guten Tag!" });
    supportedLanguages.put("e", new String[] { "english", "Good Day!" });
    supportedLanguages.put("f", new String[] { "francais", "Bonne journée" });

    Scanner scan = new Scanner(System.in);
    String input, format, key;
    String[] value, language;

    while(true) {
      System.out.println("Select one of the following:");
      for (HashMap.Entry<String, String[]> entry : supportedLanguages.entrySet()) {
        key = entry.getKey();
        value = entry.getValue();
            format = String.format("\t[%s]%s", key.toUpperCase(), value[0].substring(1));
        System.out.println(format);
      }
      System.out.println("[Q]uit");

      input = scan.next();
      input = input.toLowerCase();

      if (input.equals("q")) {
        System.out.println("Quitting...");
        scan.close();
        break;
      }

      language = supportedLanguages.get(input);
      if (language != null) {
        System.out.println(language[1]);
      } else {
        System.out.println("Input Not Supported");
      }
    }
  }

}
