import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToStringArray {
  public static void main(String[] args) {
    Scanner sc=null;

    try {
      sc = new Scanner(new FileInputStream("examples.txt"));
    }
    catch (FileNotFoundException e) {

    }

    StringBuilder sb = new StringBuilder();
    sb.append("String []input = {");
    boolean started = false;
    while (sc.hasNext()) {
      String next = sc.next();

      if (started) {
        sb.append(", ");
      }
      else {
        started = true;
      }
      sb.append("\""+next+"\""+System.lineSeparator());
    }
    sb.append("};");
    sc.close();
    System.out.println(sb.toString());
  }

}
