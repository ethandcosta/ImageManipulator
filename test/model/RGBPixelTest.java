package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test class for the RGBPixel object.
 */
public class RGBPixelTest {
  RGBPixel red;
  RGBPixel blue;
  RGBPixel green;
  RGBPixel mix;
  RGBPixel black;
  RGBPixel white;

  @Before
  public void setup() {
    red = new RGBPixel(10, 10, 255, 255, 0, 0);
    blue = new RGBPixel(10, 10, 255, 0, 0, 255);
    green = new RGBPixel(10, 10, 255, 0, 255, 0);
    mix = new RGBPixel(10, 10, 255, 10, 45, 100);
    black = new RGBPixel(10, 10, 255, 0, 0, 0);
    white = new RGBPixel(10, 10, 255, 255, 255, 255);
  }

  @Test
  public void testGetLuma() {
    assertEquals(54.213, red.getLuma(), 0.001);
    assertEquals(18.411, blue.getLuma(), 0.001);
    assertEquals(182.3759, green.getLuma(), 0.001);
    assertEquals(0, black.getLuma(), 0.001);
    assertEquals(255, white.getLuma(), 0.001);
    assertEquals(41.53, mix.getLuma(), 0.001);
  }

  @Test
  public void testGetValue() {
    assertEquals(255, red.getValue());
    assertEquals(255, black.getValue());
  }

  @Test
  public void testGetIntensity() {
    assertEquals(85, red.getIntensity(), 0.001);
    assertEquals(85, blue.getIntensity(), 0.001);
    assertEquals(85, green.getIntensity(), 0.001);
    assertEquals(0, black.getIntensity(), 0.001);
    assertEquals(255, white.getIntensity(), 0.001);
    assertEquals(51.666, mix.getIntensity(), 0.001);
  }
}