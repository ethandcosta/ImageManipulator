package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Brighten operation.
 */
public class BrightenTest {
  Brighten brighten;
  Brighten darken;
  ImageProcessorModel model;
  Pixel[][] ogImage;
  Pixel[][] brighter;
  Pixel[][] darker;

  @Before
  public void init() {
    brighten = new Brighten(10, false);
    darken = new Brighten(-10, false);
    ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    model = new ImageProcessorModel(ogImage, "ogImage");
    brighter = new Pixel[2][2];
    brighter[0][1] = new RGBPixel(0, 1, 255, 20, 30, 40);
    brighter[0][0] = new RGBPixel(0, 0, 255, 30, 20, 40);
    brighter[1][1] = new RGBPixel(1, 1, 255, 40, 30, 40);
    brighter[1][0] = new RGBPixel(1, 0, 255, 55, 42, 22);
    darker = new Pixel[2][2];
    darker[0][0] = new RGBPixel(0, 0, 255, 0, 10, 20);
    darker[0][1] = new RGBPixel(0, 1, 255, 10, 0, 20);
    darker[1][0] = new RGBPixel(1, 0, 255, 20, 10, 20);
    darker[1][1] = new RGBPixel(1, 1, 255, 35, 22, 2);
  }

  @Test
  public void testBrighten() {
    Pixel[][] result = brighten.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), brighter[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), brighter[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), brighter[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testDarken() {
    Pixel[][] result = darken.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), darker[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), darker[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), darker[i][j].getColorValue("b"));
      }
    }
  }

}
