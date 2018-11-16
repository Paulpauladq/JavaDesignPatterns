import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import tradesimulator.model.operation.ModelErrMess;
import tradesimulator.model.operation.SimulatorModel;
import tradesimulator.model.operation.SimulatorOperations;
import tradesimulator.model.tradingitem.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Simulator model test class to test all the cases.
 */
public class SimulatorModelTest {

  private SimulatorOperations<Stock> simulator1;
  private SimulatorOperations<Stock> simulator2;
  //private SimulatorOperations<Stock> simulator3;
  private String str1;
  private String str2;
  private String str3;
  private String str4;

  /**
   * Set up three SimulatorModel objects for further test.
   */
  @Before
  public void setUp() {
    simulator1 = SimulatorModel.getBuilder().build();
    simulator2 = SimulatorModel.getBuilder().portfolios(2).build();
    str1 = "Stock: AMZN Cost:309204.0 Volume:200\n"
            + "Stock: GOOG Cost:243012.0 Volume:200\n";
    str2 = "Stock: FB Date:2018-10-03 bought Amount:300 Price:159.53\n"
            + "Stock: GOOG Date:2018-08-03 bought Amount:200 Price:1215.06\n";
  }

  /**
   * tests for creating a new portfolio.
   */
  @Test
  public void testCreatingPortfolio() {
    simulator2.createPortfolio("sp2");
    //add portfolio exceeding the number.
    try {
      simulator2.createPortfolio("sp3");
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(ModelErrMess.TOO_MUCH_PORTF.getMess(), e.getMessage());
    }
    // in createPortfolio(), exception occurs when portfolio name is already in the simulator:
    // portfolioMap.containsKey(portfolioName).
    try {
      simulator1.createPortfolio("init");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.PORTF_NAME_EXISTS.getMess(), e.getMessage());
    }
    //if the portfolio number exceeds 16.
    try {
      SimulatorOperations<Stock> simulator3 = SimulatorModel.getBuilder().portfolios(17).build();
      //simulator2.createPortfolio("sp3");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.TOO_MUCH_PORTF.getMess(), e.getMessage());
    }
    //if the portfolio number is less than 0.
    try {
      SimulatorOperations<Stock> simulator4 = SimulatorModel.getBuilder().portfolios(-1).build();
      //simulator2.createPortfolio("sp3");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.PORTF_SIZE_CANT_BE_NEG.getMess(), e.getMessage());
    }
    //if the portfolio number = 0.
    try {
      SimulatorOperations<Stock> simulator4 = SimulatorModel.getBuilder().portfolios(0).build();
      //simulator2.createPortfolio("sp3");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.PORTF_SIZE_CANT_BE_NEG.getMess(), e.getMessage());
    }
    //if the portfolio number = 0.


  }

  /**
   * tests for using methods in SimulatorModel.
   */
  @Test
  public void testMethods() {
    simulator1.createPortfolio("s2");
    simulator1.createPortfolio("s3");
    //test: buy().
    simulator1.buy("GOOG", 200, LocalDate.of(2018, 8, 3), "init");
    simulator1.buy("AMZN", 200, LocalDate.of(2018, 5, 3), "init");
    simulator1.buy("GOOG", 200, LocalDate.of(2018, 8, 3), "s2");
    simulator1.buy("FB", 300, LocalDate.of(2018, 10, 3), "s2");
    //test: getPortfolioState().
    assertEquals(str1, simulator1.getPortfolioState("init"));
    //test: getPortfolioList().
    assertTrue(simulator1.getPortfolioList().contains("s3"));
    assertTrue(simulator1.getPortfolioList().contains("init"));
    assertTrue(simulator1.getPortfolioList().contains("s2"));
    //test: getPortfolioLog().
    assertEquals(str2, simulator1.getPortfolioLog("s2"));
    //test: getTotalValue().
    assertEquals(252894, simulator1.getTotalValue(LocalDate.of(2018, 5, 3), "s2"), 4);
    assertEquals(510462, simulator1.getTotalValue(LocalDate.of(2018, 5, 3), "init"), 4);
  }

  /**
   * tests for using getTotalCostBasis() method in SimulatorModel.
   */
  @Test
  public void getTotalCostBasis() {
    SimulatorOperations<Stock> simulator3 = SimulatorModel.getBuilder().build();
    simulator3.buy("GOOG", 200, LocalDate.of(2015, 8, 3), "init");
    simulator3.buy("GOOG", 200, LocalDate.of(2015, 4, 23), "init");
    assertEquals(233114, simulator3.getTotalCostBasis(LocalDate.of(2016, 1, 1), "init"), 4);
    //System.out.println(simulator3.getTotalCostBasis(LocalDate.of(2016,1,1),"init"));
  }

  /**
   * tests Exception cases.
   */
  @Test
  public void testException() {
    SimulatorOperations<Stock> simulator3 = SimulatorModel.getBuilder().build();
    /**
     * in buy(), exception occurs when: !portfolioMap.containsKey(portfolioName).
     */
    try {
      simulator3.buy("GOOG", 200, LocalDate.of(2018, 4, 3), "NONONO");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.NO_SUCH_PORTF.getMess(), e.getMessage());
    }
    /**
     * in getTotalCostBasis(), exception occurs when: !portfolioMap.containsKey(portfolioName).
     */
    try {
      simulator3.getTotalCostBasis(LocalDate.of(2018, 8, 3), "fff");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.NO_SUCH_PORTF.getMess(), e.getMessage());
    }
    /**
     * in getTotalValue(), exception occurs when: !portfolioMap.containsKey(portfolioName).
     */
    try {
      simulator3.getTotalValue(LocalDate.of(2018, 8, 3), "fff");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.NO_SUCH_PORTF.getMess(), e.getMessage());
    }
    /**
     * in getPortfolioState(), exception occurs when: !portfolioMap.containsKey(portfolioName).
     */
    try {
      simulator3.getPortfolioState("fff");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.NO_SUCH_PORTF.getMess(), e.getMessage());
    }
    /**
     * in getPortfolioLog(), exception occurs when: !portfolioMap.containsKey(portfolioName).
     */
    try {
      simulator3.getPortfolioLog("fff");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.NO_SUCH_PORTF.getMess(), e.getMessage());
    }
  }
}