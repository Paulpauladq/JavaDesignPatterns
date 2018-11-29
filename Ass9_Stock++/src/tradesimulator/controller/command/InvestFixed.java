package tradesimulator.controller.command;

import java.time.LocalDate;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Invest fixed command class.
 */
public class InvestFixed implements TradeCommand<Stock> {
  LocalDate date;
  double money;
  String portfolioName;

  /**
   * Constructor of this command.
   *
   * @param date          the local date
   * @param money         the money amount
   * @param portfolioName the portfolio name
   */
  public InvestFixed(LocalDate date, double money, String portfolioName) {
    this.date = date;
    this.money = money;
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    m.investFixedAmount(this.date, this.money, this.portfolioName);
    return "Successfully invest $" + this.money + " on " + this.date.toString()
            + " into portfolio " + this.portfolioName + " by fixed ratios\n";
  }
}
