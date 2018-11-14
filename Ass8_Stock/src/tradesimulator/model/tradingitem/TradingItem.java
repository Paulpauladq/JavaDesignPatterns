package tradesimulator.model.tradingitem;

import java.time.LocalDate;

/**
 * Interface of Stock
 */
public interface TradingItem {

  String getSymbol();

  double getCost();

  int getVolume();

  void addRecord(LocalDate date, int volume, double price);

  String recordLog();
}
