package model;

/**
 * This class represents the abstraction of all the operation implementations.
 */
public abstract class AbstractOperations implements Operations {
  protected boolean override;

  protected AbstractOperations(boolean override) {
    this.override = override;
  }

  @Override
  public abstract Pixel[][] apply(ImageProcessor image) throws IllegalStateException;

}
