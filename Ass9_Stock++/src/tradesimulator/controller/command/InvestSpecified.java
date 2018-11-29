package tradesimulator.controller.command;

import java.time.LocalDate;
import java.util.Map;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Invest specified command class.
 */
public class InvestSpecified implements TradeCommand<Stock> {

  LocalDate date;
  double money;
  Map<String, Double> ratioMap;
  String portfolioName;

  /**
   * Constructor of the invest specified command.
   *
   * @param date          the date
   * @param money         the money
   * @param ratioMap      the ratio map
   * @param portfolioName the portfolio name
   */
  public InvestSpecified(LocalDate date, double money,
                         Map<String, Double> ratioMap, String portfolioName) {
    this.date = date;
    this.money = money;
    this.ratioMap = ratioMap;
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    m.investSpecifiedAmount(this.date, this.money, this.ratioMap, this.portfolioName);
    return "Successfully invest $" + this.money + " on " + this.date.toString()
            + " into portfolio " + this.portfolioName + " by specified ratios\n";
  }
}
