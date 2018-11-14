package tradesimulator.model.portfolio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import tradesimulator.model.tradingitem.Stock;

public class Portfolio implements IPortfolio<Stock>{
  private Map<String, Stock> stockMap;
  private double totalCost;
  private String portfolioName;

  public Portfolio(String portfolioName){
    this.portfolioName = portfolioName;
    stockMap = new HashMap<>();
    totalCost = 0.0;
  }

  public String getPortfolioName(){
    return portfolioName;
  }

  @Override
  public double getTotalCost(){
    return totalCost;
  }

  /**
   * Add stock or update stock in the stock map.
   * @param symbol
   * @param date
   * @param volume
   * @param price
   */
  @Override
  public void addStock(String symbol, LocalDate date, int volume, double price) {
    if(stockMap.containsKey(symbol)){
      Stock tempStock = stockMap.get(symbol);
      //TBD  how to get price???????????????
      tempStock.addRecord(date, volume, price);
      //update map and the cost
      totalCost += tempStock.getCost();
      stockMap.put(symbol, tempStock);

    }
    //it's a new company
    else{
      Stock tempStock = new Stock(symbol);
      tempStock.addRecord(date, volume, price);
      totalCost += tempStock.getCost();
      stockMap.put(symbol, tempStock);
    }
  }

  /**
   * delete/update the stock item in portfolio.
   * @param symbol
   * @param date
   * @param volume
   * @param price
   */
  @Override
  public void deleteStock(String symbol, LocalDate date, int volume, double price) {
    return;
  }

  public String recordLog(){
    StringBuilder sb = new StringBuilder();
    for(Map.Entry<String, Stock> entry : stockMap.entrySet()){
      sb.append(entry.getValue().recordLog());
    }
    return sb.toString();
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    for(Map.Entry<String, Stock> entry : stockMap.entrySet()){
      sb.append(entry.getValue().toString());
    }
    return sb.toString();
  }

  public static void main(String args[]){
    Portfolio obj = new Portfolio("Dsakjdh");

    obj.addStock("AMAZ",LocalDate.of(1990,11,13),10, 1.12);
    obj.addStock("GOOG",LocalDate.of(1990,11,13),10, 100.12);
    obj.addStock("GOOG",LocalDate.of(2001,12,14),50,  111.12);
    obj.addStock("APPL",LocalDate.of(1990,11,13),130, 1.412);
    obj.addStock("APPL",LocalDate.of(2090,11,13),0, 122);
    obj.addStock("APPL",LocalDate.of(1990,11,13),230, 109);
    obj.addStock("APPL",LocalDate.of(1990,11,13),230, 12);

    System.out.println(obj.getPortfolioName());
    System.out.println(obj.toString());
    System.out.println(obj.recordLog());
    System.out.println(obj.getTotalCost());

  }
}
