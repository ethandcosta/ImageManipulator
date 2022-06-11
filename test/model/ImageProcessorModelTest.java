package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents a tester class for the ImageProcessorModel class.
 */
public class ImageProcessorModelTest {

  ImageProcessor modelOne;
  ImageProcessor modelThreeArgs;
  ImageProcessor error;
  Pixel[][] modelOneImage;

  @Before
  public void init() {
    modelOne = new ImageProcessorModel();
    error = new ImageProcessorModel();
    modelThreeArgs = new ImageProcessorModel();

  }

  @Test
  public void testConstructor() {
    try {
      Pixel[][] modelOneImage = {{new RGBPixel(0, 0, 255,
              6, 7, 8)}};
      modelOne = new ImageProcessorModel(modelOneImage, "One-Pixel");
    } catch (Exception ie) {
      System.out.println("No error should be thrown");
      fail();
    }

    try {
      Pixel[][] modelThreeArgsImage = {{new RGBPixel(0, 0, 255, 6, 7,
                              8), new RGBPixel(0, 1, 255, 0,
              0, 0), new RGBPixel(0, 2, 255, 1, 1,
              1)}, {new RGBPixel(1, 0, 255, 3, 3,
              3), new RGBPixel(1, 1, 255, 4, 4, 4),
              new RGBPixel(1, 2, 255, 5, 5, 5)},
                                       {new RGBPixel(2, 0, 255, 6, 6,
                                               6), new RGBPixel(2, 1, 255,
                                               7, 7, 7), new RGBPixel(2,
                                               2, 255, 8, 8,
                                               8)}};
      modelThreeArgs = new ImageProcessorModel(modelThreeArgsImage,
              "ThreeByThree-Pixel", 255);
    } catch (Exception ie) {
      System.out.println("No error should be thrown");
      fail();
    }

    try {
      error = new ImageProcessorModel(null, null, 255);
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorModel(new Pixel[2][2], null, 255);
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorModel(null, "Error", 255);
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorModel(null, "Error");
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorModel(new Pixel[500][500], null);
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }
  }
}