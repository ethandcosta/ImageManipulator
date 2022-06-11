package util;

import org.junit.Test;

import model.ImageProcessor;
import model.ImageProcessorModel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents the test class for the Util class.
 */
public class UtilTest {

  Util testUtil = new Util();

  @Test
  public void checkNullness() {

    // test cases - not null
    try {
      String a = "test'";
      assertEquals(a, Util.checkNullness(a));
    } catch (IllegalArgumentException ie) {
      fail();
    } catch (Exception e) {
      System.out.print("Note the right error was thrown!");
      fail();
    }

    try {
      Integer a = 40;
      assertEquals(a, Util.checkNullness(a));
    } catch (IllegalArgumentException ie) {
      fail();
    } catch (Exception e) {
      System.out.print("Note the right error was thrown!");
      fail();
    }

    try {
      ImageProcessorView viewTemp = new ImageProcessorTextView(
              new ImageProcessorModel());
      assertEquals(viewTemp, Util.checkNullness(viewTemp));
    } catch (IllegalArgumentException ie) {
      fail();
    } catch (Exception e) {
      System.out.print("Note the right error was thrown!");
      fail();
    }

    try {
      ImageProcessor modelTemp = new ImageProcessorModel();
      assertEquals(modelTemp, Util.checkNullness(modelTemp));
    } catch (IllegalArgumentException ie) {
      fail();
    } catch (Exception e) {
      System.out.print("Note the right error was thrown!");
      fail();
    }

    // test case - null
    try {
      Util.checkNullness(null);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.print("Note the right error was thrown!");
      fail();
    }

  }
}