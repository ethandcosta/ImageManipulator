package model;

/**
 * This class represents an implementation of a vertical flip image operation which
 * is a function object that flips the contents of the given image name in the model on its x-axis
 * and creates a copy of the modified image with a new given name being the image name.
 */
public class VerticalFlip extends AbstractOperations {

  /**
   * This constructor instantiates a vertical flip function object by doing a vertical flip and
   * saving the new image as a new given name.
   *
   * @param override indicates if the source image needs to be overridden when applying this
   *                 operation
   */
  public VerticalFlip(boolean override) {
    super(override);
  }

  /**
   * This method takes in a model and either applies the transformation of the specified operation
   * implementation in this method or indicates to the model that the change being made is the
   * specified function object and allows the model to change its state accordingly.
   *
   * @param image the model that is being passed to be changed
   * @throws IllegalStateException when any of the function object implementations encounters
   *                               an invalid state of the model based on arguments given to it
   *                               (specified in each implementations' documentation)
   */
  @Override
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException {
    return image.verticalFlip(this.override);
  }
}
