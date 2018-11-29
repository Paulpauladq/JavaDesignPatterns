import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

/**
 * Mock model in order to test the controller.
 */
public class MockModel implements SimulatorOperations<Stock> {

  private StringBuilder log;
  private final double uniqueCodeCost;
  private final double uniqueCodeValue;
  private final String uniqueCode;


  /**
   * Constructor of mock model.
   *
   * @param log             the log String
   * @param uniqueCodeCost  the unique code for cost
   * @param uniqueCodeValue the unique code for value
   */
  public MockModel(StringBuilder log, double uniqueCodeCost,
                   double uniqueCodeValue, String uniqueCode) {
    this.log = log;
    this.uniqueCodeCost = uniqueCodeCost;
    this.uniqueCodeValue = uniqueCodeValue;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void buy(String stockSymbol, double volume, LocalDate date, String portfolioName)
          throws IllegalArgumentException {
    log.append("Buy operation:\nStock symbol:" + stockSymbol + " Volume: " + volume
            + " Date: " + date.toString() + " Portfolio Name:" + portfolioName + "\n");
  }

  @Override
  public void createPortfolio(String portfolioName)
          throws IllegalArgumentException {
    log.append("Create operation:\nPortfolio Name:" + portfolioName + "\n");
  }

  @Override
  public double getTotalCostBasis(LocalDate date, String portfolioName) {
    log.append("Get cost operation:\nDate:" + date.toString()
            + " Portfolio Name:" + portfolioName + "\n");
    return uniqueCodeCost;
  }

  @Override
  public double getTotalValue(LocalDate date, String portfolioName) {
    log.append("Get value operation:\nDate:" + date.toString()
            + " Portfolio Name:" + portfolioName + "\n");
    return uniqueCodeValue;
  }

  @Override
  public String getPortfolioState(String portfolioName)
          throws IllegalArgumentException {
    return uniqueCode;
  }

  @Override
  public String getPortfolioLog(String portfolioName) throws IllegalArgumentException {
    return uniqueCode;
  }

  @Override
  public String getPortfolioList() {
    return uniqueCode;
  }

  @Override
  public void investFixedAmount(LocalDate date, double money, String portfolioName)
          throws IllegalArgumentException {
    log.append("Invest fixed operation:\nDate:" + date.toString() + " Money: " + money
            + " Portfolio Name:" + portfolioName + "\n");
  }

  @Override
  public void investSpecifiedAmount(LocalDate date, double money, Map<String, Double> ratioMap,
                                    String portfolioName)
          throws IllegalArgumentException {
    log.append("Invest specified operation:\nDate:" + date.toString() + " Money: " + money
            + " Portfolio Name:" + portfolioName + "\n");
  }

  @Override
  public void createSpecifiedPortfolio(List<String> symbols, String portfolioName)
          throws IllegalArgumentException {
    log.append("Create with specified stocks operation:\nPortfolio Name:" + portfolioName + "\n");
  }

  @Override
  public void investPeriodically(String portfolioName, double money, Map<String, Double> ratioMap,
                                 LocalDate beginDate, LocalDate endDate,
                                 int interval) throws IllegalArgumentException {
    log.append("Invest periodically in specified ratio operation:\nDate:from "
            + beginDate.toString() + " to " + endDate.toString() + "\nMoney:"
            + money + " Portfolio Name:" + portfolioName + "\nInterval:" + interval + "\n");
  }

  @Override
  public void setCommissionFee(double commissionFee) throws IllegalArgumentException {
    log.append("Set commission fee" + commissionFee + "\n");
  }
}
