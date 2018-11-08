import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is meant to test the all the possible cases for the PolynomialImpl
 * class. It covers every circumstances for every method.
 */
public class PolynomialImplTest {

  private Polynomial polynomialTest;

  /**
   * Set up some variables.
   */
  @Before
  public void setUp() {
    polynomialTest = new PolynomialImpl();
  }

  /**
   * Test the no-parameter constructor.
   */
  @Test
  public void PolynomialConstructor() {
    assertEquals(polynomialTest.toString(), "0");
  }

  /**
   * Test the invalid string constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void PolynomialStringInvalidConstructor() {
    Polynomial polyTest = new PolynomialImpl("dasdsadsadsa");
  }

  /**
   * Test the null string constructor.
   */
  @Test(expected = NullPointerException.class)
  public void PolynomialStringInvalidConstructorNull() {
    Polynomial polyTest = new PolynomialImpl(null);
  }

  /**
   * Test the invalid string constructor.
   */
  @Test
  public void PolynomialStringInvalidConstructorZero() {
    Polynomial polyTest = new PolynomialImpl("0");
    assertEquals(polyTest.toString(), "0");
  }

  /**
   * Test the valid string constructor.
   */
  @Test
  public void PolynomialStringValidConstructor1() {
    Polynomial polyTest = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals(polyTest.toString(), "4x^3+3x^1-5");
  }

  /**
   * Test the valid string constructor.
   */
  @Test
  public void PolynomialStringValidConstructor2() {
    Polynomial polyTest = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals(polyTest.toString(), "-2x^5-3x^4+11x^1-5");
  }

  /**
   * Test the valid string constructor.
   */
  @Test
  public void PolynomialStringValidConstructor3() {
    Polynomial polyTest = new PolynomialImpl("102");
    assertEquals(polyTest.toString(), "102");
  }

  /**
   * Test the valid string constructor.
   */
  @Test
  public void PolynomialStringValidConstructor4() {
    Polynomial polyTest = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(polyTest.toString(), "-2x^5+1x^4+11x^1-5");
  }

  /**
   * Test the addTerm() method.
   */
  @Test
  public void PolynomialAddTerm() {
    Polynomial obj = new PolynomialImpl();
    obj.addTerm(-2, 3);
    assertEquals(obj.toString(), "-2x^3");
    obj.addTerm(4, 2);
    assertEquals(obj.toString(), "-2x^3+4x^2");
    obj.addTerm(5, 2);
    assertEquals(obj.toString(), "-2x^3+9x^2");
    obj.addTerm(-5, 2);
    assertEquals(obj.toString(), "-2x^3+4x^2");
    obj.addTerm(-5, 1);
    assertEquals(obj.toString(), "-2x^3+4x^2-5x^1");
    obj.addTerm(0, 0);
    assertEquals(obj.toString(), "-2x^3+4x^2-5x^1");
    obj.addTerm(2, 6);
    assertEquals(obj.toString(), "2x^6-2x^3+4x^2-5x^1");
  }

  /**
   * Test the evaluate() method with negative value.
   */
  @Test
  public void PolynomialStringEvaluateNegative() {
    Polynomial polyTest = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(polyTest.evaluate(-2.0), 53.0, 0.01);
  }

  /**
   * Test the evaluate() method with positive value.
   */
  @Test
  public void PolynomialStringEvaluateRegular() {
    Polynomial polyTest = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(polyTest.evaluate(2.0), -31.0, 0.01);

  }

  /**
   * Test the evaluate() method with 0 value.
   */
  @Test
  public void PolynomialStringEvaluateZero() {
    Polynomial polyTest = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(polyTest.evaluate(0.0), -5.0, 0.01);
  }

