package view;

import org.junit.Before;
import org.junit.Test;

import model.ImageProcessor;
import model.ImageProcessorModel;
import model.Pixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents the tester class for the ImageProcessorTextView class.
 */
public class ImageProcessorTextViewTest {

  CorruptAppendable corrupt;
  ImageProcessorTextView error;

  Appendable appendable;
  ImageProcessorTextView validTest;

  ImageProcessor standardModel;
  ImageProcessor standardModel2;
  ImageProcessor standardModel3;

  @Before
  public void init() {
    standardModel = new ImageProcessorModel();
    standardModel2 = new ImageProcessorModel();

    Pixel[][] temp = {{new RGBPixel(0, 0, 255, 14, 9, 200),
            new RGBPixel(0, 1, 255, 34, 45, 90)},
                      {new RGBPixel(1, 0, 255, 34, 188,
                    180), new RGBPixel(1, 1, 255, 200,
                    12, 103)}};
    standardModel3 = new ImageProcessorModel(temp, "standardModel3", 255);

    corrupt = new CorruptAppendable();
    appendable = new StringBuilder();

    error = new ImageProcessorTextView(corrupt, standardModel);
    validTest = new ImageProcessorTextView(appendable, standardModel);
  }

  @Test
  public void testConstructor() {
    try {
      error = new ImageProcessorTextView(null, null);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorTextView(null, standardModel3);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorTextView(appendable, null);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorTextView(null);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      error = new ImageProcessorTextView(standardModel3);
    } catch (Exception e) {
      System.out.println("No exception should be thrown");
      fail();
    }

    try {
      error = new ImageProcessorTextView(appendable, standardModel3);
    } catch (Exception e) {
      System.out.println("No exception should be thrown");
      fail();
    }
  }

  @Test
  public void renderMessage() {
    try {
      error.renderMessage("Testing");
      fail();
    } catch (IllegalStateException ie) {
      assertEquals("Corrupt exception.", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      validTest.renderMessage("Testing");
      assertEquals("Testing", appendable.toString());
    } catch (IllegalStateException ie) {
      fail();
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }
  }

  @Test
  public void renderImage() {
    try {
      error = new ImageProcessorTextView(corrupt, standardModel3);
      error.renderImage();
      fail();
    } catch (IllegalStateException ie) {
      assertEquals("Corrupt exception.", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      Appendable appendable2 = new StringBuilder();
      validTest = new ImageProcessorTextView(appendable2, standardModel3);
      validTest.renderImage();
      String test = "P3\n2 2\n255\n14\n9\n200\n34\n45\n90\n34\n188\n180\n200\n12\n103\n";
      assertEquals(test, appendable2.toString());
    } catch (IllegalStateException ie) {
      fail();
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }
  }
}