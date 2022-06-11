package model;

/**
 * This class represents a Pixel that handles and models the state of
 * a colored pixel with RBG values.
 */
public class RGBPixel extends Pixel {

  int redValue;
  int greenValue;
  int blueValue;

  /**
   * This constructor instantiates a new pixel in the image being given as a color
   * pixel with a location on the image denoted by cartesian coordinates and a max range of values
   * and the actual value of the pixel.
   *
   * @param x          represents the x coordinate of a given pixel in an image
   *                   (top right is origin)
   * @param y          represents the y coordinate of a given pixel in an image
   * @param maxValue   the max-value that a channel of the pixel can contain
   *                   (usually 1 for BW and 255 for RGB)
   * @param redValue   the red component of the color of the pixel, the r in RGB
   * @param greenValue the green component of the color of the pixel, the g in RGB
   * @param blueValue  the blue component of the color of the pixel, the b in RGB
   */
  public RGBPixel(int x, int y, int maxValue, int redValue, int greenValue, int blueValue) {
    super(x, y, maxValue);
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
  }

  @Override
  public double getLuma() {
    return 0.2126 * redValue + 0.7152 * greenValue + 0.0722 * blueValue;
  }

  @Override
  public double getValue() {
    return Math.max(Math.max(redValue, greenValue), blueValue);
  }

  @Override
  public double getIntensity() {
    return (redValue + blueValue + greenValue) / 3.0;
  }

  @Override
  public void setColor(String color, int value) {
    switch (color) {
      case "r":
        this.redValue = value;
        break;
      case "b":
        this.blueValue = value;
        break;
      case "g":
        this.greenValue = value;
        break;
      default:
        break;
    }
  }

  @Override
  public int getColorValue(String color) throws IllegalArgumentException {
    switch (color) {
      case "r":
        return this.redValue;
      case "b":
        return this.blueValue;
      case "g":
        return this.greenValue;
      case "maxval":
        return this.maxValue;
      default:
        throw new IllegalArgumentException("not a valid color");
    }
  }


}