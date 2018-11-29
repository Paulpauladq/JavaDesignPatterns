import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    str1 = "Stock: AMZN Cost:309204.00 Volume:200.00\n"
            + "Stock: GOOG Cost:243012.00 Volume:200.00\n";
    str2 = "Stock: FB Date:2018-11-10 bought Amount:300.00 Price:140.49\n"
            + "Stock: GOOG Date:2018-08-03 bought Amount:200.00 Price:1215.06\n";
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
    simulator1.buy("FB", 300, LocalDate.of(2018, 11, 10), "s2");
    //test: getPortfolioState().
    assertEquals(str1, simulator1.getPortfolioState("init"));
    //test: getPortfolioList().
    assertTrue(simulator1.getPortfolioList().contains("s3"));
    assertTrue(simulator1.getPortfolioList().contains("init"));
    assertTrue(simulator1.getPortfolioList().contains("s2"));
    //test: getPortfolioLog().
    assertEquals(str2, simulator1.getPortfolioLog("s2"));
    //test: getTotalValue().
    assertEquals(252894, simulator1.getTotalValue(LocalDate.of(2018, 5, 3), "s2"), 0.01);
    assertEquals(510462, simulator1.getTotalValue(LocalDate.of(2018, 5, 3), "init"), 0.01);
  }

  /**
   * tests for using getTotalCostBasis() method in SimulatorModel.
   */
  @Test
  public void getTotalCostBasis() {
    SimulatorOperations<Stock> simulator3 = SimulatorModel.getBuilder().build();
    simulator3.buy("GOOG", 200, LocalDate.of(2015, 8, 3), "init");
    simulator3.buy("GOOG", 200, LocalDate.of(2015, 4, 23), "init");
    assertEquals(233104.0, simulator3.getTotalCostBasis(LocalDate.of(2016, 1, 1), "init"), 0.01);
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


  @Test
  public void testCreateSpecifiedPortfolio() {

    String[] stockArray = {"GOOG", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    simulator1.createSpecifiedPortfolio(list, "f1");
    String[] stock2 = {"GOOG", "AMZN", "TWTR"};
    List<String> list2 = new ArrayList<>();
    for (String str : stock2) {
      list2.add(str);
    }
    simulator1.createSpecifiedPortfolio(list2, "f2");
    try {
      simulator1.createSpecifiedPortfolio(list2, "f2");
    } catch (IllegalArgumentException e) {
      assertEquals("Portfolio name exists!", e.getMessage());
    }
    simulator1.investFixedAmount(LocalDate.of(2018, 10, 1), 20000, "f1");
  }

  @Test
  public void testInvestFixedAmount() {
    String[] stockArray = {"GOOG", "TWTR", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    simulator1.createSpecifiedPortfolio(list, "f1");
    simulator1.createSpecifiedPortfolio(list, "f2");
    simulator1.investFixedAmount(LocalDate.of(2018, 10, 1), 20000, "f1");
    simulator1.investFixedAmount(LocalDate.of(2018, 3, 1), 20000, "f2");
    assertEquals("Stock: AMZN Cost:5000.00 Volume:2.50\n"
            + "Stock: GOOG Cost:5000.00 Volume:4.20\n"
            + "Stock: MSFT Cost:5000.00 Volume:43.58\n"
            + "Stock: TWTR Cost:5000.00 Volume:178.57\n", simulator1.getPortfolioState("f1"));
    assertEquals("Stock: AMZN Cost:5000.00 Volume:3.41\n"
            + "Stock: GOOG Cost:5000.00 Volume:4.69\n"
            + "Stock: MSFT Cost:5000.00 Volume:54.44\n"
            + "Stock: TWTR Cost:5000.00 Volume:159.95\n", simulator1.getPortfolioState("f2"));
    assertEquals(19980.0, simulator1.getTotalCostBasis(LocalDate.of(2018, 10, 10), "f1"), 0.01);
    assertEquals(18308.774, simulator1.getTotalValue(LocalDate.of(2018, 10, 10), "f1"), 0.01);
    assertEquals(19980.0, simulator1.getTotalCostBasis(LocalDate.of(2018, 10, 10), "f2"), 0.01);
    assertEquals(21093.888, simulator1.getTotalValue(LocalDate.of(2018, 10, 10), "f2"), 0.01);
  }

  @Test
  public void testInvestSpecifiedAmount() {
    String[] stockArray = {"GOOG", "TWTR", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    Map<String, Double> ratioMap = new HashMap<>();
    ratioMap.put("GOOG", 0.3);
    ratioMap.put("TWTR", 0.2);
    ratioMap.put("AMZN", 0.2);
    ratioMap.put("MSFT", 0.3);
    simulator1.createSpecifiedPortfolio(list, "f1");
    simulator1.investSpecifiedAmount(LocalDate.of(2018, 3, 1), 20000,
            ratioMap, "f1");
    assertEquals(19980.0,
            simulator1.getTotalCostBasis(LocalDate.of(2018, 4, 1), "f1"), 0.01);
    assertEquals("Stock: AMZN Cost:4000.00 Volume:2.73\n"
            + "Stock: GOOG Cost:6000.00 Volume:5.62\n"
            + "Stock: MSFT Cost:6000.00 Volume:65.33\n"
            + "Stock: TWTR Cost:4000.00 Volume:127.96\n", simulator1.getPortfolioState("f1"));
    assertEquals("Stock: AMZN Date:2018-03-01 bought Amount:2.73 Price:1465.00\n"
                    + "Stock: GOOG Date:2018-03-01 bought Amount:5.62 Price:1067.00\n"
                    + "Stock: MSFT Date:2018-03-01 bought Amount:65.33 Price:91.84\n"
                    + "Stock: TWTR Date:2018-03-01 bought Amount:127.96 Price:31.26\n",
            simulator1.getPortfolioLog("f1"));
    simulator1.investSpecifiedAmount(LocalDate.of(2018, 5, 1), 20000,
            ratioMap, "f1");
    assertEquals(39960.0,
            simulator1.getTotalCostBasis(
                    LocalDate.of(2018, 6, 1), "f1"), 0.01);
  }

  @Test
  public void testInvestStockPeriodically() {
    String[] stockArray = {"GOOG", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    Map<String, Double> ratioMap = new HashMap<>();
    ratioMap.put("GOOG", 0.3);
    ratioMap.put("AMZN", 0.4);
    ratioMap.put("MSFT", 0.3);
    simulator1.createSpecifiedPortfolio(list, "f1");
    simulator1.investPeriodically("f1",
            20000.0, ratioMap, LocalDate.of(2018, 5, 15),
            LocalDate.of(2018, 10, 1), 7);
    assertEquals(0.0, simulator1.getTotalCostBasis(LocalDate.of(2018, 1, 5), "f1"), 0.01);
    assertEquals(0.0, simulator1.getTotalCostBasis(LocalDate.of(2018, 2, 5), "f1"), 0.01);
    assertEquals(399700.0, simulator1.getTotalCostBasis(LocalDate.of(2018, 10, 1), "f1"), 0.01);
    assertEquals("Stock: AMZN Date:2018-05-15 bought Amount:5.11 Price:1565.22\n"
                    + "Stock: AMZN Date:2018-05-22 bought Amount:5.08 Price:1575.25\n"
                    + "Stock: AMZN Date:2018-05-29 bought Amount:5.00 Price:1600.15\n"
                    + "Stock: AMZN Date:2018-06-05 bought Amount:4.79 Price:1670.06\n"
                    + "Stock: AMZN Date:2018-06-12 bought Amount:4.73 Price:1691.52\n"
                    + "Stock: AMZN Date:2018-06-19 bought Amount:4.70 Price:1700.39\n"
                    + "Stock: AMZN Date:2018-06-26 bought Amount:4.81 Price:1663.34\n"
                    + "Stock: AMZN Date:2018-07-03 bought Amount:4.73 Price:1692.48\n"
                    + "Stock: AMZN Date:2018-07-10 bought Amount:4.62 Price:1731.00\n"
                    + "Stock: AMZN Date:2018-07-17 bought Amount:4.45 Price:1797.38\n"
                    + "Stock: AMZN Date:2018-07-24 bought Amount:4.42 Price:1809.38\n"
                    + "Stock: AMZN Date:2018-07-31 bought Amount:4.60 Price:1739.32\n"
                    + "Stock: AMZN Date:2018-08-07 bought Amount:4.33 Price:1846.27\n"
                    + "Stock: AMZN Date:2018-08-14 bought Amount:4.21 Price:1900.00\n"
                    + "Stock: AMZN Date:2018-08-21 bought Amount:4.27 Price:1874.40\n"
                    + "Stock: AMZN Date:2018-08-28 bought Amount:4.15 Price:1928.82\n"
                    + "Stock: AMZN Date:2018-09-04 bought Amount:3.97 Price:2013.00\n"
                    + "Stock: AMZN Date:2018-09-11 bought Amount:4.17 Price:1917.00\n"
                    + "Stock: AMZN Date:2018-09-18 bought Amount:4.18 Price:1915.44\n"
                    + "Stock: AMZN Date:2018-09-25 bought Amount:4.13 Price:1938.85\n"
                    + "Stock: GOOG Date:2018-05-15 bought Amount:5.59 Price:1073.47\n"
                    + "Stock: GOOG Date:2018-05-22 bought Amount:5.62 Price:1066.69\n"
                    + "Stock: GOOG Date:2018-05-29 bought Amount:5.69 Price:1055.22\n"
                    + "Stock: GOOG Date:2018-06-05 bought Amount:5.29 Price:1133.19\n"
                    + "Stock: GOOG Date:2018-06-12 bought Amount:5.31 Price:1130.73\n"
                    + "Stock: GOOG Date:2018-06-19 bought Amount:5.20 Price:1154.01\n"
                    + "Stock: GOOG Date:2018-06-26 bought Amount:5.37 Price:1116.66\n"
                    + "Stock: GOOG Date:2018-07-03 bought Amount:5.45 Price:1100.02\n"
                    + "Stock: GOOG Date:2018-07-10 bought Amount:5.22 Price:1149.59\n"
                    + "Stock: GOOG Date:2018-07-17 bought Amount:5.13 Price:1170.60\n"
                    + "Stock: GOOG Date:2018-07-24 bought Amount:4.86 Price:1235.56\n"
                    + "Stock: GOOG Date:2018-07-31 bought Amount:4.98 Price:1205.60\n"
                    + "Stock: GOOG Date:2018-08-07 bought Amount:4.85 Price:1236.17\n"
                    + "Stock: GOOG Date:2018-08-14 bought Amount:4.90 Price:1225.11\n"
                    + "Stock: GOOG Date:2018-08-21 bought Amount:5.00 Price:1200.35\n"
                    + "Stock: GOOG Date:2018-08-28 bought Amount:4.88 Price:1228.69\n"
                    + "Stock: GOOG Date:2018-09-04 bought Amount:5.03 Price:1192.50\n"
                    + "Stock: GOOG Date:2018-09-11 bought Amount:5.19 Price:1156.24\n"
                    + "Stock: GOOG Date:2018-09-18 bought Amount:5.19 Price:1157.09\n"
                    + "Stock: GOOG Date:2018-09-25 bought Amount:5.14 Price:1168.00\n"
                    + "Stock: MSFT Date:2018-05-15 bought Amount:62.28 Price:96.34\n"
                    + "Stock: MSFT Date:2018-05-22 bought Amount:61.73 Price:97.20\n"
                    + "Stock: MSFT Date:2018-05-29 bought Amount:61.71 Price:97.23\n"
                    + "Stock: MSFT Date:2018-06-05 bought Amount:59.10 Price:101.53\n"
                    + "Stock: MSFT Date:2018-06-12 bought Amount:59.55 Price:100.75\n"
                    + "Stock: MSFT Date:2018-06-19 bought Amount:60.30 Price:99.50\n"
                    + "Stock: MSFT Date:2018-06-26 bought Amount:60.76 Price:98.75\n"
                    + "Stock: MSFT Date:2018-07-03 bought Amount:60.64 Price:98.94\n"
                    + "Stock: MSFT Date:2018-07-10 bought Amount:58.90 Price:101.86\n"
                    + "Stock: MSFT Date:2018-07-17 bought Amount:57.52 Price:104.32\n"
                    + "Stock: MSFT Date:2018-07-24 bought Amount:55.94 Price:107.26\n"
                    + "Stock: MSFT Date:2018-07-31 bought Amount:56.94 Price:105.38\n"
                    + "Stock: MSFT Date:2018-08-07 bought Amount:55.47 Price:108.17\n"
                    + "Stock: MSFT Date:2018-08-14 bought Amount:55.53 Price:108.04\n"
                    + "Stock: MSFT Date:2018-08-21 bought Amount:56.68 Price:105.85\n"
                    + "Stock: MSFT Date:2018-08-28 bought Amount:54.65 Price:109.79\n"
                    + "Stock: MSFT Date:2018-09-04 bought Amount:54.44 Price:110.22\n"
                    + "Stock: MSFT Date:2018-09-11 bought Amount:55.10 Price:108.89\n"
                    + "Stock: MSFT Date:2018-09-18 bought Amount:53.71 Price:111.72\n"
                    + "Stock: MSFT Date:2018-09-25 bought Amount:52.75 Price:113.75\n",
            simulator1.getPortfolioLog("f1"));
  }

  @Test
  public void testSetCommissionFee() {
    String[] stockArray = {"GOOG", "TWTR", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    Map<String, Double> ratioMap = new HashMap<>();
    ratioMap.put("GOOG", 0.3);
    ratioMap.put("TWTR", 0.2);
    ratioMap.put("AMZN", 0.2);
    ratioMap.put("MSFT", 0.3);
    simulator1.createSpecifiedPortfolio(list, "f1");
    simulator1.investSpecifiedAmount(LocalDate.of(2018, 3, 1), 20000,
            ratioMap, "f1");
    assertEquals(19980.0,
            simulator1.getTotalCostBasis(LocalDate.of(2018, 4, 1), "f1"), 0.01);
    assertEquals("Stock: AMZN Cost:4000.00 Volume:2.73\n"
            + "Stock: GOOG Cost:6000.00 Volume:5.62\n"
            + "Stock: MSFT Cost:6000.00 Volume:65.33\n"
            + "Stock: TWTR Cost:4000.00 Volume:127.96\n", simulator1.getPortfolioState("f1"));
    assertEquals("Stock: AMZN Date:2018-03-01 bought Amount:2.73 Price:1465.00\n"
                    + "Stock: GOOG Date:2018-03-01 bought Amount:5.62 Price:1067.00\n"
                    + "Stock: MSFT Date:2018-03-01 bought Amount:65.33 Price:91.84\n"
                    + "Stock: TWTR Date:2018-03-01 bought Amount:127.96 Price:31.26\n",
            simulator1.getPortfolioLog("f1"));
    simulator1.setCommissionFee(25.00);
    simulator1.investSpecifiedAmount(LocalDate.of(2018, 5, 1), 20000,
            ratioMap, "f1");
    assertEquals(39800.0, simulator1.getTotalCostBasis(
            LocalDate.of(2018, 6, 1), "f1"), 0.01);
    assertEquals("Stock: AMZN Date:2018-03-01 bought Amount:2.73 Price:1465.00\n"
                    + "Stock: AMZN Date:2018-05-01 bought Amount:2.58 Price:1552.18\n"
                    + "Stock: GOOG Date:2018-03-01 bought Amount:5.62 Price:1067.00\n"
                    + "Stock: GOOG Date:2018-05-01 bought Amount:5.95 Price:1008.21\n"
                    + "Stock: MSFT Date:2018-03-01 bought Amount:65.33 Price:91.84\n"
                    + "Stock: MSFT Date:2018-05-01 bought Amount:64.66 Price:92.79\n"
                    + "Stock: TWTR Date:2018-03-01 bought Amount:127.96 Price:31.26\n"
                    + "Stock: TWTR Date:2018-05-01 bought Amount:135.14 Price:29.60\n",
            simulator1.getPortfolioLog("f1"));
  }

  @Test
  public void testPeriodicallyException() {
    String[] stockArray = {"GOOG", "AMZN", "MSFT"};
    List<String> list = new ArrayList<>();
    for (String str : stockArray) {
      list.add(str);
    }
    Map<String, Double> ratioMap = new HashMap<>();
    ratioMap.put("GOOG", 0.3);
    ratioMap.put("AMZN", 0.4);
    ratioMap.put("MSFT", 0.3);
    simulator1.createSpecifiedPortfolio(list, "f1");
    try {
      simulator1.investPeriodically("f1",
              20000.0, ratioMap, LocalDate.of(2018, 12, 15),
              LocalDate.of(2018, 10, 1), 7);
    } catch (IllegalArgumentException e) {
      assertEquals("Begin date can't be after today!", e.getMessage());
    }
    try {
      simulator1.investPeriodically("f1",
              20000.0, ratioMap, LocalDate.of(2018, 11, 15),
              LocalDate.of(2018, 12, 1), 7);
    } catch (IllegalArgumentException e) {
      assertEquals("End date can't be after today!", e.getMessage());
    }
    try {
      simulator1.investPeriodically("f1",
              20000.0, ratioMap, LocalDate.of(2018, 11, 15),
              LocalDate.of(2018, 10, 1), 7);
    } catch (IllegalArgumentException e) {
      assertEquals("Begin date can't be after the end date!", e.getMessage());
    }
    try {
      simulator1.investPeriodically("f2312",
              20000.0, ratioMap, LocalDate.of(2018, 11, 15),
              LocalDate.of(2018, 10, 1), 7);
    } catch (IllegalArgumentException e) {
      assertEquals("No such portfolio!", e.getMessage());
    }
  }

  @Test
  public void testInvalidCommissionFee() {
    try {
      simulator1.setCommissionFee(100000);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal commission fee!", e.getMessage());
    }
    try {
      simulator1.setCommissionFee(-100);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal commission fee!", e.getMessage());
    }
  }

  @Test
  public void testInvalidInvestFixed() {
    try {
      simulator1.investFixedAmount(LocalDate.MAX, 1000, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals("No such portfolio!", e.getMessage());
    }
    simulator1.createPortfolio("f1");
    try {
      simulator1.investFixedAmount(LocalDate.MAX, 1000, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals("Invest date can't be future date!", e.getMessage());
    }
    try {
      simulator1.investFixedAmount(
              LocalDate.of(2018, 10, 10), -100, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.ILLEGAL_MONEY.getMess(), e.getMessage());
    }
  }

  @Test
  public void testInvalidInvestSpecified() {
    Map<String, Double> ratioMap = new HashMap<>();
    try {
      simulator1.investSpecifiedAmount(
              LocalDate.of(2018, 10, 10), 1000, ratioMap, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals("No such portfolio!", e.getMessage());
    }
    simulator1.createPortfolio("f1");
    try {
      simulator1.investSpecifiedAmount(
              LocalDate.of(2019, 10, 10), 1000, ratioMap, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals("Invest date can't be future date!", e.getMessage());
    }
    try {
      simulator1.investSpecifiedAmount(
              LocalDate.of(2018, 10, 10), -1000, ratioMap, "f1");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.ILLEGAL_MONEY.getMess(), e.getMessage());
    }
  }

}