package tradesimulator.model.operation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import tradesimulator.model.parser.DailyParser;
import tradesimulator.model.parser.IParser;
import tradesimulator.model.portfolio.Portfolio;
import tradesimulator.model.tradingitem.Stock;

/**
 * Simulator model implements all the mechanisms like buy, create and determine operations.
 */
public class SimulatorModel implements SimulatorOperations<Stock> {

  private Map<String, Portfolio> portfolioMap;
  private static final String INIT_NAME = "init";
  private int portfSize;
  private IParser parser;

  /**
   * The portfolio constructor.
   *
   * @param portfSize the size of the portfolio.
   */
  public SimulatorModel(int portfSize) {
    this.portfSize = portfSize;
    portfolioMap = new HashMap<>();
    portfolioMap.put(INIT_NAME, new Portfolio(INIT_NAME));
    parser = new DailyParser();
  }

  /**
   * The class implements the SimulatorOperationsBuilder, which represents a builder.
   */
  public static class SimulatorBuilder implements SimulatorOperationsBuilder {

    protected int portfSize;

    protected static final int PORTF_SIZE_LIMIT = 16;

    @Override
    public SimulatorOperationsBuilder portfolios(int p) throws IllegalArgumentException {
      if (p <= 0) {
        throw new IllegalArgumentException(ModelErrMess.PORTF_SIZE_CANT_BE_NEG.getMess());
      }
      if (p > PORTF_SIZE_LIMIT) {
        throw new IllegalArgumentException(ModelErrMess.TOO_MUCH_PORTF.getMess());
      }
      this.portfSize = p;
      return this;
    }

    @Override
    public <Stock> SimulatorOperations<Stock> build() {
      return (SimulatorOperations<Stock>) new SimulatorModel(this.portfSize);
    }
  }

  @Override
  public void buy(String stockSymbol, int volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
    if (isHoliday(date)) {
      throw new IllegalArgumentException(ModelErrMess.CANT_BUY_ON_HOLIDAY.getMess());
    }
    double price = parser.getPrice(stockSymbol, date);
    portfolioMap.get(portfolioName).addStockRecord(stockSymbol, date, volume, price);
  }

  /**
   * Create nre portfolios.
   */
  @Override
  public void createPortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioMap.size() == portfSize) {
      throw new IllegalArgumentException(ModelErrMess.TOO_MUCH_PORTF.getMess());
    }

    if (portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.PORTF_NAME_EXISTS.getMess());
    }

    portfolioMap.put(portfolioName, new Portfolio(portfolioName));
  }

  @Override
  public double getTotalCostBasis(LocalDate date, String portfolioName) {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
    return portfolioMap.get(portfolioName).getTotalCostByDate(date);
  }

  @Override
  public double getTotalValue(LocalDate date, String portfolioName) {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
    double totalValue = 0.0;
    for (Map.Entry<String, Stock> entry
            : portfolioMap.get(portfolioName).getStockMap().entrySet()) {
      totalValue += entry.getValue().getVolume() * parser.getPrice(entry.getKey(), date);
    }
    return totalValue;
  }

  @Override
  public String getPortfolioState(String portfolioName) throws IllegalArgumentException {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
    return portfolioMap.get(portfolioName).toString();
  }

  @Override
  public String getPortfolioLog(String portfolioName) throws IllegalArgumentException {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
    return portfolioMap.get(portfolioName).recordLog();
  }

  @Override
  public String getPortfolioList() {
    StringBuilder sb = new StringBuilder();
    for (String name : portfolioMap.keySet()) {
      sb.append(name + "\n");

    }
    return sb.toString();
  }

  /**
   * Get a new builder.
   *
   * @return a new builder.
   */
  public static SimulatorOperationsBuilder getBuilder() {
    return new SimulatorBuilder();
  }


  /**
   * Private helper to check if this current date is holiday.
   *
   * @param date the passing date
   * @return if it is the holiday, it'll return true
   */
  private boolean isHoliday(LocalDate date) {
    return date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY;
  }
}
