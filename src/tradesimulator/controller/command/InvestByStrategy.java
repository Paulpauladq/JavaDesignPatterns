package tradesimulator.controller.command;

import java.time.LocalDate;
import java.util.Map;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Invest by strategy command.
 */
public class InvestByStrategy implements TradeCommand<Stock> {
  String portfolioName;
  double money;
  Map<String, Double> ratioMap;
  LocalDate beginDate;
  LocalDate endDate;
  int interval;

  /**
   * Constructor of this invest by strategy command.
   *
   * @param portfolioName portfolio name
   * @param money         money amount
   * @param ratioMap      ratio map
   * @param beginDate     begin date
   * @param endDate       end date
   * @param interval      interval
   */
  public InvestByStrategy(String portfolioName, double money,
                          Map<String, Double> ratioMap, LocalDate beginDate,
                          LocalDate endDate, int interval) {
    this.portfolioName = portfolioName;
    this.money = money;
    this.ratioMap = ratioMap;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.interval = interval;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    m.investPeriodically(this.portfolioName, this.money, this.ratioMap, this.beginDate,
            this.endDate, this.interval);
    return "Successfully invest $" + this.money + " from " + this.beginDate.toString()
            + " to " + this.endDate.toString() + " periodically into portfolio "
            + this.portfolioName + " by specified ratios\n";
  }
}
