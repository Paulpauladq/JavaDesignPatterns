package tradesimulator.controller.command;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the Create command.
 */
public class Create implements TradeCommand<Stock> {

  String portfolioName;

  /**
   * the constructor.
   *
   * @param portfolioName the name of the portfolio.
   */
  public Create(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    m.createPortfolio(this.portfolioName);
    return "Successfully created " + this.portfolioName + "\n";
  }
}
