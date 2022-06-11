package util;

import org.junit.Test;

import model.Pixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the Image Utils class.
 */
public class ImageUtilTest {

  @Test
  public void processPPM() {
    try {
      ImageUtil.processPPM("src\\bad\\file\\");
      fail();
    } catch (IllegalStateException ie) {
      assertEquals("File src\\bad\\file\\ not found!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Not the right exception thrown");
      fail();
    }

    try {
      ImageUtil.processPPM("res/sunset-noP3.ppm");
      fail();
    } catch (IllegalStateException ie) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              ie.getMessage());
    } catch (Exception e) {
      System.out.println("Not the right exception thrown");
      fail();
    }

  }

  @Test
  public void succesfulPPMRead() {
    Pixel[][] reading = ImageUtil.processPPM("res/test.ppm");
    Pixel[][] expected = new Pixel[2][2];
    expected[0][0] = new RGBPixel(0, 0, 255, 1, 2, 3);
    expected[0][1] = new RGBPixel(0, 1, 255, 4, 5, 6);
    expected[1][0] = new RGBPixel(1, 0, 255, 7, 8, 9);
    expected[1][1] = new RGBPixel(1, 1, 255, 10, 11, 12);
    for (int i = 0; i < reading.length; i++) {
      for (int j = 0; j < reading[0].length; j++) {
        assertEquals(reading[i][j].getColorValue("r"), expected[i][j].getColorValue("r"));
        assertEquals(reading[i][j].getColorValue("g"), expected[i][j].getColorValue("g"));
        assertEquals(reading[i][j].getColorValue("b"), expected[i][j].getColorValue("b"));
      }
    }
  }

}