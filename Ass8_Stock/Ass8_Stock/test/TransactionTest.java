import org.junit.Test;

import java.time.LocalDate;

import tradesimulator.model.transaction.ITransaction;
import tradesimulator.model.transaction.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

  @Test
  public void test1() {
    //LocalDate date, int amount, double price, boolean isBuy;

    ITransaction t1 = new Transaction(LocalDate.of(2016, 2, 2), 200, 3, true);
    /**
     * test for getDate().
     */
    assertEquals(0, LocalDate.of(2016, 2, 2).compareTo(t1.getDate()));
    /**
     * test for getAmount().
     */
    assertEquals(200, t1.getAmount());
    /**
     * test for getPrice().
     */
    assertEquals(3, t1.getPrice(), 4);
    /**
     * test for isBuy().
     */
    assertTrue(t1.isBuy());

    ITransaction t2 = new Transaction(LocalDate.of(2050, 2, 2), 50000, 30000, false);
    assertEquals(0, LocalDate.of(2050, 2, 2).compareTo(t2.getDate()));
    assertEquals(50000, t2.getAmount());
    assertEquals(30000, t2.getPrice(), 4);
    assertFalse(t2.isBuy());
  }

}