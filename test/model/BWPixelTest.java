package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test class for the BWPixel object.
 */
public class BWPixelTest {
  BWPixel bW;
  BWPixel bW2;
  Pixel bWFail;

  @Before
  public void init() {
    bW = new BWPixel(0, 0, 1, 0.2);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetLuma() {
    bW.getLuma();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetValue() {
    bW.getValue();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetIntensity() {
    bW.getIntensity();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetColorValue() {
    bW.getColorValue("fake");
  }

  @Test
  public void testConstructor() {
    bW = new BWPixel(0, 0, 1, 0.2);
    bW2 = new BWPixel(0, 0, 10, 3);
    assertEquals(bW.getBWValue(), 0.2, 0.0001);
    assertEquals(bW2.getBWValue(), 3, 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFail() {
    bWFail = new BWPixel(0, 0, 2, 200);
  }

}