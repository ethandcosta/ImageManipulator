package controller;

/**
 * This interface represents the image processing program's controller which will handle inputs
 * and relay changes in state of te image from the model to the view.
 */
public interface ImageProcessorController {

  /**
   * This method starts the listeners for the controller and will handle the inputs made by the
   * client.
   */
  public void listen();
}
