package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class represents the model of the image processor program. As the model, the logic
 * of the actual processing and image manipulation will occur in this class which will change
 * the state of the image represented as a 2D array.
 */
public class ImageProcessorModel extends AbstractImageProcessorModel {

  /**
   * This constructor represents an empty processor which is instantiated when the controller
   * first starts receiving input and will remain empty until an image and its attributes
   * are loaded.
   */
  public ImageProcessorModel() {
    super();
  }

  /**
   * Constructor instantiates the IPModel by defining the image it wants to
   * do operations on and modify with its methods.
   *
   * @param image     the image array being added to this processor
   * @param imageName the name of the image being given
   */
  public ImageProcessorModel(Pixel[][] image, String imageName) {
    super(image, imageName);
  }

  /**
   * Constructor instantiates the IPModel by defining the image it wants to
   * do operations on and modify with its methods as well as max value for the pixels of the image.
   *
   * @param image    the image array being added to this processor
   * @param maxValue the max value of that a pixel value can hold in this processor's image
   * @oaram imageName the name of the image being passed
   */
  public ImageProcessorModel(Pixel[][] image, String imageName, int maxValue) {
    super(image, imageName);
    this.maxValue = maxValue;
  }

  @Override
  public Pixel[][] grayScaleHighlight(String color, boolean override) {

    //creates temp image to manipulate.
    Pixel[][] temp = this.generateTemp();

    //sets all color values to highlighter channel.
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        int val = 0;
        switch (color) {
          case "Red":
            val = image[i][j].getColorValue("r");
            break;
          case "Blue":
            val = image[i][j].getColorValue("b");
            break;
          case "Green":
            val = image[i][j].getColorValue("g");
            break;
          case "Luma":
            val = (int) image[i][j].getLuma();
            break;
          case "Intensity":
            val = (int) image[i][j].getIntensity();
            break;
          case "Value":
            val = (int) image[i][j].getValue();
            break;
          default:
            break;
        }
        temp[i][j].setColor("b", val);
        temp[i][j].setColor("r", val);
        temp[i][j].setColor("g", val);
      }
    }
    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  @Override
  public Pixel[][] greyscaleFilter(boolean override) {
    Pixel[][] temp = this.generateTemp();
    double[][] greyscale = {{.2126, .7152, .0722}, {.2126, .7152, .0722}, {.2126, .7152, .0722}};

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        temp[i][j] = this.multiplyMatrix(i, j, greyscale);
      }
    }

    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  @Override
  public Pixel[][] sharpen(boolean override) {
    Pixel[][] temp = this.generateTemp();
    double[][] sharpen = {{-.125, -.125, -.125, -.125, -.125}, {-.125, .25, .25, .25, -.125},
            {-.125, .25, 1, .25, -.125}, {-.125, .25, .25, .25, -.125},
            {-.125, -.125, -.125, -.125, -.125}};

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        temp[i][j] = this.applyFilter(i, j, sharpen);
      }
    }

    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  @Override
  public Pixel[][] blur(boolean override) {
    Pixel[][] temp = this.generateTemp();
    double[][] blur = {{.0625, .125, .0625}, {.125, .25, .125}, {.0625, .125, .0625}};

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        temp[i][j] = this.applyFilter(i, j, blur);
      }
    }

    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  // applies a given filter for a given Pixel denoted by its x and y position on the image
  // returns a new Pixel with the applied changes
  private Pixel applyFilter(int x, int y, double[][] filter) throws IndexOutOfBoundsException {
    double red = 0;
    double blue = 0;
    double green = 0;

    int filterHalfSize = filter.length / 2;

    int i = 0;
    int j = 0;
    while (i < filter.length) {
      while (j < filter[0].length) {
        try {
          red += image[x - filterHalfSize + i][y - filterHalfSize + j].getColorValue("r")
                  * filter[i][j];
          green += image[x - filterHalfSize + i][y - filterHalfSize + j].getColorValue("g")
                  * filter[i][j];
          blue += image[x - filterHalfSize + i][y - filterHalfSize + j].getColorValue("b")
                  * filter[i][j];
          j++;
        } catch (IndexOutOfBoundsException ie) {
          j++;
        }
      }
      i++;
      j = 0;
    }
    return new RGBPixel(x, y, this.getMaxValue(), (int) red, (int) green, (int) blue);
  }

  // does matrix multiplication on the given pixel at its x and y location with another
  // given matrix and return a new transformed pixel
  // very specific for this assignment
  private Pixel multiplyMatrix(int x, int y, double[][] filter) throws IllegalArgumentException {
    if (filter.length != 3 && filter[0].length != 3) {
      throw new IllegalArgumentException("Matrix for RGB color transformation must be 3x3!");
    }

    double[] oldPixel = {this.image[x][y].getColorValue("r"), this.image[x][y].getColorValue("g"),
            this.image[x][y].getColorValue("b")};
    double[] newPixel = new double[3];

    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter[i].length; j++) {
        newPixel[i] += filter[i][j] * oldPixel[j];
      }
    }
    return new RGBPixel(x, y, this.getMaxValue(), (int) newPixel[0], (int) newPixel[1],
            (int) newPixel[2]);
  }

  @Override
  public Pixel[][] sepia(boolean override) {
    Pixel[][] temp = this.generateTemp();
    double[][] sepia = {{.393, .769, 0.189}, {.349, .686, .168}, {.272, .534, .131}};

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        temp[i][j] = this.multiplyMatrix(i, j, sepia);
      }
    }

    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  /**
   * Overwrites the current image with the given image.
   *
   * @param given the 2D array to replace this.image with.
   */
  private void overwriteImage(Pixel[][] given) {
    this.image = given;
    this.maxValue = given[0][0].maxValue;
  }

  @Override
  public Pixel[][] brighten(int increment, boolean override) {
    Pixel[][] temp = this.generateTemp();
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        temp[i][j].setColor("r", this.checkifLimits(image[i][j].getColorValue("r")
                + increment));
        temp[i][j].setColor("g", this.checkifLimits(image[i][j].getColorValue("g")
                + increment));
        temp[i][j].setColor("b", this.checkifLimits(image[i][j].getColorValue("b")
                + increment));
      }
    }
    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  /**
   * Makes sure set color value does not fall out of bounds.
   *
   * @param value color value that is desired to be set to
   * @return 0 if value is negative,maxVal if greater than maxVal, or value
   * if neither.
   */
  private int checkifLimits(int value) {
    if (value < 0) {
      return 0;
    } else if (value > maxValue) {
      return maxValue;
    } else {
      return value;
    }
  }

  @Override
  public Pixel[][] verticalFlip(boolean override) {
    Pixel[][] temp = this.generateTemp();
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        temp[i][j] = new RGBPixel(j, i, temp[i][j].getColorValue("maxval"),
                temp[(imageHeight - 1) - i][j].getColorValue("r"),
                temp[(imageHeight - 1) - i][j].getColorValue("g"),
                temp[(imageHeight - 1) - i][j].getColorValue("b"));
      }
    }
    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  @Override
  public Pixel[][] horizontalFlip(boolean override) {
    Pixel[][] temp = this.generateTemp();

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        temp[i][j] = new RGBPixel(j, i, temp[i][j].getColorValue("maxval"),
                temp[i][(imageWidth - 1) - j].getColorValue("r"),
                temp[i][(imageWidth - 1) - j].getColorValue("g"),
                temp[i][(imageWidth - 1) - j].getColorValue("b"));
      }
    }
    if (override) {
      this.overwriteImage(temp);
    }
    return temp;
  }

  @Override
  public Pixel[][] save(String path, ImageProcessor model) {

    if (path.charAt(path.length() - 1) == '\\') {
      path = path.substring(0, path.length() - 1);
    }

    Path paths = Paths.get(path);

    try {
      Files.deleteIfExists(paths);

      // write the header
      StringBuilder fileWriter = new StringBuilder();
      fileWriter.append("P3\n")
              .append(model.getImage()[0].length)
              .append(" ")
              .append(model.getImage().length)
              .append("\n")
              .append(model.getMaxValue())
              .append("\n");
      for (int i = 0; i < model.getImage().length; i++) {
        for (int j = 0; j < model.getImage()[0].length; j++) {
          fileWriter.append(model.getImage()[i][j].getColorValue("r") + "\n");
          fileWriter.append(model.getImage()[i][j].getColorValue("g") + "\n");
          fileWriter.append(model.getImage()[i][j].getColorValue("b") + "\n");
        }
      }
      Files.writeString(paths, fileWriter.toString(), StandardCharsets.UTF_8);
    } catch (IOException ie) {
      throw new IllegalStateException("Transmission error upon writing file");
    }
    return this.image;
  }

  @Override
  public Pixel[][] load(String sourceName, String path) {
    Pixel[][] temp = util.ImageUtil.processPPM(path);
    this.overwriteImage(temp);
    this.imageName = sourceName;
    this.imageHeight = this.image.length;
    this.imageWidth = this.image[0].length;
    return temp;
  }

  /**
   * Generates temporary version of the image to
   * manipulate with operations.
   */
  private Pixel[][] generateTemp() {
    Pixel[][] temp = new Pixel[imageHeight][imageWidth];
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        temp[i][j] = new RGBPixel(j, i, image[i][j].getColorValue("maxval"),
                image[i][j].getColorValue(
                        "r"), image[i][j].getColorValue(
                "g"), image[i][j].getColorValue("b"));
      }
    }
    return temp;
  }

}