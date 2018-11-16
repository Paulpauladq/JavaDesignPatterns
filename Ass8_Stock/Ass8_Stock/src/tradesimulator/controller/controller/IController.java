package tradesimulator.controller.controller;

import tradesimulator.model.operation.SimulatorOperations;

/**
 * the IController represents a controller. The immplementation runs the go method.
 */
public interface IController<K> {

  /**
   * the go method runs the model.
   *
   * @param model the model to be runned.
   */
  void execute(SimulatorOperations<K> model) throws IllegalArgumentException,
          IllegalStateException;
}
