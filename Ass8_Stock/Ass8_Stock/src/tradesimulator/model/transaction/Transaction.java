package tradesimulator.model.transaction;

import java.time.LocalDate;

/**
 * The class implements ITransaction, which represents a transaction.
 */
public class Transaction implements ITransaction {
  private LocalDate date;
  private int amount;
  private double price;
  private boolean isBuy;

  /**
   * the constructor of Transaction.
   *
   * @param date   the date that a transaction occurs.
   * @param amount the amount of the stock traded in the transaction.
   * @param price  the unit price of the stock traded in the transaction.
   * @param isBuy  whether it's a trade of buying.
   */
  public Transaction(LocalDate date, int amount, double price, boolean isBuy) {
    this.date = date;
    this.amount = amount;
    this.price = price;
    this.isBuy = isBuy;
  }

  public LocalDate getDate() {
    return date;
  }

  public double getPrice() {
    return price;
  }

  public int getAmount() {
    return amount;
  }

  public boolean isBuy() {
    return isBuy;
  }
}
