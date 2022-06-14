package model;

public abstract class AbstractFilterableOperations implements FilterableOperations {

  boolean override;

  protected AbstractFilterableOperations(boolean override) {
    this.override = override;
  }

}
