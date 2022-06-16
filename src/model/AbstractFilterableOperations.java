package model;

/**
 * This class represents an abstraction of all filterable operations implementations.
 */
public abstract class AbstractFilterableOperations implements FilterableOperations {

  boolean override;

  protected AbstractFilterableOperations(boolean override) {
    this.override = override;
  }

}
