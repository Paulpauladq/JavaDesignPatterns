package tradesimulator.model.operation;

/**
 * The interface of the builder. The implementation builds the model.
 */
public interface SimulatorOperationsBuilder {

  /**
   * Build the model.
   *
   * @param p the maximum number of the portfolio in the model.
   * @return a new SimulatorOperationsBuilder.
   */
  SimulatorOperationsBuilder portfolios(int p) throws IllegalArgumentException;

  /**
   * Build the model.
   *
   * @param <K> the object type.
   * @return a new interface of a model.
   */
  <K> SimulatorOperations<K> build();
}
