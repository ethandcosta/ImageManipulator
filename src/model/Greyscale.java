package model;

/**
 * This class represents an implementation of a greyscale image operation which
 * is a function object that greyscales a given image in the model by fixing a pixels channels
 * of a given image to the luma of that pixel.
 * This will create a copy of the modified image with the new given name being the image name.
 */
public class Greyscale extends AbstractFilterableOperations {

  /**
   * This constructor instantiates a new Operations function object of greyscale which
   * is a filter applied to greyscale all the pixels of an image based off components or luma.
   * @param override if the current model's image will be overridden with the new image created
   */
  public Greyscale(boolean override) {
    super(override);
  }

  /**
   * This method takes in a model and indicates to the model that the change being made is the
   * specified function object and allows the model to change its state accordingly.
   *
   * @param image the model that is being passed to be changed
   * @throws IllegalStateException when any of the function object implementations encounters
   *                               an invalid state of the model based on arguments given to it
   *                               (specified in each implementations' documentation)
   */
  @Override
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException {
    return image.greyscaleFilter(this.override);
  }
}
