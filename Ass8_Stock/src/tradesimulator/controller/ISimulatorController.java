package tradesimulator.controller;

import tradesimulator.model.operation.SimulatorOperations;

public interface ISimulatorController<K> {

  public void trade(SimulatorOperations<K> model);

}
