package tradesimulator.controller.command;

import java.time.LocalDate;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the DetermineCost command.
 */
public class DetermineCost implements TradeCommand<Stock> {

  LocalDate date;
  String portfolioName;

  /**
   * the constructor.
   *
   * @param date          the date of the purchase.
   * @param portfolioName the name of the portfolio.
   */
  public DetermineCost(LocalDate date, String portfolioName) {
    this.date = date;
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    double cost = m.getTotalCostBasis(this.date, this.portfolioName);
    return "Total cost of " + this.portfolioName + " by " + this.date.toString()
            + " is " + cost + "\n";
  }
}
