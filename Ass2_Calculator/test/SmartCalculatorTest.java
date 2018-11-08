import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import calculator.AbstractCalculator;
import calculator.SmartCalculator;

/**
 * SmartCalculatorTest is a test class for the class SmartCalculator.
 * Its tests covers all possible circumstances of the rules.
 */
public class SmartCalculatorTest {

  public AbstractCalculator smartTest;

  /**
   * Initialize SimpleCalculator object.
   */
  @Before
  public void setUp() {
    smartTest = new SmartCalculator();
  }

  /**
   * Test initialization variables.
   */
  @Test
  public void testInitialVariable() {
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '#');
    assertEquals(smartTest.resultString, "");
    assertEquals(smartTest.getResult(), "");
  }

  /**
   * Test clear input character.
   */
  @Test
  public void testClearInputCharacter() {
    smartTest.input('C');
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '#');
    assertEquals(smartTest.resultString, "");
  }

  /**
   * Test invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    smartTest.input('&');
  }

  /**
   * Test valid input: +++.
   */
  @Test
  public void testInputPlusPlusPlus() {
    String str = new String("+++");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '#');
    assertEquals(smartTest.resultString, "");
  }

  /**
   * Test valid input: +.
   */
  @Test
  public void testInitialIgnoredcurrOperator() {
    smartTest = (SmartCalculator) smartTest.input('+');
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '#');
    assertEquals(smartTest.getResult(), "");
  }

  /**
   * Test valid input: 22+-**.
   */
  @Test
  public void testInput22PlusMinusMulMul() {
    String str = new String("22+-**");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 22);
    assertEquals(smartTest.currOperator, '*');
    assertEquals(smartTest.getResult(), "22*");
  }

  /**
   * Test valid input: 22+22=+.
   */
  @Test
  public void testInput22Plus22EquPlus() {
    String str = new String("22+22=+");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 44);
    assertEquals(smartTest.currOperator, '+');
    assertEquals(smartTest.resultString, "44+");
  }

  /**
   * Test valid input: 22+22+.
   */
  @Test
  public void testInput22Plus22Plus() {
    String str = new String("22+22+");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 44);
    assertEquals(smartTest.currOperator, '+');
    assertEquals(smartTest.resultString, "44+");
  }

  /**
   * Test valid input: 22-32*.
   */
  @Test
  public void testInput22Minus32Mul() {
    String str = new String("22-32*");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, -10);
    assertEquals(smartTest.currOperator, '*');
    assertEquals(smartTest.resultString, "-10*");
  }

  /**
   * Test plus overflow.
   */
  @Test
  public void testPlusOverflow() {
    String str = new String("10+2147483647+10=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 10);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "10");
  }

  /**
   * Test minus overflow.
   */
  @Test
  public void testMinusOverflow() {
    String str = new String("0-2147483647-10=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "0");
  }

  /**
   * Test multiplication overflow.
   */
  @Test
  public void testMulOverflow() {
    String str = new String("22333333*322222222+1");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 1);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '+');
    assertEquals(smartTest.resultString, "0+1");
  }

  /**
   * Test illegal input: 233=.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test233Equ() {
    String str = new String("233=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
  }

  /**
   * Test illegal input: 233=2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test233EquOperand() {
    String str = new String("233=2");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
  }

  /**
   * Test operand overflow.
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflow() {
    String str = new String("2332222222222222");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
  }

  /**
   * Test valid input: 233222.
   */
  @Test
  public void testValidMultiDigitOperand() {
    String str = new String("233222");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 233222);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '#');
    assertEquals(smartTest.resultString, "233222");
  }

  /**
   * Test valid input: 323+=.
   */
  @Test
  public void testValidMultipleOpeartorPlusEqu() {
    String str = new String("323+=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 646);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "646");
  }

  /**
   * Test valid input: 323-=.
   */
  @Test
  public void testValidMultipleOpeartorMinusEqu() {
    String str = new String("323-=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "0");
  }

  /**
   * Test valid input: 3*=.
   */
  @Test
  public void testValidMultipleOpeartorMulEqu() {
    String str = new String("3*=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 9);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "9");
  }

  /**
   * Test valid input: +32-24=.
   */
  @Test
  public void testInput22Plus33Equ222() {
    String str = new String("+32-24=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 8);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "8");
  }

  /**
   * Test valid input: 3+===.
   */
  @Test
  public void testSingleOperandConsectiveEquals() {
    String str = new String("3+===");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }

    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 12);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "12");
  }

  /**
   * Test valid input: 3+5===.
   */
  @Test
  public void testTwoOpearndConsectiveEquals() {
    String str = new String("3+5===");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }

    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 18);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "18");
  }

  /**
   * Test plus overflow.
   */
  @Test
  public void testSingleOpearndMAXOverflowEqu() {
    String str = new String("2147483647+=");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }

    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 0);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "0");
  }

  /**
   * Test plus overflow with a double equation.
   */
  @Test
  public void testSingleOpearndMAXOverflowEquEqu() {
    String str = new String("2147483647+==");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }

    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, 2147483647);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "2147483647");
  }

  /**
   * Test consecutive currOperators.
   */
  @Test
  public void testConsecutiveEquPlusConsecutiveEqu() {
    String str = new String("4-====+==");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, -36);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "-36");
  }

  /**
   * Test consecutive currOperators.
   */
  @Test
  public void testTwoOperandConsecutiveEquPlusConsecutiveEqu() {
    String str = new String("4*5====-==");
    char[] charArr = str.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
    }
    assertEquals(smartTest.lastOperand, 0);
    assertEquals(smartTest.lastResult, -2500);
    assertEquals(smartTest.currOperator, '!');
    assertEquals(smartTest.resultString, "-2500");
  }

  /**
   * Test consecutive output.
   */
  @Test
  public void testConsecutiveOutput() {
    String str = new String("2-2=+2*2=");
    char[] charArr = str.toCharArray();
    String[] str1 = new String[]{"2", "2-", "2-2", "0", "0+", "0+2", "2*", "2*2", "4", "4"};

    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
      assertEquals(smartTest.resultString, str1[i]);
    }
  }

  /**
   * test consecutive output.
   */
  @Test
  public void testConsectiveOutputSmart() {
    String str = new String("4*5===C3+=-==");
    char[] charArr = str.toCharArray();
    String[] strArr =
            new String[]{"4", "4*", "4*5", "20", "100", "500", "", "3", "3+", "6", "6-", "0", "-6"};
    for (int i = 0; i < charArr.length; i++) {
      smartTest = (SmartCalculator) smartTest.input(charArr[i]);
      assertEquals(smartTest.resultString, strArr[i]);
    }
  }
}