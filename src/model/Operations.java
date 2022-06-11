package model;

/**
 * This interface represents an operation or command that can be done on the image given.
 * Examples of operations include flipping an image, greyscaling it, or changing its brightness.
 */
public interface Operations {

  /**
   * This method takes in a model and indicates to the model that the change being made is the
   * specified function object and allows the model to change its state accordingly.
   *
   * @param image the model that is being passed to be changed
   * @throws IllegalStateException when any of the function object implementations encounters
   *                               an invalid state of the model based on arguments given to it
   *                               (specified in each implementations' documentation)
   */
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException;
}
