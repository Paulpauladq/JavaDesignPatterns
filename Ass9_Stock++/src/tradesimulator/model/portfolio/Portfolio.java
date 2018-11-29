package tradesimulator.model.portfolio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import tradesimulator.model.tradingitem.Stock;

/**
 * The class implements Iportfolio, which represents a Portfolio.
 */
public class Portfolio implements IPortfolio<Stock> {
  private Map<String, Stock> stockMap;
  private double totalCost;
  private String portfolioName;

  /**
   * The constructor of the Portfolio.
   *
   * @param portfolioName the name of the new portfolio.
   */
  public Portfolio(String portfolioName) {
    this.portfolioName = portfolioName;
    stockMap = new HashMap<>();
    totalCost = 0.0;
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public double getTotalCost() {
    double total = 0.0;
    for (Map.Entry<String, Stock> entry : stockMap.entrySet()) {
      total += entry.getValue().getCost();
    }
    totalCost = total;
    return totalCost;
  }

  @Override
  public double getTotalCostByDate(LocalDate date, double commissionFee) {
    double totalCostByDate = 0.0;
    for (Map.Entry<String, Stock> entry : stockMap.entrySet()) {
      totalCostByDate += entry.getValue().getCostByDate(date, commissionFee);
    }
    return totalCostByDate;
  }

  @Override
  public Map<String, Stock> getStockMap() {
    return stockMap;
  }

  /**
   * The method sorts the stockMap by the name of the stock.
   */
  private void stockMapSort() {
    TreeMap<String, Stock> sorted = new TreeMap<>();
    sorted.putAll(stockMap);
    this.stockMap = sorted;
  }


  @Override
  public void addStockRecord(String symbol, LocalDate date, double volume, double price) {
    if (stockMap.containsKey(symbol)) {
      Stock tempStock = stockMap.get(symbol);
      tempStock.addRecord(date, volume, price);
      stockMap.put(symbol, tempStock);
    }
    //it's a new company
    else {
      Stock tempStock = new Stock(symbol);
      tempStock.addRecord(date, volume, price);
      stockMap.put(symbol, tempStock);
      stockMapSort();
    }
  }

  /**
   * Add stock to the portfolio.
   *
   * @param stock the stock to be added.
   */
  @Override
  public void addStock(Stock stock) {
    if (stockMap.containsKey(stock.getSymbol())) {
      stockMap.put(stock.getSymbol(), stock);
    } else {
      stockMap.put(stock.getSymbol(), stock);
    }
    stockMapSort();
  }

  @Override
  public void deleteStock(String symbol, LocalDate date, double volume, double price) {
    return;
  }

  /**
   * recordLog() method is used to record the log.
   *
   * @return the log String
   */
  public String recordLog() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Stock> entry : stockMap.entrySet()) {
      sb.append(entry.getValue().recordLog());
    }
    return sb.toString();
  }

  /**
   * Get the summary of the portfolio.
   *
   * @return the record of the portfolio.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Stock> entry : stockMap.entrySet()) {
      sb.append(entry.getValue().toString());
    }
    return sb.toString();
  }
}
