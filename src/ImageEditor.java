
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.*;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import model.ImageProcessorModel;
import view.Histogram;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * this is the class that houses the main method.
 */
public class ImageEditor {

  /**
   * This method runs the program.
   *
   * @param args the command line arguments passed to the program
   */
  public static void main(String[] args) {
    ImageProcessorModel model = new ImageProcessorModel();
    ImageProcessorView view = new ImageProcessorTextView(model);
    Readable read;

    if (args.length > 0) {
      String input = "";
      for (String s : args) {
        s = listenForScript(s);
        input = input.concat(s + "\n");
      }
      read = new StringReader(input);
    } else {
      read = new InputStreamReader(System.in);
    }



    ImageProcessorController controller = new ImageProcessorControllerImpl(view, model, read);
    controller.listen();
  }

  private static String readTextLines(File file) {
    try {
      Scanner sc = new Scanner(file);
      StringBuilder temp = new StringBuilder();
      while (sc.hasNextLine()) {
        temp.append(sc.nextLine()).append("\n");
      }
      return temp.toString();
    } catch (FileNotFoundException ie) {
      throw new IllegalStateException("File not found");
    }
  }

  private static String listenForScript(String s) {
    if (s.startsWith("-file ")) {
      s = s.substring(6);
      File scriptFile = new File(s);
      s = readTextLines(scriptFile);
    }
    return s;
  }
}

