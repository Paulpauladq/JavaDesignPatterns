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

  /**
   * getDateFromMap() gets date from cache.
   * @param stockSymbol stock symbol
   * @param date the local date
   * @return the renewed local date
   */
  public LocalDate getDateFromMap(String stockSymbol, LocalDate date);

  /**
   * checkValidStockSymbol() checks valid stock symbol.
   * @param stockSymbol the stock symbol
   */
  void checkValidStockSymbol(String stockSymbol);

}
