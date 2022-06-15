package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreyscaleTest {

  Operations greyscale;
  ImageProcessor model;
  Pixel[][] ogImage;
  Pixel[][] greyscaleImage;

  @Before
  public void init() {
    greyscale = new Greyscale(false);
    ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 0, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(0, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(0, 0, 255, 45, 32, 12);
    model = new ImageProcessorModel(ogImage, "ogImage");
    greyscaleImage = new Pixel[2][2];
    greyscaleImage[0][0] = new RGBPixel(0, 0, 255, 18, 18, 18);
    greyscaleImage[0][1] = new RGBPixel(0, 0, 255, 13, 13, 13);
    greyscaleImage[1][0] = new RGBPixel(0, 0, 255, 22, 22, 22);
    greyscaleImage[1][1] = new RGBPixel(0, 0, 255, 33, 33, 33);
  }

  @Test
  public void apply() {
    Pixel[][] result = greyscale.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(greyscaleImage[i][j].getColorValue("r"), result[i][j].getColorValue("r"));
        assertEquals(greyscaleImage[i][j].getColorValue("g"), result[i][j].getColorValue("g"));
        assertEquals(greyscaleImage[i][j].getColorValue("b"), result[i][j].getColorValue("b"));
      }
    }
  }
}