  /**
   * Test the evaluate() method with 0 value.
   */
  @Test
  public void PolynomialStringEvaluateEmpty() {
    Polynomial polyTest = new PolynomialImpl();
    assertEquals(polyTest.evaluate(0.0), 0.0, 0.01);
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testPolynomialToString() {
    Polynomial obj = new PolynomialImpl();
    obj.addTerm(-2, 3);
    assertEquals(obj.toString(), "-2x^3");
    obj.addTerm(4, 2);
    assertEquals(obj.toString(), "-2x^3+4x^2");
    obj.addTerm(5, 2);
    assertEquals(obj.toString(), "-2x^3+9x^2");
    obj.addTerm(-5, 2);
    assertEquals(obj.toString(), "-2x^3+4x^2");
    obj.addTerm(-5, 1);
    assertEquals(obj.toString(), "-2x^3+4x^2-5x^1");
    obj.addTerm(0, 0);
    assertEquals(obj.toString(), "-2x^3+4x^2-5x^1");
    obj.addTerm(2, 6);
    assertEquals(obj.toString(), "2x^6-2x^3+4x^2-5x^1");
    Polynomial obj2 = new PolynomialImpl("4");
    assertEquals(obj2.toString(), "4");
    Polynomial obj3 = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(obj3.toString(), "-2x^5+1x^4+11x^1-5");
  }

  /**
   * Test the equals() method.
   */
  @Test
  public void testPolynomialEquals() {
    Polynomial obj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    Polynomial obj2 = new PolynomialImpl("+3x^4 -2x^5 -2x^4 -5 +11x^1");
    Polynomial obj3 = new PolynomialImpl("+1x^4 -2x^5 -5 +11x^1");

    assertEquals(obj2.equals(obj2), true);
    assertEquals(obj.equals(obj2), true);
    assertEquals(obj2.equals(obj3), true);
    assertEquals(obj.equals(obj3), true);
    assertEquals(obj2.equals(obj), true);
  }

  /**
   * Test the getDegree() method.
   */
  @Test
  public void testGetDegree() {
    Polynomial obj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    Polynomial obj2 = new PolynomialImpl("0");
    Polynomial obj3 = new PolynomialImpl();

    assertEquals(obj.getDegree(), 5);
    assertEquals(obj2.getDegree(), 0);
    assertEquals(obj3.getDegree(), 0);
  }

  /**
   * Test the getCoefficient() method.
   */
  @Test
  public void testGetCoefficient() {
    Polynomial obj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    Polynomial obj2 = new PolynomialImpl("0");
    Polynomial obj3 = new PolynomialImpl();

    assertEquals(obj.getCoefficient(5), -2);
    assertEquals(obj.getCoefficient(4), 1);
    assertEquals(obj.getCoefficient(1), 11);
    assertEquals(obj.getCoefficient(0), -5);
    assertEquals(obj.getCoefficient(44), 0);

    assertEquals(obj2.getCoefficient(0), 0);
    assertEquals(obj3.getCoefficient(44), 0);
    assertEquals(obj3.getCoefficient(0), 0);
    assertEquals(obj3.getCoefficient(44), 0);
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testAddTwoPolynomial() {
    Polynomial obj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    Polynomial obj2 = new PolynomialImpl("+3x^44 -23x^5 -2x^3 +31x^1");
    Polynomial obj1Rev = new PolynomialImpl("-3x^4 -2x^5 +5 +2x^4 -11x^1");
    Polynomial obj3 = new PolynomialImpl("0");
    Polynomial obj4 = new PolynomialImpl("0");
    Polynomial objEmpty = new PolynomialImpl();
    Polynomial res = new PolynomialImpl();

    res = obj.add(obj2);
    assertEquals(res.toString(), "3x^44-25x^5+1x^4-2x^3+42x^1-5");
    //immutablilty test!!!!!!!!!!!!!!!!!!!!!!!!
    assertEquals(obj.toString(), "-2x^5+1x^4+11x^1-5");
    assertEquals(obj2.toString(), "3x^44-23x^5-2x^3+31x^1");
    res = obj.add(obj1Rev);
    assertEquals(res.toString(), "-4x^5");
    res = obj3.add(obj4);
    assertEquals(res.toString(), "0");
    res = obj.add(objEmpty);
    assertEquals(res.toString(), "-2x^5+1x^4+11x^1-5");
    res = objEmpty.add(obj);
    assertEquals(res.toString(), "-2x^5+1x^4+11x^1-5");
    res = objEmpty.add(objEmpty);
    assertEquals(res.toString(), "0");
  }

  /**
   * Test the derivative() method.
   */
  @Test
  public void testPolynomialDerivative() {
    Polynomial obj = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    Polynomial obj3 = new PolynomialImpl("44");
    Polynomial objEmpty = new PolynomialImpl();

    Polynomial res = new PolynomialImpl();
    res = obj.derivative();
    assertEquals(res.toString(), "-10x^4+4x^3+11");
    res = obj3.derivative();
    assertEquals(res.toString(), "0");
    res = objEmpty.derivative();
    assertEquals(res.toString(), "0");
  }
}