package controller;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.*;

import model.Blur;
import model.Brighten;
import model.Greyscale;
import model.HorizontalFlip;
import model.ImageProcessor;
import model.ImageProcessorModel;
import model.Load;
import model.Operations;
import model.Pixel;
import model.Save;
import model.Sepia;
import model.Sharpen;
import model.ValueComponent;
import model.VerticalFlip;
import util.Util;
import view.Histogram;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * This class is the controller implementation for the image processing program.
 * It will be able to read a PPM file and pass it to its given model and append the view as needed.
 * It will also handle commands to perform operations on the given image which can then be
 * saved as a new file.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {
  private ImageProcessorView view;
  private ImageProcessor model;
  private Readable readable;

  Map<String, ImageProcessor> imageModels = new HashMap<String, ImageProcessor>();
  // needed to store all the different models that have operations applied to them

  Map<String, Function<Scanner, Operations>> operations =
          new HashMap<String, Function<Scanner, Operations>>();

  /**
   * This constructor instantiates the controller implementation for this class and
   * sets the readable to a custom one given by the client.
   *
   * @param view     the view object that acts as the interface the client sees
   * @param model    the model object that handles the logic and operations that the client wishes
   *                 to execute
   * @param readable the custom location where the controller will read input from to load an image
   *                 as well as to listen and parse commands
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  public ImageProcessorControllerImpl(ImageProcessorView view, ImageProcessor model,
                                      Readable readable) throws IllegalArgumentException {
    this.view = Util.checkNullness(view);
    this.model = Util.checkNullness(model);
    this.readable = Util.checkNullness(readable);
  }

  /**
   * This constructor instantiates the controller implementation for this class and
   * sets the default read location as the console.
   *
   * @param view  the view object that acts as the interface the client sees
   * @param model the model object that handles the logic and operations that the client wishes
   *              to execute
   * @throws IllegalArgumentException if any of the arguments given are null
   */
  public ImageProcessorControllerImpl(ImageProcessorView view, ImageProcessor model)
          throws IllegalArgumentException {
    this.view = Util.checkNullness(view);
    this.model = Util.checkNullness(model);
    this.readable = new InputStreamReader(System.in);

  }

  /**
   * Asynchronous waiting for user commands.
   *
   * <p>
   * This design features a heavily decoupled model with
   * the model being "in-place" where it is never re-instantiated to represent a new image.
   * It eas chosen by drawing inspiration from the traditional notion of stability
   * which often is seen in sorting algorithms. We wanted to change the model itself as little
   * as possible due to the fact that we did not create a mutable and immutable version of the
   * model. This design for the controller also is heavily reliant on
   * correct sequence of inputs that roughly follow with Assignment4.
   * </p>
   * <p>
   * Additionally, a map is used to supplement the fact that our model stays the same and acts as a
   * housing for a revolving set of images. We have also made assumptions about cases of overriding
   * for each command and have made the model a "focus" object which will only change
   * when overridden or
   * </p>
   */
  @Override
  public void listen() {

    Scanner scan = new Scanner(readable);
    Pixel[][] newEntry;

    while (scan.hasNext()) {
      Operations operation;
      String in = scan.next();

      operations = new HashMap<String, Function<Scanner, Operations>>();
      String sourceName;
      String destName;
      try {
        sourceName = scan.next();
        destName = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException("Missing input values!");
      }

      boolean overrideSource;
      ImageProcessor saveModel;
      boolean changeSource = false;

      if (in.equals("save")) {
        overrideSource = this.checkSourceName(destName);
        saveModel = this.imageModels.get(destName);
      } else if (in.equals("load")) {
        changeSource = !this.imageModels.isEmpty() && destName.equals(this.model.getImageName());
        overrideSource = this.imageModels.isEmpty() && this.checkSourceName(destName);
        saveModel = null;
      } else {
        overrideSource = this.checkSourceName(sourceName) && sourceName.equals(destName);
        changeSource = !this.imageModels.isEmpty() && !sourceName.equals(this.model.getImageName());
        saveModel = null;
      }

      // change template model
      if (changeSource) {
        this.imageModels.put(this.model.getImageName(),
                new ImageProcessorModel(this.model.getImage(), this.model.getImageName()));
        this.model = imageModels.get(sourceName);
      }

      operations.put("load", s -> new Load(sourceName, destName, overrideSource));
      // sourceName has to be the path
      operations.put("save", s -> new Save(sourceName, saveModel, false));
      // sourceName has to be the path
      operations.put("vertical-flip", s -> new VerticalFlip(overrideSource));
      operations.put("horizontal-flip", s -> new HorizontalFlip(overrideSource));
      operations.put("red-component", s -> new ValueComponent("Red", overrideSource));
      operations.put("blue-component", s -> new ValueComponent("Blue", overrideSource));
      operations.put("green-component", s -> new ValueComponent("Green", overrideSource));
      operations.put("luma-component", s -> new ValueComponent("Luma", overrideSource));
      operations.put("value-component", s -> new ValueComponent("Value", overrideSource));
      operations.put("intensity-component", s -> new ValueComponent("Intensity",
              overrideSource));
      operations.put("brighten", s -> new Brighten(scan.nextInt(), overrideSource));
      operations.put("sepia", s -> new Sepia(overrideSource));
      operations.put("blur", s -> new Blur(overrideSource));
      operations.put("sharpen", s -> new Sharpen(overrideSource));
      operations.put("greyscale", s -> new Greyscale(overrideSource));
      Function<Scanner, Operations> cmd;
      try {
        cmd = operations.getOrDefault(in, null);
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException("operation not found");
      }

      if (cmd == null) {
        throw new IllegalArgumentException("error in parsing input");
      } else {
        operation = cmd.apply(scan);
        newEntry = operation.apply(this.model);
        ImageProcessor modelNew = new ImageProcessorModel(newEntry,destName);
        view = new ImageProcessorTextView(
                new ImageProcessorModel(newEntry, destName));
        view.renderImage();
      }

      // loading for the first time
      if (in.equals("load") && !this.imageModels.containsKey(destName)) {
        ImageProcessor firstLoad = new ImageProcessorModel(newEntry, destName);
        this.imageModels.put(destName, firstLoad);

      }

      if (!overrideSource) {
        ImageProcessor modelEntry = new ImageProcessorModel(newEntry, destName);
        this.imageModels.put(destName, modelEntry);
      }

    }
  }

  // throws an exception when the given source name is not contained in the "library" of
  // images stored as a map key in this controller while the "library" is not empty
  private boolean checkSourceName(String sourceName) throws IllegalArgumentException {
    if (this.imageModels.containsKey(sourceName) || this.imageModels.isEmpty()) {
      return true;
    } else {
      System.out.println(sourceName);
      throw new IllegalArgumentException("Please enter a valid source name");
    }
  }


}
