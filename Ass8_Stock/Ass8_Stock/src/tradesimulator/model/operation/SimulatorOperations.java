package tradesimulator.model.operation;

import java.time.LocalDate;

/**
 * The interface represents the model of the game.
 *
 * @param <K> the object type.
 */
public interface SimulatorOperations<K> {

  /**
   * Add a buying record to a method.
   *
   * @param stockSymbol   the stock symbol.
   * @param volume        the stock number.
   * @param date          the purchase date.
   * @param portfolioName the name of the portfolio.
   */
  void buy(String stockSymbol, int volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException;

  /**
   * Create a new portfolio.
   *
   * @param portfolioName the name of the new portfolio.
   */
  void createPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Get the total cost basis of a portfolio before a certain date.
   *
   * @param date          the date to be used.
   * @param portfolioName the name of the portfolio.
   * @return the total cost basis of a portfolio.
   */
  double getTotalCostBasis(LocalDate date, String portfolioName);

  /**
   * Get the total value at a certain date.
   *
   * @param date          the date to be used.
   * @param portfolioName the name of the portfolio.
   * @return the total market value of a portfolio at a certain date.
   */
  double getTotalValue(LocalDate date, String portfolioName);

  /**
   * Get the state of a portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @return the state of a portfolio.
   */
  String getPortfolioState(String portfolioName) throws IllegalArgumentException;

  /**
   * Get the record of the portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @return the record of the portfolio.
   */
  String getPortfolioLog(String portfolioName) throws IllegalArgumentException;

  /**
   * Get the record summary of the portfolio.
   *
   * @return the record summary of the portfolio.
   */
  String getPortfolioList();

}
