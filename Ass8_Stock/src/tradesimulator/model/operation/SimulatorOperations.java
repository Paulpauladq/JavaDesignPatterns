package tradesimulator.model.operation;

import java.time.LocalDate;
import java.util.List;

public interface SimulatorOperations<K>  {

  void buy(String stockSymbol, int volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException;

  void createPortfolio(String portfolioName) throws IllegalArgumentException;

  double getTotalCostBasis(LocalDate date);

  double getTotalValue(LocalDate date);

  String getPortfolioState(String portfolioName) throws IllegalArgumentException;

  String getPortfolioLog(String portfolioName) throws IllegalArgumentException;

  String getPortfolioList();

}
