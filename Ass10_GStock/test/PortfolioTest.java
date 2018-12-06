import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import tradesimulator.model.portfolio.Portfolio;
import tradesimulator.model.tradingitem.Stock;

import static junit.framework.TestCase.assertEquals;

public class PortfolioTest {

  private String str1;
  private String str2;
  private String str3;
  private Portfolio p1;
  private Stock fb;

  /**
   * initialize the String for test.
   */
  @Before
  public void before() {
    str1 = "Stock: GOOG Date:2002-02-26 bought Amount:700 Price:40.0\n"
            + "Stock: GOOG Date:2005-02-25 bought Amount:600 Price:30.0\n"
            + "Stock: GOOG Date:2007-02-26 bought Amount:800 Price:45.0\n"
            + "Stock: GOOG Date:2016-02-24 bought Amount:500 Price:103.3\n";
    str2 = "Stock: FB Date:2002-02-26 bought Amount:700 Price:40.0\n"
            + "Stock: FB Date:2005-02-25 bought Amount:600 Price:30.0\n"
            + "Stock: FB Date:2007-02-20 bought Amount:900 Price:72.0\n"
            + "Stock: FB Date:2007-02-26 bought Amount:800 Price:45.0\n"
            + "Stock: FB Date:2016-02-24 bought Amount:500 Price:103.3\n"
            + "Stock: GOOG Date:2002-02-26 bought Amount:700 Price:40.0\n"
            + "Stock: GOOG Date:2005-02-25 bought Amount:600 Price:30.0\n"
            + "Stock: GOOG Date:2007-02-20 bought Amount:900 Price:72.0\n"
            + "Stock: GOOG Date:2007-02-26 bought Amount:800 Price:45.0\n"
            + "Stock: GOOG Date:2016-02-24 bought Amount:500 Price:103.3\n";
    str3 = "Stock: FB Cost:198450.0 Volume:3500\n" +
            "Stock: GOOG Cost:198450.0 Volume:3500\n";
    p1 = new Portfolio("Portfolio1");
    Stock google = new Stock("GOOG");
  }

  /**
   * test for methods in Portfolio.
   */
  @Test
  public void test1() {
    //Portfolio p1 = new Portfolio("Portfolio1");
    p1.addStockRecord("GOOG", LocalDate.of(2016, 2, 24), 500, 103.3);
    p1.addStockRecord("GOOG", LocalDate.of(2005, 2, 25), 600, 30);
    p1.addStockRecord("GOOG", LocalDate.of(2002, 2, 26), 700, 40);
    p1.addStockRecord("GOOG", LocalDate.of(2007, 2, 26), 800, 45);
    /**
     * test for recordLog().
     */
    assertEquals(str1, p1.recordLog());
    //System.out.println(p1.recordLog());
    Stock fb = new Stock("FB");
    fb.addRecord(LocalDate.of(2016, 2, 24), 500, 103.3);
    fb.addRecord(LocalDate.of(2005, 2, 25), 600, 30);
    fb.addRecord(LocalDate.of(2002, 2, 26), 700, 40);
    fb.addRecord(LocalDate.of(2007, 2, 26), 800, 45);
    fb.addRecord(LocalDate.of(2007, 2, 20), 900, 72);
    p1.addStock(fb);
    /**
     * test for addStockRecord().
     */
    p1.addStockRecord("GOOG", LocalDate.of(2007, 2, 20), 900, 72);
    assertEquals(str2, p1.recordLog());
    /**
     * test for getPortfolioName().
     */
    assertEquals("Portfolio1", p1.getPortfolioName());
    /**
     * test for getTotalCostByDate().
     */

    /**
     * test for getStockMap().
     */
    assertEquals(2, p1.getStockMap().size());
    /**
     * test for toString().
     */
    assertEquals(str3, p1.toString());
  }


}