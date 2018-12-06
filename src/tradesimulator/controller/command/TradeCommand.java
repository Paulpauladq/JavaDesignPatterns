package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;

/**
 * A Trade command interface which allows implementation of the several commands.
 */
public interface TradeCommand<K> {

  /**
   * Go method.
   *
   * @param m the simulator operation.
   */
  String execute(SimulatorOperations<K> m);

}
