package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the Sepia function object.
 */
public class SepiaTest {

  Operations sepia;
  ImageProcessor model;
  Pixel[][] ogImage;
  Pixel[][] sepiaed;

  @Before
  public void init() {
    sepia = new Sepia(false);
    ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 50, 100, 200);
    ogImage[0][1] = new RGBPixel(1, 0, 255, 100, 150, 200);
    ogImage[1][0] = new RGBPixel(0, 1, 255, 20, 40, 60);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 80, 160, 240);
    model = new ImageProcessorModel(ogImage, "ogImage");
    sepiaed = new Pixel[2][2];
    sepiaed[0][0] = new RGBPixel(0, 0, 255, 134, 119, 93);
    sepiaed[0][1] = new RGBPixel(1, 0, 255, 192, 171, 133);
    sepiaed[1][0] = new RGBPixel(0, 1, 255, 49, 44, 34);
    sepiaed[1][1] = new RGBPixel(1, 1, 255, 199, 178, 138);
  }

  @Test
  public void apply() {
    Pixel[][] result = sepia.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(sepiaed[i][j].getColorValue("r"), result[i][j].getColorValue("r"));
        assertEquals(sepiaed[i][j].getColorValue("g"), result[i][j].getColorValue("g"));
        assertEquals(sepiaed[i][j].getColorValue("b"), result[i][j].getColorValue("b"));
      }
    }
  }
}