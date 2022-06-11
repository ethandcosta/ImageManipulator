package model;

import java.util.ArrayList;
import java.util.List;

import util.Util;

/**
 * This class represents an abstraction of the different types of image processors
 * in this program.
 */
public abstract class AbstractImageProcessorModel implements ImageProcessor {

  protected Pixel[][] image;
  protected List<Operations> supportedOps;
  protected int imageHeight;
  protected int imageWidth;
  protected int maxValue;
  protected String imageName;

  /**
   * Default constructor that just instantiates the
   * type of the model when the constructor first creates it.
   * A different constructor will be called later when the constructor
   * receives the image.
   */
  protected AbstractImageProcessorModel() {
    this.supportedOps = new ArrayList<Operations>();
  }

  /**
   * This constructor instantiates the ImageProcessorModel creates a new image as an empty
   * 2D array of pixels and then initializes it based on the image height and image width.
   * This constructor creates a blank list of supported operations by default.
   *
   * @param imageHeight the image height
   * @param imageWidth  the image width
   */
  protected AbstractImageProcessorModel(int imageHeight, int imageWidth, String imageName) {
    this.image = new Pixel[imageHeight][imageWidth];
    this.imageHeight = imageHeight;
    this.imageWidth = imageWidth;
    this.supportedOps = new ArrayList<Operations>();
    this.maxValue = image[0][0].maxValue;
    this.imageName = Util.checkNullness(imageName);
  }

  /**
   * This constructor instantiates the ImageProcessorModel creates a new image as an empty
   * 2D array of pixels and then initializes it based on the image height and image width.
   * This constructor allows for a custom list of operations to be passed as an argument.
   *
   * @param image     the image to be processed in the format of a 2D-Array.
   * @param imageName the name of the image given
   * @throws IllegalArgumentException if any of the arguments is null
   */
  protected AbstractImageProcessorModel(Pixel[][] image, String imageName)
          throws IllegalArgumentException {
    this.image = Util.checkNullness(image);
    this.imageHeight = image.length;
    this.imageWidth = image[0].length;
    this.imageName = Util.checkNullness(imageName);
    this.maxValue = image[0][0].maxValue;
    this.supportedOps = new ArrayList<Operations>();
  }

  /**
   * For the controller to set the model's stored image value
   * to what it desires. The user can modify what image is being
   * currently processed by the model like this.
   *
   * @param image The image for the model to process in a 2D array
   *              of pixels.
   */
  public void setImage(Pixel[][] image) {
    this.image = image;
  }

  /**
   * Uses grayscale to highlight the intensity of a certain
   * color in the image.
   *
   * @param color the color to highlight
   * @return new 2D Array of Pixels where all other color channels
   *         are set to the highlighted color Ex: Highlighting red in grayscale
   *         would set every pixel's G and B vals to what R is.
   */
  @Override
  public abstract Pixel[][] grayScaleHighlight(String color, boolean overwrite);

  /**
   * Future plan to add supported operation to model.
   *
   * @param o Object to be added.
   */
  @Override
  public void addOperations(Operations o) {
    this.supportedOps.add(o);
  }

  /**
   * Future plan to remove supported operation to model.
   *
   * @param o Object to be removed.
   */
  @Override
  public void removeOperations(Operations o) {
    this.supportedOps.remove(o);
  }

  /**
   * Retrieves the current image with editing.
   *
   * @return this's image.
   */
  @Override
  public Pixel[][] getImage() {
    return this.image;
  }

  /**
   * Retrieves the max value of the pixels in the image.
   *
   * @return the max value of the pixels
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public String getImageName() {
    return this.imageName;
  }
}