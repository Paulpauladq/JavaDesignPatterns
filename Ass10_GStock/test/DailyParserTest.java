
import org.junit.Test;

import java.time.LocalDate;

import tradesimulator.model.parser.DailyParser;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class DailyParserTest {

  /**
   * tests successful searching based on API.
   */
  @Test
  public void test1() {
    DailyParser parser = new DailyParser();
    try {
      LocalDate date1 = LocalDate.of(2018, 11, 8);
      //new GregorianCalendar(2018, Calendar.NOVEMBER, 8).getTime();
      Double priceFB1 = parser.getPrice("FB", date1);
      assertEquals(147.8700, priceFB1, 4);
    } catch (Exception e) {
      throw new IllegalArgumentException("Test for Facebook failed!");
    }

    try {
      LocalDate date2 = LocalDate.of(2015, 2, 6);
      //LocalDate date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 2).getTime();
      Double priceGOOGLE = parser.getPrice("GooG", date2);
      assertEquals(526.41, priceGOOGLE, 4);
    } catch (Exception e) {
      throw new IllegalArgumentException("Test for Google failed!");
    }
  }


  /**
   * tests Exception cases.
   */
  @Test
  public void testException() {
    DailyParser parser = new DailyParser();

    /**
     * stockSymbol = "".
     */
    try {
      LocalDate date2 = LocalDate.of(2015, 2, 2);
      Double priceGOOGLE = parser.getPrice("", date2);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input!", e.getMessage());
    }


    /**
     * stockSymbol is illegal.
     */
    try {
      LocalDate date4 = LocalDate.of(2015, 2, 2);
      //LocalDate date4 = new GregorianCalendar(2015, Calendar.FEBRUARY, 2).getTime();
      Double price = parser.getPrice("XXXXXXX", date4);
      fail("Test for Illegal stock failed!");
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input for stockSymbol!", e.getMessage());
    }

    /**
     * stockSymbol is null.
     */
    try {
      LocalDate date5 = LocalDate.of(2015, 2, 2);
      Double priceGOOGLE = parser.getPrice(null, date5);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input!", e.getMessage());
    }

    //"Illegal input for time!"
    try {
      LocalDate date6 = LocalDate.of(2055, 2, 2);
      Double priceGOOGLE = parser.getPrice("GOOG", date6);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input for time!", e.getMessage());
    }

  }

  /**
   * tests Exception cases.
   */
  @Test
  public void testExceptionForDate() {
    DailyParser parser = new DailyParser();
    /**
     * date is null.
     */
    try {
      LocalDate date = null;
      Double priceGOOGLE = parser.getPrice("GooG", date);
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input!", e.getMessage());
    }

    /**
     * date is illegal.
     */
    try {
      LocalDate date3 = LocalDate.of(2050, 2, 2);
      //LocalDate date3 = new GregorianCalendar(2050, Calendar.FEBRUARY, 2).getTime();
      Double priceGOOGLE = parser.getPrice("GOOG", date3);
      fail("Test for Illegal LocalDate failed!");
    } catch (IllegalArgumentException e) {
      assertEquals("Illegal input for time!", e.getMessage());
    }
  }
}