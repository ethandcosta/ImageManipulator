package model;

/**
 * This class represents a single pixel on an image with a certain color.
 */
public abstract class Pixel {
  protected int x; // represents the x coordinate of a given pixel in an image (top right is origin)
  protected int y; // represents the y coordinate of a given pixel in an image
  public int maxValue; // the max-value that a channel of the pixel can contain
  // (usually 1 for BW and 255 for RGB)

  /**
   * This constructor instantiates a new pixel in the image being given with a max value for the
   * pixel channels' values as well as their location in cartesian coordinates in the image.
   *
   * @param x        represents the x coordinate of a given pixel in an image (top right is origin)
   * @param y        represents the y coordinate of a given pixel in an image
   * @param maxValue the max-value that a channel of the pixel can contain
   *                 (usually 1 for BW and 255 for RGB)
   */
  protected Pixel(int x, int y, int maxValue) {
    this.x = x;
    this.y = y;
    this.maxValue = maxValue;
  }

  /**
   * Returns the value of one of the pixel's color channels.
   *
   * @param color the color channel of the Pixel whose value you want.
   * @return int value of the pixel's color channel.
   * @throws IllegalArgumentException when color is not a valid option
   *                                  (not r,g, or b or maxvalue)
   */
  public abstract int getColorValue(String color) throws IllegalArgumentException;

  /**
   * Modifies a pixel's R,G,or B value to value.
   *
   * @param color R, G, or B
   * @param value value to modify color to
   */
  protected abstract void setColor(String color, int value);

  /**
   * Provides the largest channel value.
   *
   * @return the largest value of redValue, greenValue, and blueValue.
   */
  protected abstract double getValue();

  /**
   * Returns the weighted average of all the RGB values, known as luma.
   *
   * @return double representing the luma of an RBG pixel.
   */
  protected abstract double getLuma();

  /**
   * Returns the intensity of the RBG Pixel.
   *
   * @return the average of the R,G, and B values.
   */
  protected abstract double getIntensity();

}