package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
          int red = (rgb >> 16) & 0x000000FF;
          int green = (rgb >> 8) & 0x000000FF;
          int blue = (rgb) & 0x000000FF;
          arr[i][j] = new RGBPixel(j, i, 255, red, green, blue);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    return arr;
  }


}
