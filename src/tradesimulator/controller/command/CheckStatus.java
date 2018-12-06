package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Check status command for portfolioName.
 */
public class CheckStatus implements TradeCommand<Stock> {

  String portfolioName;

  /**
   * Constructor of the command.
   *
   * @param portfolioName the portfolio name
   */
  public CheckStatus(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    return m.getPortfolioState(this.portfolioName);
  }
}
