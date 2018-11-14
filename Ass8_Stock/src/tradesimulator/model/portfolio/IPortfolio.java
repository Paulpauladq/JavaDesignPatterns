package tradesimulator.model.portfolio;

import java.time.LocalDate;

public interface IPortfolio<K> {

  public double getTotalCost();

  public void addStock(String symbol, LocalDate date, int volume, double price);

  public void deleteStock(String symbol, LocalDate date, int volume, double price);

  public String toString();

  public String recordLog();
}
