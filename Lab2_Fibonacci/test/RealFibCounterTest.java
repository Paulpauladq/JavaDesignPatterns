import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs5010.lab2.fib.FibonacciCounter;
import cs5010.lab2.fib.RealFibCounter;

/**
 * A Junit test class for the RealFibCOunter class.
 */
public class RealFibCounterTest {

  private FibonacciCounter counterTest;

  /**
   * Set up the new RealFibCounter for testing.
   */
  @Before
  public void setUp() {
    counterTest = new RealFibCounter();
  }

  /**
   * test the initial value of the count, which is 1.
   */
  @Test
  public void testInitialCount() {
    assertEquals(counterTest.currentCount(), 1);
  }

  /**
   * Test whether the incrementCounter() function is working properly.
   */
  @Test
  public void testIncrementCount() {
    counterTest.incrementCounter();
    assertEquals(counterTest.currentCount(), 2);
  }

  /**
   * Test whether the decrementCounter() function is working properly.
   */
  @Test
  public void testDecrementCount() {
    counterTest.incrementCounter();
    counterTest.decrementCounter();
    assertEquals(counterTest.currentCount(), 1);
  }

  /**
   * Test whether the exception will be thrown properly when the counter is
   * less than 1(i.e. underflow).
   */
  @Test(expected = ArithmeticException.class)
  public void testIllegalDecrement() {
    counterTest.decrementCounter();
  }

  /**
   * Test the critical point just before overflow happens.
   */
  @Test
  public void testOneIncrementBeforeOverflow() {
    for (int i = 0; i < 46; i++) {
      counterTest.incrementCounter();
    }
    assertEquals(counterTest.calculateFibonacci(), 1836311903);
  }

  /**
   * Test whethter the ArithmeticException happens when overflow happens.
   */
  @Test(expected = ArithmeticException.class)
  public void testOverflowIncrement() {
    for (int i = 0; i < 47; i++) {
      counterTest.incrementCounter();
    }
    counterTest.calculateFibonacci();
  }

  /**
   * Test whether the calculateFibonacci() function is calculating right.
   */
  @Test
  public void testCalculate() {
    for (int i = 0; i < 5; i++) {
      counterTest.incrementCounter();
    }
    assertEquals(counterTest.calculateFibonacci(), 5);
  }

}