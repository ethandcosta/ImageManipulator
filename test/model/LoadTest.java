package model;

import org.junit.Before;
import org.junit.Test;

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

    try {
      test = new Load("res/sunset.ppm", "Valid", true);
      // test.apply(model);
    } catch (Exception e) {
      System.out.println("No error should be thrown");
      fail();
    }
  }

}
