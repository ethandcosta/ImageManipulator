package model;


import java.util.Objects;

/**
 * a Mock of the model class for testing purposes.
 */
public class MockModel extends AbstractImageProcessorModel {

  final StringBuilder log;

  /**
   * Constructs a mock model for testing.
   *
   * @param log the string builder to which responses will be loaded onto
   */
  public MockModel(StringBuilder log) {
    super();
    this.log = Objects.requireNonNull(log);
    image = new Pixel[2][2];
    image[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    image[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    image[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    image[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    imageHeight = 3;
    imageWidth = 3;
    maxValue = 255;
    imageName = "sunset";
  }

  @Override
  public void addOperations(Operations o) {
    log.append("added operation");
  }

  @Override
  public void removeOperations(Operations o) {
    log.append("removed operation");
  }

  @Override
  public String getImageName() {
    return imageName;
  }

  @Override
  public Pixel[][] getImage() {
    return null;
  }

  @Override
  public Pixel[][] greyscaleFilter(boolean override) {
    return null;
  }

  @Override
  public Pixel[][] sharpen(boolean override) {
    return null;
  }

  @Override
  public Pixel[][] blur(boolean override) {
    return null;
  }

  @Override
  public Pixel[][] sepia(boolean override) {
    return null;
  }

  /**
   * Retrieves the max value of the pixels in the image.
   *
   * @return the max value of the pixels
   */
  @Override
  public int getMaxValue() {
    return 0;
  }

  @Override
  public Pixel[][] verticalFlip(boolean override) {
    log.append("did vertical flip\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }

  @Override
  public Pixel[][] horizontalFlip(boolean override) {
    log.append("did horizontal flip\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }

  @Override
  public Pixel[][] brighten(int increment, boolean override) {
    log.append("brightened by " + increment + "\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }

  @Override
  public Pixel[][] save(String path, ImageProcessor model) {
    log.append("saved " + path + "\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }

  @Override
  public Pixel[][] load(String sourceName, String path) {
    log.append("loaded " + sourceName + "\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }

  @Override
  public Pixel[][] grayScaleHighlight(String color, boolean override) {
    log.append("passed to " + color + "\n");
    Pixel[][] ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    return ogImage;
  }
}
