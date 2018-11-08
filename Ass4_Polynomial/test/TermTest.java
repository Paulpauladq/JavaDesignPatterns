import org.junit.Before;
import org.junit.Test;

import polynomial.Term;

import static org.junit.Assert.assertEquals;

/**
 * The class is meant to test the term class thoroughly.
 * It covers every possible cases and methods for Term class.
 */
public class TermTest {

  private Term termTest1;
  private Term termTest2;
  private Term termTest3;
  private Term termTest4;
  private Term termTest5;
  private Term termTest6;

  /**
   * set up.
   */
  @Before
  public void setUp() {
    int coe1 = 5;
    int power1 = 3;
    int coe2 = 4;
    int power2 = 3;
    int coe3 = 2;
    int power3 = 7;
    int coe4 = 5;
    int power4 = 3;
    int coe5 = 0;
    int power5 = 10;
    int coe6 = 10;
    int power6 = 0;
    termTest1 = new Term(coe1, power1);
    termTest2 = new Term(coe2, power2);
    termTest3 = new Term(coe3, power3);
    termTest4 = new Term(coe4, power4);
    termTest5 = new Term(coe5, power5);
    termTest6 = new Term(coe6, power6);
  }

  /**
   * Test when the initialization is invalid.(poewr < 0)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTermInitialization() {
    Term term = new Term(2, -3);
  }

  /**
   * Test getCoefficient() method.
   */
  @Test
  public void testTermGetCoefficient() {
    assertEquals(termTest1.getCoefficient(), 5);
  }

  /**
   * Test getPower() method.
   */
  @Test
  public void testTermGetPower() {
    assertEquals(termTest2.getPower(), 3);
  }

  /**
   * Test hasSmallerPowerThan() method when the return is false.
   */
  @Test
  public void testHasSmallerPowerThanFalse() {
    assertEquals(termTest1.hasSmallerPowerThan(termTest2), false);
  }

  /**
   * Test hasSmallerPowerThan() method when the return is true.
   */
  @Test
  public void testHasSmallerPowerThanTrue() {
    assertEquals(termTest2.hasSmallerPowerThan(termTest3), true);
  }

  /**
   * Test testHasTheSamePowerToFalse() method when the return is false.
   */
  @Test
  public void testHasTheSamePowerToFalse() {
    assertEquals(termTest3.hasTheSamePowerTo(termTest1), false);
  }

  /**
   * Test testHasTheSamePowerToFalse() method when the return is true.
   */
  @Test
  public void testHasTheSamePowerToTrue() {
    assertEquals(termTest1.hasTheSamePowerTo(termTest2), true);
  }

  /**
   * Test equals() method when the return is true.
   */
  @Test
  public void testEquals1() {
    assertEquals(termTest1.equals(termTest1), true);
  }

  /**
   * Test equals() method when the return is false.
   */
  @Test
  public void testEquals2() {
    String str = " ";
    assertEquals(termTest1.equals(str), false);
  }

  /**
   * Test equals() method when the return is true.
   */
  @Test
  public void testEquals3() {
    assertEquals(termTest1.equals(termTest4), true);
  }

  /**
   * Test toString() method.
   */
  @Test
  public void testToStringRegular() {
    assertEquals(termTest1.toString(), "5x^3");
  }

  /**
   * Test toString() method.
   */
  @Test
  public void testToStringZeroCoe() {
    assertEquals(termTest5.toString(), "");
  }

  /**
   * Test toString() method.
   */
  @Test
  public void testToStringZeroPower() {
    assertEquals(termTest6.toString(), "10");
  }

  /**
   * Test toString() method.
   */
  @Test
  public void testCalculateValue() {
    assertEquals(termTest1.calculateValue(3.0), 135.0, 0.001);
  }

  /**
   * Test merge() method when the merge is valid.
   */
  @Test
  public void testValidMerge() {
    Term term = termTest1.merge(termTest2);
    assertEquals(term.getPower(), 3);
    assertEquals(term.getCoefficient(), 9);
  }

  /**
   * Test merge() method when the merge is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMerge() {
    Term term = termTest1.merge(termTest3);
  }

  /**
   * Test calculateDerivative() method.
   */
  @Test
  public void testCalDerivativeRegular() {
    Term term = termTest1.calculateDerivative();
    assertEquals(term.getCoefficient(), 15);
    assertEquals(term.getPower(), 2);
  }

  /**
   * Test calculateDerivative() method.
   */
  @Test
  public void testCalDerivativeRegularConstant() {
    Term term = termTest6.calculateDerivative();
    assertEquals(term.getCoefficient(), 0);
    assertEquals(term.getPower(), 0);
  }
}