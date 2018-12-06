package tradesimulator.model.operation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
  void buy(String stockSymbol, double volume, LocalDate date, String portfolioName)
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

  /**
   * investFixedAmount() method invests the fixed amount into the same portfolio name.
   *
   * @param date          the invest date
   * @param money         the money
   * @param portfolioName the portfolio name
   * @throws IllegalArgumentException if the date is wrong
   */
  void investFixedAmount(LocalDate date, double money, String portfolioName)
          throws IllegalArgumentException;

  /**
   * investSpecifiedAmount() method invests the specified amount into the same portfolio name.
   *
   * @param date          the invest date
   * @param money         the money
   * @param ratioMap      the ratio map
   * @param portfolioName the portfolio name
   * @throws IllegalArgumentException if the date is wrong
   */
  void investSpecifiedAmount(LocalDate date, double money,
                             Map<String, Double> ratioMap, String portfolioName)
          throws IllegalArgumentException;

  /**
   * createSpecifiedPortfolio() method creates portfolio with specified stocks.
   *
   * @param symbols       the symbol list
   * @param portfolioName the portfolio name
   * @throws IllegalArgumentException if the name is wrong
   */
  void createSpecifiedPortfolio(List<String> symbols, String portfolioName)
          throws IllegalArgumentException;

  /**
   * investPeriodically() method makes the investment periodically.
   *
   * @param portfolioName the portfolio name
   * @param money         the amount
   * @param ratioMap      the ratio map
   * @param beginDate     the begin date
   * @param endDate       the end date
   * @param interval      the interval
   * @throws IllegalArgumentException if the argument is wrong
   */
  void investPeriodically(String portfolioName, double money,
                          Map<String, Double> ratioMap, LocalDate beginDate,
                          LocalDate endDate, int interval)
          throws IllegalArgumentException;

  /**
   * setCommissionFee() sets the commission fee.
   *
   * @param commissionFee the commission fee double
   * @throws IllegalArgumentException if the argument is wrong
   */
  void setCommissionFee(double commissionFee) throws IllegalArgumentException;

  /**
   * Save a portfolio to a csv file.
   *
   * @param portfolioName the name of the portfolio to be saved.
   * @throws IOException throw exception.
   */
  void savePortfolio(String portfolioName) throws IOException;


  /**
   * Retrieve a portfolio to a new portfolio and add it to the portfolioMap.
   *
   * @param portFileName the name of the portfolio.
   * @param newName      the new portfolio name.
   */
  void retrievePortfolio(String portFileName, String newName) throws IOException;


  /**
   * Save a strategy to a new csv file.
   *
   * @param strategyName the name of the portfolio.
   * @param money        the money.
   * @param ratioMap     the map of the investment.
   * @param beginDate    the begin date.
   * @param endDate      the end date.
   * @param interval     the day interval.
   */
  void saveStrategy(String strategyName, double money,
                    Map<String, Double> ratioMap, LocalDate beginDate,
                    LocalDate endDate, int interval) throws IOException;

  /**
   * retrieve a strategy to a portfolio.
   *
   * @param strategyName  the name of the strategy saved in file.
   * @param portfolioName the name of the portfolio.
   */
  void retrieveStrategyToPort(String strategyName, String portfolioName) throws IOException;

  /**
   * get all saved portfolios.
   *
   * @return a string that lists all the portfolio saved.
   */
  String getAllSavedPortfolio();

  /**
   * get all saved strategy.
   *
   * @return a string that lists all the strategy saved.
   */
  String getAllSavedStrategy();


}
