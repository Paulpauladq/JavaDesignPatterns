package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Check log command.
 */
public class CheckLog implements TradeCommand<Stock> {
  String portfolioName;

  /**
   * Constructor of the check log command.
   *
   * @param portfolioName portfolio name
   */
  public CheckLog(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    return m.getPortfolioLog(this.portfolioName);
  }
}
