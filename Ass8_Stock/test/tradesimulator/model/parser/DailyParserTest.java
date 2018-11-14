//package tradesimulator.model.parser;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Calendar;
//
//import java.util.GregorianCalendar;
//
//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.fail;
//
//public class DailyParserTest {
//
//  @Test
//  public void test1() {
//    DailyParser parser = new DailyParser();
//    try {
//      LocalDate date1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 8).getTime();
//      Double priceFB1 = parser.getPrice("FB", date1);
//      assertEquals(147.8700,priceFB1,4);
//    } catch (Exception e) {
//      throw new IllegalArgumentException("Test for Facebook failed!");
//    }
//
//    try {
//      LocalDate date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 2).getTime();
//      Double priceGOOGLE = parser.getPrice("GooG", date2);
//      assertEquals(528.48,priceGOOGLE,4);
//    } catch (Exception e) {
//      throw new IllegalArgumentException("Test for Google failed!");
//    }
//
//    try {
//      LocalDate date3 = new GregorianCalendar(2050, Calendar.FEBRUARY, 2).getTime();
//      Double priceGOOGLE = parser.getPrice("GooG", date3);
//      fail("Test for Illegal LocalDate failed!");
//    } catch (IllegalArgumentException e) {
//      assertEquals("Illegal input for date!",e.getMessage());
//    }
//
//    try {
//      LocalDate date4 = new GregorianCalendar(2015, Calendar.FEBRUARY, 2).getTime();
//      Double price = parser.getPrice("XXXXXXX", date4);
//      fail("Test for Illegal stock failed!");
//    } catch (IllegalArgumentException e) {
//      assertEquals("Illegal input for stock!",e.getMessage());
//    }
//
//  }
//
//
//}