package tradesimulator.model.tradingitem;

import java.time.LocalDate;

/**
 * The interface of the Stock. The implementation records the record of the same stock.
 */
public interface TradingItem {

  /**
   * get the stockSymbol.
   *
   * @return String of the stockSymbol.
   */
  String getSymbol();

  /**
   * get the total cost of the stock.
   *
   * @return the total cost of the stock.
   */
  double getCost();

  /**
   * get the total cost of the stock by a certain date.
   */
  double getCostByDate(LocalDate date, double commissionFee);

  /**
   * Get the stock number of the stock.
   *
   * @return stock number of the stock.
   */
  double getVolume();

  /**
   * add a new transaction record of the stock.
   *  @param date   the date that the transaction occurs.
   * @param volume the trading amount of the stock.
   * @param price  the unit price of the stock.
   */
  void addRecord(LocalDate date, double volume, double price);

  /**
   * get the recordLog.
   *
   * @return the transaction record of the stock.
   */
  String recordLog();
}
