package tradesimulator.controller.command;

import java.time.LocalDate;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the DetermineValue command.
 */
public class DetermineValue implements TradeCommand<Stock> {

  LocalDate date;
  String portfolioName;

  /**
   * The constructor.
   *
   * @param date the date of the trade.
   */
  public DetermineValue(LocalDate date, String portfolioName) {
    this.date = date;
    this.portfolioName = portfolioName;
  }

  /**
   * execute the model.
   *
   * @param m the simulator operation
   */
  @Override
  public String execute(SimulatorOperations<Stock> m) {
    double value = m.getTotalValue(this.date, this.portfolioName);
    return "Total value of "
            + this.portfolioName + " by " + this.date.toString() + " is "
            + value + "\n";
  }
}
