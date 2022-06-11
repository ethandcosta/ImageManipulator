package view;

import java.io.IOException;

import util.Util;
import model.ImageProcessor;

/**
 * This class is an abstraction of all the common features and actions of the different types of
 * views for this image processing program.
 */
public abstract class AbstractImageProcessorView implements ImageProcessorView {
  Appendable appendable;
  ImageProcessor model;

  /**
   * This constructor instantiates an abstract view with a custom appendable and custom model that
   * will be used to modify and write displays of the image.
   *
   * @param appendable the custom location that the view will append elements to
   * @param model      the model of the ImageProcessor that will handle changing the image state
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  protected AbstractImageProcessorView(Appendable appendable, ImageProcessor model)
          throws IllegalArgumentException {
    this.appendable = Util.checkNullness(appendable);
    this.model = Util.checkNullness(model);
  }

  /**
   * This constructor instantiates an abstract view for the image processing program and sets
   * the default appendable to the console.
   *
   * @param model the model of the ImageProcessor that will handle changing the image state
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  protected AbstractImageProcessorView(ImageProcessor model)
          throws IllegalArgumentException {
    this.appendable = System.out;
    this.model = Util.checkNullness(model);
  }

  @Override
  public void renderMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Transmission error\n" + e.getMessage());
    }
  }

  @Override
  public abstract void renderImage() throws IllegalStateException;
}
