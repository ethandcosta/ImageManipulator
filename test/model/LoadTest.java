package model;

import org.junit.Before;
import org.junit.Test;

import util.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the load function.
 */
public class LoadTest {
  Load test;
  ImageProcessor model;

  @Before
  public void init() {
    test = new Load("res/sunset.ppm", "Valid", true);
    model = new ImageProcessorModel();
  }

  @Test
  public void testConstructor() {
    try {
      test = new Load(null, null, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      test = new Load("Valid", null, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      test = new Load("res/sunset.ppm", null, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }

    try {
      test = new Load("jank\\file\\path", "Valid", false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("Please enter a valid path for your image", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown");
      fail();
    }
  }

  @Test
  public void apply() {
    try {
      test = new Load("res/test2.ppm", "valid", true);
      Pixel[][] expected = {{new RGBPixel(0, 0, 255, 1, 2,
              3), new RGBPixel(0, 1, 255, 4, 5,
              6)}, {new RGBPixel(1, 0, 255, 7, 8,
              9), new RGBPixel(1, 1, 255, 10, 11,
              12)}};
      Pixel[][] result = test.apply(model);
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[0].length; j++) {
          assertEquals(expected[i][j].getColorValue("r"), result[i][j].getColorValue("r"));
          assertEquals(expected[i][j].getColorValue("g"), result[i][j].getColorValue("g"));
          assertEquals(expected[i][j].getColorValue("b"), result[i][j].getColorValue("b"));
        }
      }
    } catch (Exception e) {
      System.out.println("No error should be thrown");
      fail();
    }

    try {
      test = new Load("res\\dragon-copy.jpg", "Dragon", true);
      test.apply(model);
      Brighten brighten = new Brighten(255, false);
      Pixel[][] result = brighten.apply(model);
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[0].length; j++) {
          assertEquals(255, result[i][j].getColorValue("r"));
          assertEquals(255, result[i][j].getColorValue("g"));
          assertEquals(255, result[i][j].getColorValue("b"));
        }
      }
    } catch (Exception e) {
      System.out.println("No error should be thrown");
      fail();
    }
  }

  @Test
  public void testJPG() {
    try {
      Pixel[][] ppmVersion = ImageUtil.processPPM("res/sunset.ppm");
      test = new Load("res\\sunset.jpg", "sunset", true);
      Pixel[][] result = test.apply(model);

      // test for compression issue with sunset.jpg to sunset.ppm
      Operations testSave = new Save("res\\sunset-copy.ppm", model, false);
      testSave.apply(model);
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[0].length; j++) {
          assertEquals(ppmVersion[i][j].getColorValue("r"),
                  result[i][j].getColorValue("r"), 5);
          assertEquals(ppmVersion[i][j].getColorValue("g"),
                  result[i][j].getColorValue("g"), 5);
          assertEquals(ppmVersion[i][j].getColorValue("b"),
                  result[i][j].getColorValue("b"), 5);
        }
      }
    } catch (Exception e) {
      System.out.println("No error should be thrown");
      fail();
    }
  }

}
