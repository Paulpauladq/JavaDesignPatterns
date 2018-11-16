import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import tradesimulator.model.tradingitem.Stock;

import static org.junit.Assert.assertEquals;

public class StockTest {

  private String record1;
  private String record2;

  @Before
  public void before() {
    record1 = "Stock: GOOG Date:2002-02-26 bought Amount:700 Price:40.0\n"
            + "Stock: GOOG Date:2005-02-25 bought Amount:600 Price:30.0\n"
            + "Stock: GOOG Date:2007-02-20 bought Amount:900 Price:72.0\n"
            + "Stock: GOOG Date:2007-02-26 bought Amount:800 Price:45.0\n"
            + "Stock: GOOG Date:2016-02-24 bought Amount:500 Price:103.3\n";
    record2 = "Stock: GOOG Cost:198450.0 Volume:3500\n";
  }

  @Test
  public void test1() {
    Stock google = new Stock("GOOG");
    google.addRecord(LocalDate.of(2016, 2, 24), 500, 103.3);
    google.addRecord(LocalDate.of(2005, 2, 25), 600, 30);
    google.addRecord(LocalDate.of(2002, 2, 26), 700, 40);
    google.addRecord(LocalDate.of(2007, 2, 26), 800, 45);
    google.addRecord(LocalDate.of(2007, 2, 20), 900, 72);
    assertEquals(record1, google.recordLog());
    //System.out.println(google.recordLog());
    assertEquals(record1, google.recordLog());
    assertEquals("GOOG", google.getSymbol());
    assertEquals(198450, google.getCost(), 4);
    assertEquals(46000, google.getCostByDate(LocalDate.of(2006, 2, 20)), 4);
    assertEquals(3500, google.getVolume());
    assertEquals(record2, google.toString());
  }

}