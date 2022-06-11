package model;

/**
 * This class represents a Pixel that handles and models the state of
 * a black and white pixel with a greyness scale.
 */
public class BWPixel extends Pixel {
  double bWValue; // represents the black (0) to white (1) greyness scale value

  /**
   * This constructor instantiates a new pixel in the image being given as a black and white
   * pixel with a location on the image denoted by cartesian coordinates and a max range of values
   * from 0-1 and the actual value of the pixel.
   *
   * @param x        represents the x coordinate of a given pixel in an image (top right is origin)
   * @param y        represents the y coordinate of a given pixel in an image
   * @param maxValue the max-value that a channel of the pixel can contain
   *                 (usually 1 for BW and 255 for RGB)
   * @param bWValue  represents a value on the greyness scale ranging from black (0) to white (1)
   */
  protected BWPixel(int x, int y, int maxValue, double bWValue) {
    super(x, y, maxValue);
    this.bWValue = bWValue;
  }

  /**
   * Returns the intensity of BW channel.
   *
   * @return
   */
  public double getBWValue() {
    return this.bWValue;
  }

  @Override
  public int getColorValue(String color) throws IllegalArgumentException {
    throw new IllegalArgumentException("Function not supported for BW-Pixel");
  }

  @Override
  protected void setColor(String color, int value) {
    this.bWValue = value;
  }

  @Override
  protected double getValue() throws IllegalStateException {
    throw new IllegalStateException("Function not supported for BW-Pixel");
  }

  @Override
  protected double getLuma() throws IllegalStateException {
    throw new IllegalStateException("Function not supported for BW-Pixel");
  }

  @Override
  protected double getIntensity() throws IllegalStateException {
    throw new IllegalStateException("Function not supported for BW-Pixel");
  }

}
