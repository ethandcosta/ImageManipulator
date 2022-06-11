package model;

/**
 * This interface represents a Photoshop-style image processor that can modify an image that it
 * stores.
 */
public interface ImageProcessor {

  /**
   * This method adds an operation to this model to indicate the number of supported operations.
   *
   * @param o the operation being added
   */
  public void addOperations(Operations o);

  /**
   * This method removes an operation to this model to indicate the number of supported operations.
   *
   * @param o the operation being removed
   */
  public void removeOperations(Operations o);

  /**
   * Retrieves the current image with editing.
   *
   * @return this's image.
   */
  public Pixel[][] getImage();

  /**
   * Retrieves the max value of the pixels in the image.
   *
   * @return the max value of the pixels
   */
  public int getMaxValue();

  /**
   * Retrieves the name of the image.
   *
   * @return the name of the image that is being stored currently in the processor
   */
  public String getImageName();

  /**
   * This method applies the vertical flip command by mutating a copy of this model's image and
   * flipping the pixels locations on the pictures y-axis.
   *
   * @param override whether the new produced image should override the current model's image
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] verticalFlip(boolean override);

  /**
   * This method applies the horizontal flip command by mutating a copy of this model's image and
   * flipping the pixels locations on the pictures x-axis.
   *
   * @param override whether the new produced image should override the current model's image
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] horizontalFlip(boolean override);

  /**
   * This method applies the brighten command by mutating a copy of this model's image and
   * increments the rgb values of its pixels by a certain amount.
   *
   * @param increment the amount by which to raise or lower the brightness of the image
   * @param override  whether the new produced image should override the current model's image
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] brighten(int increment, boolean override);

  /**
   * This method applies the save command by saving copy of this an image to a certain file
   * path.
   *
   * @param path  the path to save the model
   * @param model the model whose image is being saved as a PPM
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] save(String path, ImageProcessor model);

  /**
   * This method applies the load command by mutating a copy of this model's image and
   * increments the rgb values of its pixels by a certain amount.
   *
   * @param sourceName the amount by which to raise or lower the brightness of the image
   * @param path       whether the new produced image should override the current model's image
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] load(String sourceName, String path);

  /**
   * This method applies the greyscale command by mutating a copy of this model's image and
   * increments the rgb values of its pixels to match the given color's value at that pixel.
   *
   * @param color    the color component being used as the foundation for the greyscale
   * @param override whether the new produced image should override the current model's image
   * @return the new image as a 2D array of Pixels
   */
  public Pixel[][] grayScaleHighlight(String color, boolean override);

}


