package tradesimulator.model.transaction;

import java.time.LocalDate;

/**
 * the interface of ITransaction. An implementation will record details in a stock transaction.
 */
public interface ITransaction {

  /**
   * get the date of the transaction.
   *
   * @return the date which the transaction occurs.
   */
  LocalDate getDate();

  /**
   * get the unit price of the stock in the transaction.
   *
   * @return the price of unit price of stock in the transaction.
   */
  double getPrice();

  /**
   * get the amount of the stock in the transaction.
   *
   * @return the total amount.
   */
  double getAmount();

  /**
   * return a boolean value describing the trade type(buying or selling).
   *
   * @return true if it's trade for buying, false if it's trade for selling.
   */
  boolean isBuy();
}
