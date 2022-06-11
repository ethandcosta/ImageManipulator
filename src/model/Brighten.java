package model;

/**
 * This class represents an implementation of a brighten image operation which
 * is a function object that brightens or darkens the given image name in the model
 * by raising/lowering each pixels' rgb values and creates a copy of the modified image
 * with a new given name being the image name.
 */
public class Brighten extends AbstractOperations {
  int increment;

  /**
   * This constructor instantiates the Brighten function object which takes in an image name to
   * load the brighten or darken by a certain amount or increment and saving the image under a new
   * name.
   *
   * @param increment the amount by which the values of the image's channels will increase or
   *                  decrease by in brightness
   * @param override  indicates if the source image needs to be overridden when applying this
   *                  operation
   */
  public Brighten(int increment, boolean override) {
    super(override);
    this.increment = increment;
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
    return image.brighten(this.increment, this.override);
  }
}
