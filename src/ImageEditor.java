
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import model.ImageProcessorModel;
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
  public static void main(String[] args) throws IOException {
    ImageProcessorModel model = new ImageProcessorModel();
    ImageProcessorView view = new ImageProcessorTextView(model);
    Readable read;

    if (args.length > 0) {
      String input = "";
      for (String s : args) {
        input = input.concat(s + "\n");
      }
      read = new StringReader(input);
    } else {
      read = new InputStreamReader(System.in);
    }

    ImageProcessorController controller = new ImageProcessorControllerImpl(view, model, read);
    controller.listen();
  }
}
