package freecell.model;

/**
 * This interface is a builder to build a model. This interface is offered to
 * implement the builder module with which you can use the builder to call the
 * constructor of the builder.
 */
public interface FreecellOperationsBuilder {

  /**
   * This method is used to set the pile number for cascade pile. It takes a parameter
   * as the cascade number and return a builder object for cascading operation.
   *
   * @param c the pile number for cascade pile
   * @return the new FreecellOperationsBuilder object
   */
  FreecellOperationsBuilder cascades(int c);

  /**
   * This method is used to set the pile number for open pile. It takes a parameter
   * as the open pile size and return a builder object for cascading operation.
   *
   * @param o the pile number for open pile
   * @return the new FreecellOperationsBuilder object
   */
  FreecellOperationsBuilder opens(int o);

  /**
   * This method is used to call the constructor of the Freecell model. In this way, one can
   * use this method to build a new model.
   *
   * @param <K> Card object
   * @return a Card object
   */
  <K> FreecellOperations<K> build();

}
