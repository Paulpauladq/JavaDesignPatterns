package tradesimulator.controller.controller;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.view.simpleview.ISimulatorView;

/**
 * the IController represents a controller. The immplementation runs the go method.
 */
public interface IController<K> {

  SimulatorOperations<K> getModel();

  void setView(ISimulatorView view);

  /**
   * the go method runs the model.
   */
  void execute() throws IllegalArgumentException, IllegalStateException;
}
