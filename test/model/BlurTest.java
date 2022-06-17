package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Blur function object.
 */
public class BlurTest {

  Operations blur;
  ImageProcessor model;
  Pixel[][] ogImage;
  Pixel[][] blurred;

  @Before
  public void init() {
    blur = new Blur(false);
    ogImage = new Pixel[3][3];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 16, 16, 16);
    ogImage[0][1] = new RGBPixel(1, 0, 255, 8, 8, 8);
    ogImage[0][2] = new RGBPixel(2, 0, 255, 16, 16, 16);
    ogImage[1][0] = new RGBPixel(0, 1, 255, 8, 8, 8);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 4, 4, 4);
    ogImage[1][2] = new RGBPixel(2, 1, 255, 8, 8, 8);
    ogImage[2][0] = new RGBPixel(0, 2, 255, 16, 16, 16);
    ogImage[2][1] = new RGBPixel(1, 2, 255, 8, 8, 8);
    ogImage[2][2] = new RGBPixel(2, 2, 255, 16, 16, 16);
    model = new ImageProcessorModel(ogImage, "ogImage");
    blurred = new Pixel[3][3];
    blurred[0][0] = new RGBPixel(0, 0, 255, 6, 6, 6);
    blurred[0][1] = new RGBPixel(1, 0, 255, 7, 7, 7);
    blurred[0][2] = new RGBPixel(2, 0, 255, 6, 6, 6);
    blurred[1][0] = new RGBPixel(0, 1, 255, 7, 7, 7);
    blurred[1][1] = new RGBPixel(1, 1, 255, 9, 9, 9);
    blurred[1][2] = new RGBPixel(2, 1, 255, 7, 7, 7);
    blurred[2][0] = new RGBPixel(0, 2, 255, 6, 6, 6);
    blurred[2][1] = new RGBPixel(1, 2, 255, 7, 7, 7);
    blurred[2][2] = new RGBPixel(2, 2, 255, 6, 6, 6);
  }

  @Test
  public void apply() {
    Pixel[][] result = blur.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(blurred[i][j].getColorValue("r"), result[i][j].getColorValue("r"));
        assertEquals(blurred[i][j].getColorValue("g"), result[i][j].getColorValue("g"));
        assertEquals(blurred[i][j].getColorValue("b"), result[i][j].getColorValue("b"));
      }
    }
  }
}