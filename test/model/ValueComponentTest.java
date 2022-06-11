package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the ValueComponent class.
 */
public class ValueComponentTest {
  ValueComponent operationBlue;
  ValueComponent operationGreen;
  ValueComponent operationRed;
  ValueComponent operationLuma;
  ValueComponent operationValue;
  ValueComponent operationIntensity;
  ValueComponent operationUnsupported;
  Pixel[][] ogImage;
  Pixel[][] redImage;
  Pixel[][] blueImage;
  Pixel[][] greenImage;
  Pixel[][] valueImage;
  Pixel[][] intensityImage;
  Pixel[][] lumaImage;
  ImageProcessorModel model;

  @Before
  public void init() {
    operationBlue = new ValueComponent("Blue", false);
    operationRed = new ValueComponent("Red", false);
    operationGreen = new ValueComponent("Green", false);
    operationValue = new ValueComponent("Value", false);
    operationIntensity = new ValueComponent("Intensity", false);
    operationLuma = new ValueComponent("Luma", false);
    ogImage = new Pixel[2][2];
    ogImage[0][0] = new RGBPixel(0, 0, 255, 10, 20, 30);
    ogImage[0][1] = new RGBPixel(0, 0, 255, 20, 10, 30);
    ogImage[1][0] = new RGBPixel(0, 0, 255, 30, 20, 30);
    ogImage[1][1] = new RGBPixel(0, 0, 255, 45, 32, 12);
    model = new ImageProcessorModel(ogImage, "ogImage");
    redImage = new Pixel[2][2];
    redImage[0][0] = new RGBPixel(0, 0, 255, 10, 10, 10);
    redImage[0][1] = new RGBPixel(0, 0, 255, 20, 20, 20);
    redImage[1][0] = new RGBPixel(0, 0, 255, 30, 30, 30);
    redImage[1][1] = new RGBPixel(0, 0, 255, 45, 45, 45);
    blueImage = new Pixel[2][2];
    blueImage[0][0] = new RGBPixel(0, 0, 255, 30, 30, 30);
    blueImage[0][1] = new RGBPixel(0, 0, 255, 30, 30, 30);
    blueImage[1][0] = new RGBPixel(0, 0, 255, 30, 30, 30);
    blueImage[1][1] = new RGBPixel(0, 0, 255, 12, 12, 12);
    greenImage = new Pixel[2][2];
    greenImage[0][0] = new RGBPixel(0, 0, 255, 20, 20, 20);
    greenImage[0][1] = new RGBPixel(0, 0, 255, 10, 10, 10);
    greenImage[1][0] = new RGBPixel(0, 0, 255, 20, 20, 20);
    greenImage[1][1] = new RGBPixel(0, 0, 255, 32, 32, 32);
    valueImage = new Pixel[2][2];
    valueImage[0][0] = new RGBPixel(0, 0, 255, 30, 30, 30);
    valueImage[0][1] = new RGBPixel(0, 0, 255, 30, 30, 30);
    valueImage[1][0] = new RGBPixel(0, 0, 255, 30, 30, 30);
    valueImage[1][1] = new RGBPixel(0, 0, 255, 45, 45, 45);
    lumaImage = new Pixel[2][2];
    lumaImage[0][0] = new RGBPixel(0, 0, 255, 18, 18, 18);
    lumaImage[0][1] = new RGBPixel(0, 0, 255, 13, 13, 13);
    lumaImage[1][0] = new RGBPixel(0, 0, 255, 22, 22, 22);
    lumaImage[1][1] = new RGBPixel(0, 0, 255, 33, 33, 33);
    intensityImage = new Pixel[2][2];
    intensityImage[0][0] = new RGBPixel(0, 0, 255, 20, 20, 20);
    intensityImage[0][1] = new RGBPixel(0, 0, 255, 20, 20, 20);
    intensityImage[1][0] = new RGBPixel(0, 0, 255, 26, 26, 26);
    intensityImage[1][1] = new RGBPixel(0, 0, 255, 29, 29, 29);
  }

  @Test
  public void testRedGrayScale() {
    Pixel[][] result = operationRed.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), redImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), redImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), redImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testBlueGrayScale() {
    Pixel[][] result = operationBlue.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), blueImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), blueImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), blueImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testGreenGrayScale() {
    Pixel[][] result = operationGreen.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), greenImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), greenImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), greenImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testValueGrayScale() {
    Pixel[][] result = operationValue.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), valueImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), valueImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), valueImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testIntensityGrayScale() {
    Pixel[][] result = operationIntensity.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), intensityImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), intensityImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), intensityImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testLumaGrayScale() {
    Pixel[][] result = operationLuma.apply(model);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        assertEquals(result[i][j].getColorValue("r"), lumaImage[i][j].getColorValue("r"));
        assertEquals(result[i][j].getColorValue("g"), lumaImage[i][j].getColorValue("g"));
        assertEquals(result[i][j].getColorValue("b"), lumaImage[i][j].getColorValue("b"));
      }
    }
  }

  @Test
  public void testUnsupportedGrayScale() {
    try {
      operationUnsupported = new ValueComponent("fake", false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }
  }

}
