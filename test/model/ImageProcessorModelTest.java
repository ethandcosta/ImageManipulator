package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents a tester class for the ImageProcessorModel class.
 */
public class ImageProcessorModelTest {

  ImageProcessor modelOne;
  ImageProcessor modelThreeArgs;
  ImageProcessor error;
  ImageProcessor modelTwo;
  ImageProcessor modelThree;

  Pixel[][] modelOneImage = {{new RGBPixel(0, 0,
          255, 7, 8, 9)}};
  Pixel[][] modelTwoImage = {{new RGBPixel(0, 0, 255,
          1, 2, 3)}};


  Pixel[][] modelThreeImage = {{new RGBPixel(0, 0, 20,
          1, 2, 3)}};

  @Before
  public void init() {
    modelOne = new ImageProcessorModel();
    error = new ImageProcessorModel();
    modelThreeArgs = new ImageProcessorModel();
    modelTwo = new ImageProcessorModel(modelTwoImage, "modelTwoName");
    modelThree = new ImageProcessorModel(modelThreeImage, "modelThreeName", 20);
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

  @Test
  public void getMaxValue() {
    assertEquals(255, this.modelTwo.getMaxValue());
    assertEquals(20, this.modelThree.getMaxValue());
  }

  @Test
  public void getImageName() {
    assertEquals("modelTwoName", this.modelTwo.getImageName());
    assertEquals("", this.modelOne.getImageName());
    assertEquals("modelThreeName", this.modelThree.getImageName());
  }

  @Test
  public void setImage() {
    AbstractImageProcessorModel emptyModel = new ImageProcessorModel();
    assertArrayEquals(new Pixel[0][0], emptyModel.getImage());
    Pixel[][] sameMemoryLocation = emptyModel.getImage();
    assertTrue(sameMemoryLocation == emptyModel.getImage());
    emptyModel.setImage(modelOneImage);
    assertFalse(sameMemoryLocation == emptyModel.getImage());
    Pixel[][] modelOneImageCopy = {{new RGBPixel(0, 0,
            255, 7, 8, 9)}};
    assertEquals(modelOneImageCopy[0][0].x, emptyModel.getImage()[0][0].x);
    assertEquals(modelOneImageCopy[0][0].y, emptyModel.getImage()[0][0].y);
    assertEquals(modelOneImageCopy[0][0].getColorValue("r"),
            emptyModel.getImage()[0][0].getColorValue("r"));
    assertEquals(modelOneImageCopy[0][0].getColorValue("g"),
            emptyModel.getImage()[0][0].getColorValue("g"));
    assertEquals(modelOneImageCopy[0][0].getColorValue("b"),
            emptyModel.getImage()[0][0].getColorValue("b"));
    assertEquals(modelOneImageCopy[0][0].maxValue, emptyModel.getImage()[0][0].maxValue);
  }

}