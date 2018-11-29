package tradesimulator.model.operation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tradesimulator.model.parser.DailyParser;
import tradesimulator.model.parser.IParser;
import tradesimulator.model.portfolio.Portfolio;
import tradesimulator.model.tradingitem.Stock;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Simulator model implements all the mechanisms like buy, create and determine operations.
 */
public class SimulatorModel implements SimulatorOperations<Stock> {

  private Map<String, Portfolio> portfolioMap;
  private static final String INIT_NAME = "init";
  private static final double COMMISSION_FEE_LIMIT = 100.0;
  private int portfSize;
  private double commissionFee;
  private IParser parser;

  /**
   * The portfolio constructor.
   *
   * @param portfSize the size of the portfolio.
   */
  public SimulatorModel(int portfSize) {
    this.portfSize = portfSize;
    portfolioMap = new HashMap<>();
    commissionFee = 5.0;
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
  public void buy(String stockSymbol, double volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException {
    checkPortfName(portfolioName);
    double price = parser.getPrice(stockSymbol, date);
    portfolioMap.get(portfolioName).addStockRecord(stockSymbol, date, volume, price);
  }

  /**
   * Create empty portfolios.
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
    checkPortfName(portfolioName);
    return portfolioMap.get(portfolioName).getTotalCostByDate(date, this.commissionFee);
  }

  @Override
  public double getTotalValue(LocalDate date, String portfolioName) {
    checkPortfName(portfolioName);
    double totalValue = 0.0;
    for (Map.Entry<String, Stock> entry
            : portfolioMap.get(portfolioName).getStockMap().entrySet()) {
      totalValue += entry.getValue().getVolume() * parser.getPrice(entry.getKey(), date);
    }
    return totalValue;
  }

  @Override
  public String getPortfolioState(String portfolioName) throws IllegalArgumentException {
    checkPortfName(portfolioName);
    return portfolioMap.get(portfolioName).toString();
  }

  @Override
  public String getPortfolioLog(String portfolioName) throws IllegalArgumentException {
    checkPortfName(portfolioName);
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

  @Override
  public void investFixedAmount(LocalDate date, double money, String portfolioName)
          throws IllegalArgumentException {
    checkPortfName(portfolioName);
    checkInvestDate(date);
    checkMoney(money);
    Portfolio tempPortf = portfolioMap.get(portfolioName);
    double average = money / tempPortf.getStockMap().size();
    for (Map.Entry<String, Stock> stockEntry : tempPortf.getStockMap().entrySet()) {
      double price = parser.getPrice(stockEntry.getKey(), date);
      double volume = average / price;
      tempPortf.addStockRecord(stockEntry.getKey(), date, volume, price);
    }
    portfolioMap.put(portfolioName, tempPortf);
  }

  @Override
  public void investSpecifiedAmount(LocalDate date, double money,
                                    Map<String, Double> ratioMap, String portfolioName)
          throws IllegalArgumentException {
    checkPortfName(portfolioName);
    checkInvestDate(date);
    checkMoney(money);
    Portfolio tempPortf = portfolioMap.get(portfolioName);
    for (Map.Entry<String, Double> ratioEntry : ratioMap.entrySet()) {
      String name = ratioEntry.getKey();
      double price = parser.getPrice(name, date);
      double volume = money * ratioMap.get(name) / price;
      tempPortf.addStockRecord(name, date, volume, price);
    }
    portfolioMap.put(portfolioName, tempPortf);
  }

  @Override
  public void createSpecifiedPortfolio(List<String> symbols, String portfolioName)
          throws IllegalArgumentException {
    if (portfolioMap.size() == portfSize) {
      throw new IllegalArgumentException(ModelErrMess.TOO_MUCH_PORTF.getMess());
    }
    if (portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.PORTF_NAME_EXISTS.getMess());
    }
    Portfolio newPortf = new Portfolio(portfolioName);
    for (int i = 0; i < symbols.size(); i++) {
      Stock stock = new Stock(symbols.get(i));
      newPortf.addStock(stock);
    }
    portfolioMap.put(portfolioName, newPortf);
  }

  @Override
  public void investPeriodically(String portfolioName, double money,
                                 Map<String, Double> ratioMap, LocalDate beginDate,
                                 LocalDate endDate, int interval)
          throws IllegalArgumentException {
    checkPortfName(portfolioName);
    checkBeginEndDateInterval(beginDate, endDate, interval);
    checkMoney(money);
    Portfolio tempPortf = portfolioMap.get(portfolioName);
    for (Map.Entry<String, Double> ratioEntry : ratioMap.entrySet()) {
      String name = ratioEntry.getKey();
      LocalDate tempDay = beginDate;

      tempDay = parser.getDateFromMap(name, tempDay);
      while (tempDay.isBefore(endDate)) {
        double price = parser.getPrice(name, tempDay);
        double volume = money * ratioEntry.getValue() / price;
        tempPortf.addStockRecord(name, tempDay, volume, price);
        tempDay = parser.getDateFromMap(name, tempDay.plusDays(interval));
      }
    }
    portfolioMap.put(portfolioName, tempPortf);
  }

  @Override
  public void setCommissionFee(double commissionFee)
          throws IllegalArgumentException {
    if (commissionFee < 0 || commissionFee > COMMISSION_FEE_LIMIT) {
      throw new IllegalArgumentException(ModelErrMess.ILLEGAL_COMMISSION_FEE.getMess());
    }
    this.commissionFee = commissionFee;
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
   * Check portfolio name.
   *
   * @param portfolioName portfolio name
   * @throws IllegalArgumentException if the argument is invalid
   */
  private void checkPortfName(String portfolioName)
          throws IllegalArgumentException {
    if (!portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(ModelErrMess.NO_SUCH_PORTF.getMess());
    }
  }

  /**
   * Check money amount.
   *
   * @param money money
   * @throws IllegalArgumentException if the money
   */
  private void checkMoney(double money) throws IllegalArgumentException {
    if (money < 0) {
      throw new IllegalArgumentException(ModelErrMess.ILLEGAL_MONEY.getMess());
    }
  }

  /**
   * Check Invest Date.
   *
   * @param date date
   * @throws IllegalArgumentException if the date is wrong
   */
  private void checkInvestDate(LocalDate date)
          throws IllegalArgumentException {
    if (date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException(
              ModelErrMess.INVEST_DATE_SHOULDNT_BE_FUTURE.getMess());
    }
  }

  /**
   * checkBeginEndDateInterval() checks begin date, end date, interval.
   *
   * @param begin    the begin date
   * @param end      the end date
   * @param interval the interval
   * @throws IllegalArgumentException if the argument is illegal
   */
  private void checkBeginEndDateInterval(LocalDate begin, LocalDate end, int interval)
          throws IllegalArgumentException {
    if (!begin.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException(ModelErrMess.BEGIN_DATE_IS_AFTER_NOW.getMess());
    }
    if (!end.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException(ModelErrMess.END_DATE_IS_AFTER_NOW.getMess());
    }
    if (!begin.isBefore(end)) {
      throw new IllegalArgumentException(ModelErrMess.BEGIN_DATE_IS_AFTER_END_DATE.getMess());
    }
    long days = DAYS.between(begin, end);
    if (interval <= 0 || interval >= days) {
      throw new IllegalArgumentException(ModelErrMess.ILLEGAL_INTERVAL.getMess());
    }
  }
}
