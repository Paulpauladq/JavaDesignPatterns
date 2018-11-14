package tradesimulator.model.operation;

public interface SimulatorOperationsBuilder {

  SimulatorOperationsBuilder portfolios(int p) throws IllegalArgumentException;

  <K> SimulatorOperations<K> build();

}
