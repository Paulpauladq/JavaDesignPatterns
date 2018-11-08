import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import calculator.AbstractCalculator;
import calculator.SimpleCalculator;

/**
 * SimpleCalculatorTest is a test class for the class SimpleCalculator.
 * Its tests covers all possible circumstances of the rules.
 */
public class SimpleCalculatorTest {

  public AbstractCalculator simpleTest;

  /**
   * Initialize SimpleCalculator object.
   */
  @Before
  public void setUp() {
    simpleTest = new SimpleCalculator();
  }

  /**
   * Test initialization.
   */
  @Test
  public void testInitialVariable() {
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 0);
    assertEquals(simpleTest.currOperator, '#');
    assertEquals(simpleTest.resultString, "");
    assertEquals(simpleTest.getResult(), "");
  }

  /**
   * Test Clear operation.
   */
  @Test
  public void testClearInputCharacter() {
    simpleTest.input('C');
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 0);
    assertEquals(simpleTest.currOperator, '#');
    assertEquals(simpleTest.resultString, "");
  }

  /**
   * Test invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    simpleTest.input('&');
  }

  /**
   * Test valid Input: 2.
   */
  @Test
  public void testValidInputDigit() {
    simpleTest = (SimpleCalculator) simpleTest.input('2');
    assertEquals(simpleTest.lastOperand, 2);
    assertEquals(simpleTest.lastResult, 0);
    assertEquals(simpleTest.currOperator, '#');
    assertEquals(simpleTest.resultString, "2");
  }

  /**
   * Test invalid plus:+.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlus() {
    simpleTest = (SimpleCalculator) simpleTest.input('+');
  }

  /**
   * Test valid Input: 22.
   */
  @Test
  public void testInput22() {
    simpleTest = (SimpleCalculator) simpleTest.input('2');
    simpleTest = (SimpleCalculator) simpleTest.input('2');
    assertEquals(simpleTest.lastOperand, 22);
    assertEquals(simpleTest.lastResult, 0);
    assertEquals(simpleTest.currOperator, '#');
    assertEquals(simpleTest.resultString, "22");
  }

  /**
   * Test valid Input: 22+.
   */
  @Test
  public void testInput22Plus() {
    simpleTest = (SimpleCalculator) simpleTest.input('2');
    simpleTest = (SimpleCalculator) simpleTest.input('2');
    simpleTest = (SimpleCalculator) simpleTest.input('+');
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 22);
    assertEquals(simpleTest.currOperator, '+');
    assertEquals(simpleTest.resultString, "22+");
  }

  /**
   * Test valid Input: 22+33.
   */
  @Test
  public void testInput22Plus322() {
    String str = new String("22+322");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }

    assertEquals(simpleTest.lastOperand, 322);
    assertEquals(simpleTest.lastResult, 22);
    assertEquals(simpleTest.currOperator, '+');
    assertEquals(simpleTest.resultString, "22+322");
  }

  /**
   * Test valid Input: 22+22=+.
   */
  @Test
  public void testInput22Plus22EquPlus() {
    String str = new String("22+22=+");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 44);
    assertEquals(simpleTest.currOperator, '+');
    assertEquals(simpleTest.resultString, "44+");
  }

  /**
   * Test invalid Input: 22+=.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInput22PlusEqu() {
    String str = new String("22+=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
  }

  /**
   * Test valid Input: 22+33===.
   */
  @Test
  public void testInput22Plus33EquEquEqu() {
    String str = new String("22+33===");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 55);
    assertEquals(simpleTest.currOperator, '!');
    assertEquals(simpleTest.resultString, "55");
  }


  /**
   * Test invalid Input: 233=2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test233EquOperand() {
    String str = new String("233=2");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
  }

  /**
   * Test invalid Input: 22+-33.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeOperand() {
    String str = new String("22+-33");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
  }

  /**
   * Test operand overflow.
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflow() {
    String str = new String("2147483648");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
  }

  /**
   * Test multiple result overflow.
   */
  @Test
  public void testResultOverflow() {
    String str = new String("2147483647*31231=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, 0);
    assertEquals(simpleTest.currOperator, '!');
    assertEquals(simpleTest.resultString, "0");
  }

  /**
   * Test plus overflow.
   */
  @Test
  public void testInput2147483647Plus33Minus22Plus() {
    String str = new String("2147483647+33-22+");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
    assertEquals(simpleTest.lastOperand, 0);
    assertEquals(simpleTest.lastResult, -22);
    assertEquals(simpleTest.currOperator, '+');
    assertEquals(simpleTest.resultString, "-22+");

  }

  /**
   * Test valid input: 22+33=2 .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInput22Plus33Equ2() {
    String str = new String("22+33=2");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
  }

  /**
   * Test valid input: 22+33+ .
   */
  @Test
  public void testInput2Plus33Equ2() {
    String str = new String("22+33+");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }
    assertEquals(simpleTest.getResult(), "55+");
  }

  /**
   * Test valid input: 2+3=C2+3.
   */
  @Test
  public void testClearAfterInput() {
    String str = new String("2+3=C2+3");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
    }

    assertEquals(simpleTest.resultString, "2+3");
  }

  /**
   * Test valid input: 2-2=+2*2=.
   */
  @Test
  public void testConsectiveOutput() {
    String str = new String("2-2=+2*2=");
    char[] charArr = str.toCharArray();
    String[] str1 =
            new String[]{"2", "2-", "2-2", "0", "0+", "0+2", "2*", "2*2", "4", "4"};

    for (int i = 0; i < charArr.length; i++) {
      simpleTest = (SimpleCalculator) simpleTest.input(charArr[i]);
      assertEquals(simpleTest.resultString, str1[i]);
    }
  }
}