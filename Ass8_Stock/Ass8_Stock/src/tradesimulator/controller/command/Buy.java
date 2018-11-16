package tradesimulator.controller.command;

import java.time.LocalDate;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * the Buy command.
 */
public class Buy implements TradeCommand<Stock> {

  String stockSymbol;
  int volume;
  LocalDate date;
  String portfolioName;

  /**
   * The constructor.
   *
   * @param stockSymbol   stock symbol.
   * @param volume        the sotck number.
   * @param date          the date of the purchase.
   * @param portfolioName the name of the portfolio.
   */
  public Buy(String stockSymbol, int volume, LocalDate date, String portfolioName) {
    this.stockSymbol = stockSymbol;
    this.volume = volume;
    this.date = date;
    this.portfolioName = portfolioName;
  }

  @Override
  public String execute(SimulatorOperations<Stock> m) {
    m.buy(this.stockSymbol, this.volume, this.date, this.portfolioName);
    return "Successfully bought stock " + this.stockSymbol + " "
            + this.volume + " shares on " + this.date.toString() + " into portfolio "
            + this.portfolioName + "\n";
  }
}
