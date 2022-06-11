package view;

import java.io.IOException;

import model.ImageProcessor;
import model.Pixel;

/**
 * This class represents a text view implementation of the ImageProcessorView and
 * will deliver messages and images as Strings to the appropriate appendable object.
 * As such, the image returned won't be the classic image that is viewed.
 */
public class ImageProcessorTextView extends AbstractImageProcessorView {

  /**
   * This constructor instantiates a text view for the image processing program and allows for a
   * custom appendable object along with the image processing model. This specific view
   * implementation will display the contents of an ASCII PPM file as a series of string texts,
   * formatted such that every pixel's channel will be separated by a newline.
   *
   * @param appendable the appendable object that the messages and image representation will
   *                   write to for this program.
   * @param model      the model of the ImageProcessor that will handle changing the image state
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  public ImageProcessorTextView(Appendable appendable,
                                ImageProcessor model) throws IllegalArgumentException {
    super(appendable, model);
  }

  /**
   * This constructor instantiates a text view for the image processing program and sets the default
   * appendable object as the system console with the image processing model. This specific view
   * implementation will display the contents of an ASCII PPM file as a series of string texts,
   * formatted such that every pixel's channel will be separated by a newline.
   *
   * @param model the model of the ImageProcessor that will handle changing the image state
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  public ImageProcessorTextView(ImageProcessor model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * This method represents an override of the Object toString method by listing each pixel
   * value.
   *
   * @return the string representation or text view of a PPM image in the form of its ASCII PPM
   *         representation
   */
  @Override
  public String toString() {
    StringBuilder header = new StringBuilder();
    Pixel[][] temp = model.getImage();
    header.append("P3\n")
            .append(temp[0].length)
            .append(" ")
            .append(temp.length)
            .append("\n")
            .append(model.getMaxValue())
            .append("\n");
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        header.append(" (" + temp[i][j].getColorValue("r") + ",");
        header.append(temp[i][j].getColorValue("g") + ",");
        header.append(temp[i][j].getColorValue("b") + ") ");
      }
      header.append("\n");
    }
    return header.toString();
  }

  @Override
  public void renderImage() throws IllegalStateException {
    try {
      appendable.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
