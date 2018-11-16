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
  public double getTotalCost();

  /**
   * get total cost basis before a certain date.
   *
   * @param date the date to be used.
   * @return total cost basis before a certain date.
   */
  public double getTotalCostByDate(LocalDate date);

  /**
   * Get the map that records the stock purchase in the portfolio.
   *
   * @return the map of stock purchase.
   */
  public Map<String, Stock> getStockMap();

  /**
   * Add new record of stock purchase in the portfolio.
   *
   * @param stockSymbol the stock symbol of a stock.
   * @param date        the date of the stock purchase.
   * @param volume      the stock unit number in the purchase.
   * @param price       the unit price in the purchase.
   */
  public void addStockRecord(String stockSymbol, LocalDate date, int volume, double price);

  /**
   * Add new stock in the record.
   *
   * @param stock the stock to be added.
   */
  public void addStock(Stock stock);

  /**
   * Delete a stock of the portfolio.
   *
   * @param stockSymbol the stock symbol of a stock.
   * @param date        the date of the stock purchase.
   * @param volume      the stock unit number in the purchase.
   * @param price       the unit price in the purchase.
   */
  public void deleteStock(String stockSymbol, LocalDate date, int volume, double price);


  /**
   * Get the purchase history.
   *
   * @return the purchase record.
   */
  public String recordLog();
}
