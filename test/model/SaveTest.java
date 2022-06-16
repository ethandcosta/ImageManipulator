package model;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import util.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the Save function.
 */
public class SaveTest {

  final int saveApplyCounter = 11;
  Save test;
  ImageProcessor model;
  ImageProcessor smallModel;
  ImageProcessor jpgModel;
  ImageProcessor dragonModel;

  @Before
  public void init() {
    model = new ImageProcessorModel();
    test = new Save("res\\testSave.ppm", model, false);
    Pixel[][] image = {{new RGBPixel(0, 0, 255, 1, 2, 3),
            new RGBPixel(0, 1, 255, 4, 5, 6)}, {
            new RGBPixel(1, 0, 255, 7, 8, 9),
            new RGBPixel(1, 1, 255, 10, 11, 12)}};
    smallModel = new ImageProcessorModel(image, "small-model");
    jpgModel = new ImageProcessorModel(image, "jpgModel");
    Pixel[][] dragon = ImageUtil.processBufferedImage("res\\dragon-copy.jpg");
    dragonModel = new ImageProcessorModel(dragon, "dragon");
  }

  @Test
  public void testConstructor() {
    try {
      test = new Save(null, null, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown!");
      fail();
    }

    try {
      test = new Save(null, model, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown!");
      fail();
    }

    try {
      test = new Save("res/sunset.ppm", null, false);
      fail();
    } catch (IllegalArgumentException ie) {
      assertEquals("One or more argument(s) are null!", ie.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception thrown!");
      fail();
    }

    try {
      test = new Save("res\\testingSave.ppm", model, false);
    } catch (Exception e) {
      System.out.println("No exception should be thrown");
      fail();
    }
  }

  @Test
  public void apply() {
    // can only be tested once and then change the saveApplyCounter to a different number to
    // save to a new file with no errors
    Path p = Paths.get("res\\testing-save" + saveApplyCounter + ".ppm");
    assertFalse(Files.exists(p));
    test = new Save("res\\testing-save" + saveApplyCounter + ".ppm", smallModel, false);
    test.apply(model);
    assertTrue(Files.exists(p));

    try {
      String actual = Files.readString(p);
      assertEquals("P3\n2 2\n255\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n", actual);
    } catch (Exception e) {
      System.out.println("Exception should not be thrown");
      fail();
    }

    Path pDragon = Paths.get("res\\dragon-copy.ppm");
    assertFalse(Files.exists(pDragon));
    test = new Save("res\\dragon-copy.ppm", smallModel, false);
    test.apply(model);
    assertTrue(Files.exists(pDragon));
  }

  @Test
  public void applyJPG() {
    // can only be tested once and then change the saveApplyCounter to a different number to
    // save to a new file with no errors
    Path p = Paths.get("res\\testing-saveJPG" + saveApplyCounter + ".jpg");
    assertFalse(Files.exists(p));
    test = new Save("res\\testing-saveJPG" + saveApplyCounter + ".jpg", jpgModel,
            false);
    test.apply(model);
    assertTrue(Files.exists(p));

    Path pDragon = Paths.get("res\\dragon-copy" + saveApplyCounter + ".jpg");
    assertFalse(Files.exists(pDragon));
    test = new Save("res\\dragon-copy" + saveApplyCounter + ".jpg", dragonModel,
            false);
    test.apply(dragonModel);
    assertTrue(Files.exists(pDragon));
  }
}