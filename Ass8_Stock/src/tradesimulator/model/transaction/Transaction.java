package tradesimulator.model.transaction;

import java.time.LocalDate;

public class Transaction implements ITransaction {
  private LocalDate date;
  private int amount;
  private double price;
  private boolean isBuy;

  public Transaction(LocalDate date, int amount, double price, boolean isBuy){
    this.date = date;
    this.amount = amount;
    this.price = price;
    this.isBuy = isBuy;
  }

  public LocalDate getDate(){
    return date;
  }

  public double getPrice(){
    return price;
  }

  public int getAmount(){
    return amount;
  }

  public boolean isBuy(){
    return isBuy;
  }
}
