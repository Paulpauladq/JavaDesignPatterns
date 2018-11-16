package tradesimulator.model.parser;

import java.time.LocalDate;

/**
 * The interface of the parser. The implementation gets stock price from API.
 */
public interface IParser {

  /**
   * Get the stock price from API.
   * @param stockSymbol the stock Symbol.
   * @param date the date to be used.
   * @return the price in a certain day.
   */
  double getPrice(String stockSymbol, LocalDate date);
}
