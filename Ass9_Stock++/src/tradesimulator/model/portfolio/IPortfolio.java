package tradesimulator.model.portfolio;

import java.time.LocalDate;
import java.util.Map;

/**
 * The interface of Portfolio. The implementation records the stock purchase in the same portfolio.
 *
 * @param <Stock> the purchased stock in the portfolio.
 */
public interface IPortfolio<Stock> {

  /**
   * Get the name of the Portfolio.
   *
   * @return the name of the Portfolio.
   */
  String getPortfolioName();

  /**
   * Get the total cost basis of the portfolio.
   *
   * @return total cost basis.
   */
  double getTotalCost();

  /**
   * get total cost basis before a certain date.
   *
   * @param date the date to be used.
   * @return total cost basis before a certain date.
   */
  double getTotalCostByDate(LocalDate date, double commissionFee);

  /**
   * Get the map that records the stock purchase in the portfolio.
   *
   * @return the map of stock purchase.
   */
  Map<String, Stock> getStockMap();

  /**
   * Add new record of stock purchase in the portfolio.
   *
   * @param stockSymbol the stock symbol of a stock.
   * @param date        the date of the stock purchase.
   * @param volume      the stock unit number in the purchase.
   * @param price       the unit price in the purchase.
   */
  void addStockRecord(String stockSymbol, LocalDate date, double volume, double price);

  /**
   * Add new stock in the record.
   *
   * @param stock the stock to be added.
   */
  void addStock(Stock stock);

  /**
   * Delete a stock of the portfolio.
   *
   * @param stockSymbol the stock symbol of a stock.
   * @param date        the date of the stock purchase.
   * @param volume      the stock unit number in the purchase.
   * @param price       the unit price in the purchase.
   */
  void deleteStock(String stockSymbol, LocalDate date, double volume, double price);

  /**
   * Get the purchase history.
   *
   * @return the purchase record.
   */
  String recordLog();
}
