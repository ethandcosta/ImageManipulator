package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessor;
import model.ImageProcessorModel;
import model.MockModel;
import view.ImageProcessorView;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test class for the ImageProcessorControllerImpl class.
 */
public class ImageProcessorControllerImplTest {
  ImageProcessorView view;
  ImageProcessorModel model;
  ImageProcessor mockModel;
  ImageProcessorControllerImpl controller;
  Readable read;
  StringBuilder log;
  StringBuilder logModel;
  StringBuilder logView;

  @Before
  public void init() {
    model = new ImageProcessorModel();
    log = new StringBuilder();
    logModel = new StringBuilder();
    logView = new StringBuilder();
    mockModel = new MockModel(logModel);
    view = new MockView(logView);
  }

  @Test
  public void testConstructor() {
    read = new StringReader("load res/sunset.ppm sunset \n red-component sunset sunset-horizontal");
    mockModel = new MockModel(logModel);
    view = new MockView(logView);
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\npassed to Red\n", logModel.toString());
  }

  @Test
  public void testBrighten() {
    read = new StringReader("load res/sunset.ppm sunset \n brighten sunset sunset-horizontal 10");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\nbrightened by 10\n", logModel.toString());
  }

  @Test
  public void testHorizontalFlip() {
    read = new StringReader("load res/sunset.ppm sunset \n "
            + "horizontal-flip sunset sunset-horizontal");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\ndid horizontal flip\n", logModel.toString());
  }

  @Test
  public void testVerticalFlip() {
    read = new StringReader("load res/sunset.ppm sunset \n vertical-flip sunset sunset-vertical");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\ndid vertical flip\n", logModel.toString());
  }

  @Test
  public void testValueComponent() {
    read = new StringReader("load res/sunset.ppm sunset "
            + "\n blue-component sunset sunset-horizontal");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\npassed to Blue\n", logModel.toString());
  }

  @Test
  public void testSave() {
    read = new StringReader("load res/sunset.ppm sunset \n save res/sunset.ppm sunset");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\nsaved res/sunset.ppm\n", logModel.toString());
  }

  @Test
  public void testLoad() {
    read = new StringReader("load res/sunset.ppm sunset");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
    assertEquals("loaded sunset\n", logModel.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    controller = new ImageProcessorControllerImpl(view, null, read);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    controller = new ImageProcessorControllerImpl(null, model, read);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRead() {
    controller = new ImageProcessorControllerImpl(view, model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringInput() {
    read = new StringReader("garbage");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondaryInput() {
    read = new StringReader("garbage fake fake");
    controller = new ImageProcessorControllerImpl(view, mockModel, read);
    controller.listen();
  }
}