package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import view.Histogram;

/**
 * This class represents an implementation of a load image operation which is a function object
 * that writes the contents of a specified PPM file path to the model
 * with the new name of the file being the image name of the model and the pixels of the file
 * converted to pixels in the model's image.
 */
public class Load extends AbstractOperations {
  private String path;
  protected String sourceName;

  /**
   * This constructor instantiates the Load function object which takes in a path to load the image
   * onto this program with the given image name. The destination name of a loaded image is set
   * to an empty string as it does not have a destination and only a source name.
   *
   * @param path       the path from which the program will attempt to load the image
   * @param sourceName the image name that the loaded picture will have
   * @param override   indicates if the source image needs to be overridden when applying this
   *                   operation
   * @throws IllegalArgumentException if the given path does not exist for the image
   */
  public Load(String path, String sourceName, boolean override) throws IllegalArgumentException {
    super(override);
    this.sourceName = util.Util.checkNullness(sourceName);
    Path p = Paths.get(path);
    if (Files.exists(p)) {
      this.path = path;
    } else {
      throw new IllegalArgumentException("Please enter a valid path for your image");
    }
  }

  @Override
  public Pixel[][] apply(ImageProcessor image) throws IllegalStateException {
    return image.load(this.sourceName, this.path);
  }
}
