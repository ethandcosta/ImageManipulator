package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests Vertical Flip function.
 */
public class VerticalFlipTest {
  VerticalFlip flip;
  ImageProcessorModel model;
  Pixel[][] ogImage;
  Pixel[][] flipped;

  @Before
  public void init() {
    flip = new VerticalFlip(false);
    ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 1, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(1, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(1, 1, 255, 45, 32, 12);
    model = new ImageProcessorModel(ogImage, "ogImage");
    flipped = new Pixel[2][2];
    flipped[0][0] = new RGBPixel(0, 0, 255, 30, 20, 30);
    flipped[0][1] = new RGBPixel(0, 1, 255, 45, 32, 12);
    flipped[1][0] = new RGBPixel(1, 0, 255, 10, 20, 30);
    flipped[1][1] = new RGBPixel(1, 1, 255, 20, 10, 30);

  }

  @Test
  public void testVertFlip() {
    Pixel[][] result = flip.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), flipped[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), flipped[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), flipped[i][j].getColorValue("b"));
      }
    }
  }
}
