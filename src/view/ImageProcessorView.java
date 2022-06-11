package view;

/**
 * This interface represents all the actions that can be undertaken by all the different types of
 * views for this image processor program.
 */
public interface ImageProcessorView {

  /**
   * This method takes in a given message to be appended to the Appendable object in the view to
   * be displayed.
   *
   * @param message the message that is going to be appended
   * @throws IllegalStateException if a transmission error occurs when sending the message
   */
  public void renderMessage(String message) throws IllegalStateException;

  /**
   * This method displays the current image state based on the implementation of the image processor
   * view.
   *
   * @throws IllegalStateException if a transmission error occurs when displaying the image state
   */
  public void renderImage() throws IllegalStateException;
}
