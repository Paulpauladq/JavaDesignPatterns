package tradesimulator.model.transaction;

import java.time.LocalDate;

/**
 * The class implements ITransaction, which represents a transaction.
 */
public class Transaction implements ITransaction {
  private LocalDate date;
  private double amount;
  private double price;
  private boolean isBuy;

  /**
   * the constructor of Transaction.
   *  @param date   the date that a transaction occurs.
   * @param amount the amount of the stock traded in the transaction.
   * @param price  the unit price of the stock traded in the transaction.
   * @param isBuy  whether it's a trade of buying.
   */
  public Transaction(LocalDate date, double amount, double price, boolean isBuy) {
    this.date = date;
    this.amount = amount;
    this.price = price;
    this.isBuy = isBuy;
  }

  @Override
  public LocalDate getDate() {
    return date;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public boolean isBuy() {
    return isBuy;
  }
}
