package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageProcessor;
import model.Pixel;
import model.RGBPixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the PPM file.
   * @return the 2D array of pixels representing an image
   * @throws IllegalStateException if the file is not found or is not a valid PPM file
   */
  public static Pixel[][] processPPM(String filename) throws IllegalStateException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] arr = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        arr[i][j] = new RGBPixel(j, i, maxValue, r, g, b);
      }
    }
    return arr;
  }

  /**
   * This method processes a non-PPM file into a Pixel[][] directly by accessing
   * each RGB value of the non-PPM image file as a BufferedImage.
   *
   * @param filename the file name where the file being read is contained
   * @return a 2D Pixel array that will be housed in the model
   */
  public static Pixel[][] processBufferedImage(String filename) {
    Pixel[][] arr;
    try {
      FileInputStream inputStream = new FileInputStream(filename);
      BufferedImage inputImage = ImageIO.read(inputStream);
      arr = new Pixel[inputImage.getHeight()][inputImage.getWidth()];
      for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr[0].length; j++) {
          int rgb = inputImage.getRGB(j, i);
          Color color = new Color(rgb);
          int red = color.getRed();
          int blue = color.getBlue();
          int green = color.getGreen();
          arr[i][j] = new RGBPixel(j, i, 255, red, green, blue);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    return arr;
  }

  /**
   * This method is a general-purpose I/O method that handles saving PPM images to the system
   * given a particular model to save and a location in the system to save it. It is heavily
   * used by the model for the save operation.
   * @param path the file path to which the PPM will be stored on the system
   * @param model the model which houses the image that will be saved as a PPM
   * @throws IllegalStateException if the method encounters a transmission problem
   */
  public static void savePPM(String path, ImageProcessor model) throws IllegalStateException {
    try {
      Path paths = Paths.get(path);
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
  }

  /**
   * This method is a general-purpose I/O method that handles saving non-PPM images to the system
   * given a particular model to save and a location in the system to save it. It is heavily
   * used by the model for the save operation.
   * @param path the file path to which the PPM will be stored on the system
   * @param model the model which houses the image that will be saved as whatever filetype
   *              the path has specified
   * @throws IllegalStateException if the method encounters a transmission problem
   */
  public static void saveBufferedImage(String path, ImageProcessor model)
          throws IllegalStateException {
    String fileType = path.substring(path.indexOf(".") + 1);
    try {
      Path paths = Paths.get(path);
      Files.deleteIfExists(paths);
      Files.writeString(paths, "");
      File outputFile = new File(path);

      BufferedImage outputImage = new BufferedImage(model.getImage()[0].length,
              model.getImage().length, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < model.getImage().length; i++) {
        for (int j = 0; j < model.getImage()[0].length; j++) {
          int red = util.Util.clampValue(0, model.getImage()[i][j].getColorValue("r"),
                  255);
          int green = util.Util.clampValue(0, model.getImage()[i][j].getColorValue("g"),
                  255);
          int blue = util.Util.clampValue(0, model.getImage()[i][j].getColorValue("b"),
                  255);
          Color color = new Color(red, green, blue);
          outputImage.setRGB(j, i, color.getRGB());

        }
      }
      ImageIO.write(outputImage, fileType, outputFile);
    } catch (IOException ie) {
      throw new IllegalStateException("Transmission error upon writing file");
    }
  }
}
