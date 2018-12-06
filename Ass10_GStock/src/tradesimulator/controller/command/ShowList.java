package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the ShowList command.
 */
public class ShowList implements TradeCommand<Stock> {

  /**
   * execute the m.
   *
   * @param m the simulator operation.
   * @return the String.
   */
  public String execute(SimulatorOperations<Stock> m) {
    return "All the Portfolios is as follows:\n" + m.getPortfolioList();
  }
}
