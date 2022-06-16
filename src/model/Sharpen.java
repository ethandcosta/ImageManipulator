package model;

/**
 * This class represents an implementation of a sharpen image operation which
 * is a function object that sharpens the image by applying a filter
 * to each of the images pixels and creates a copy of the modified image
 * with a new given name being the image name.
 */
public class Sharpen extends AbstractFilterableOperations {

  /**
   * This constructor instantiates a new Operations function object of sharpen which
   * is a filter applied to sharpen all the pixels of an image in a Gaussian distribution.
   * It is the antithesis of blurring.
   * @param override if the current model's image will be overridden with the new image created
   */
  public Sharpen(boolean override) {
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
    return image.sharpen(this.override);
  }
}
