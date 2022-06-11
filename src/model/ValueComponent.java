package model;

/**
 * This class represents an implementation of a greyscale component image operation which
 * is a function object that greyscales a given image in the model by fixing a specific channel
 * or attribute of a given image and setting all other channels to that value.
 * This will create a copy of the modified image with the new given name being the image name.
 */
public class ValueComponent extends AbstractOperations {

  String mode;

  /**
   * This class represents the enumerations of the possible greyscale option operations.
   */
  private enum ComponentOptions {
    Red("Red"),
    Green("Green"),
    Blue("Blue"),
    Value("Value"),
    Intensity("Intensity"),
    Luma("Luma");

    private final String descriptor;

    public String toString() {
      return descriptor;
    }


    ComponentOptions(String d) {
      this.descriptor = d;
    }
  }

  /**
   * This constructor instantiates the ValueComponent function object which takes in an image name
   * to greyscale the image by a certain mode and indexing the image under a new name.
   *
   * @param mode     the specific component or attribute to peg other pixels' channels to
   *                 represented
   *                 as a string of the name of the channel
   * @param override indicates if the source image needs to be overridden when applying this
   *                 operation
   */
  public ValueComponent(String mode, boolean override)
          throws IllegalArgumentException {
    super(override);
    for (ComponentOptions i : ComponentOptions.values()) {
      if (i.toString().equals(mode)) {
        this.mode = mode;
      }
    }
    util.Util.checkNullness(this.mode);
  }

  @Override
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException {
    return image.grayScaleHighlight(this.mode, this.override);
  }


}
