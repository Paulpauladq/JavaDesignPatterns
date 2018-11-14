package tradesimulator.model.tradingitem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tradesimulator.model.transaction.Transaction;

public class Stock implements TradingItem {

  private String stockSymbol;
  private double cost;
  private int volume;
  private List<Transaction> recordList;

  public Stock(String stockSymbol){

    this.stockSymbol = stockSymbol;
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
  public int getVolume() {
    return volume;
  }

  /**
   * Add transaction to the record list and update the variables of the Stock.
   * @param date
   * @param volume
   * @param price
   */
  @Override
  public void addRecord(LocalDate date, int volume, double price) {
    Transaction record = new Transaction(date, volume, price, true);
    recordList.add(record);

    //update the stock variables
    this.cost += record.getPrice() * record.getAmount();
    this.volume += record.getAmount();
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Stock: " + stockSymbol + " Cost:" + cost + " Volume:" + volume + "\n");
    return sb.toString();
  }

  @Override
  public String recordLog(){
    StringBuilder sb = new StringBuilder();
    for(Transaction record : recordList){
      sb.append("Stock: " + stockSymbol + " Date:" + record.getDate() + " "
              + (record.isBuy() ? "bought" : "sold") + " "
              + "Amount:" + record.getAmount() + "\n");
    }
    return sb.toString();
  }

  public static void main(String[] args){
   Stock obj = new Stock("APPL");
    obj.addRecord(LocalDate.of(1990,11,13),100, 10.0);
    obj.addRecord(LocalDate.of(2011,2,1),100, 20.01111);
    obj.addRecord(LocalDate.of(2021,2,12),200, 15.23);
    obj.addRecord(LocalDate.of(1990,11,13),100, 12.45);
    System.out.println(LocalDate.of(1990,1,1));
    System.out.println(obj.recordLog());
    System.out.println(obj.toString());
    System.out.println(obj.getCost());
    System.out.println(obj.getVolume());
  }
}
