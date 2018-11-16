package tradesimulator.model.tradingitem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tradesimulator.model.transaction.Transaction;

/**
 * The class implements TradingItem, which represents a stock.
 */
public class Stock implements TradingItem {

  private String stockSymbol;
  private double cost;
  private int volume;
  private List<Transaction> recordList;

  /**
   * The constructor of the stock.
   *
   * @param stockSymbol the stock symbol in String.
   */
  public Stock(String stockSymbol) {

    this.stockSymbol = stockSymbol.toUpperCase();
    this.volume = 0;
    this.cost = 0.0;
    this.recordList = new ArrayList<>();
  }

  @Override
  public String getSymbol() {
    return stockSymbol;
  }

  @Override
  public double getCost() {
    return cost;
  }

  @Override
  public double getCostByDate(LocalDate date) {
    double costByDate = 0.0;
    for (Transaction record : recordList) {
      if (record.getDate().compareTo(date) <= 0) {
        costByDate += record.getPrice() * record.getAmount();
      }
    }
    return costByDate;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  /**
   * Add transaction to the record list and update the variables of the Stock.
   */
  @Override
  public void addRecord(LocalDate date, int volume, double price) {
    Transaction record = new Transaction(date, volume, price, true);
    recordList.add(record);
    listSort();
    //update the stock variables
    this.cost += record.getPrice() * record.getAmount();
    this.volume += record.getAmount();
  }

  /**
   * Get the record of the stock.
   *
   * @return the record of the stock.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Stock: " + stockSymbol + " Cost:" + cost + " Volume:" + volume + "\n");
    return sb.toString();
  }

  @Override
  public String recordLog() {
    StringBuilder sb = new StringBuilder();
    for (Transaction record : recordList) {
      sb.append("Stock: " + stockSymbol + " Date:" + record.getDate() + " "
              + (record.isBuy() ? "bought" : "sold") + " "
              + "Amount:" + record.getAmount() + " Price:"
              + record.getPrice() + "\n");
    }
    return sb.toString();
  }

  /**
   * Sort the recordList according to the purchase date.
   */
  private void listSort() {
    Collections.sort(recordList, new Comparator<Transaction>() {
      @Override
      public int compare(Transaction t1, Transaction t2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
          if (t1.getDate().compareTo(t2.getDate()) < 0) {
            return -1;
          } else if (t1.getDate().compareTo(t2.getDate()) > 0) {
            return 1;
          } else {
            return 0;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
      }
    });
  }

}
