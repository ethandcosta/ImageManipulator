package model;

/**
 * This class represents an implementation of a save image operation which is a function object
 * that writes the contents of the current image in the model to a specified file path
 * with the new name of the file being the image name.
 */
public class Save extends AbstractOperations {
  private final String path;
  private final ImageProcessor model;

  /**
   * This constructor instantiates the Save function object which takes in a path to save the image
   * to a location in the file system with the given model's image.
   *
   * @param path     the path from which the program will attempt to load the image
   * @param model    the model whose image will be used to save a new PPM file
   * @param override indicates if the source image needs to be overridden when applying this
   *                 operation
   */
  public Save(String path, ImageProcessor model, boolean override) {
    super(override);
    this.path = util.Util.checkNullness(path);
    this.model = util.Util.checkNullness(model);
  }

  @Override
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException {
    return image.save(this.path, this.model);
  }
}
