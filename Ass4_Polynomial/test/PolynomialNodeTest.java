import polynomial.PolynomialElementNode;
import polynomial.PolynomialNode;
import polynomial.PolynomialEmptyNode;
import polynomial.Term;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The class is meant to test the PolynomialNode class thoroughly.
 * It covers every possible cases and methods for PolynomialNode class.
 */
public class PolynomialNodeTest {

  private PolynomialNode empty;
  private PolynomialNode element1;
  private PolynomialNode elementTest;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    Term term1 = new Term(5, 5);
    Term term2 = new Term(-4, 3);
    Term term3 = new Term(5, 4);
    Term term4 = new Term(-5, 4);
    Term term5 = new Term(10, 1);
    Term term6 = new Term(0, 0);
    empty = new PolynomialEmptyNode();
    element1 = new PolynomialElementNode(term1, empty);
    elementTest = new PolynomialElementNode(term2, element1);
    elementTest = new PolynomialElementNode(term3, elementTest);
    PolynomialNode empty2 = new PolynomialEmptyNode();
  }

  /**
   * Test the constructor for the element constructor.
   */
  @Test
  public void testElementNodeConstructor() {
    assertEquals(element1.getCoefficient(5), 5);
  }

  /**
   * Test the getCoefficient() method for the empty constructor.
   */
  @Test
  public void testEmptyGetCoefficient() {
    assertEquals(empty.getCoefficient(2), 0);
  }

  /**
   * Test the getDegree() method for the empty constructor.
   */
  @Test
  public void testEmptyGetDegree() {
    assertEquals(empty.getDegree(), 0);
  }

  /**
   * Test the evaluate() method for the empty constructor.
   */
  @Test
  public void testEmptyEvaluate() {
    assertEquals(empty.evaluate(0.0), 0.0, 0.001);
  }

  /**
   * Test the derivative() method for the empty constructor.
   */
  @Test
  public void testEmptyDerivative() {
    assertEquals(empty.derivative(), null);
  }

  /**
   * Test the toString() method for the empty constructor.
   */
  @Test
  public void testEmptyToString() {
    assertEquals(empty.toString(), "0");
  }

  /**
   * Test the addTerm() method for the element constructor.
   */
  @Test
  public void testElementNodeAddTerm() {
    elementTest = empty.addTerm(new Term(5, 5));
    assertEquals(elementTest.toString(), "5x^5");
    element1 = element1.addTerm(new Term(4, 4));
    assertEquals(element1.toString(), "5x^5+4x^4");
    element1 = element1.addTerm(new Term(-4, 3));
    assertEquals(element1.toString(), "5x^5+4x^4-4x^3");
    element1 = element1.addTerm(new Term(3, 6));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3");
    element1 = element1.addTerm(new Term(9, 0));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3+9");
    element1 = element1.addTerm(new Term(0, 0));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3+9");
    element1 = element1.addTerm(new Term(-4, 4));
    assertEquals(element1.toString(), "3x^6+5x^5-4x^3+9");
  }

  /**
   * Test the getDegree() method for the element constructor.
   */
  @Test
  public void testElementNodeGetDegree() {
    elementTest = empty.addTerm(new Term(5, 5));
    element1 = element1.addTerm(new Term(4, 4));
    element1 = element1.addTerm(new Term(-4, 3));
    assertEquals(element1.toString(), "5x^5+4x^4-4x^3");
    assertEquals(element1.getDegree(), 5);
  }

  /**
   * Test the getCoefficient() method for the element constructor.
   */
  @Test
  public void testElementNodeGetCoefficient() {
    elementTest = empty.addTerm(new Term(5, 5));
    element1 = element1.addTerm(new Term(4, 4));
    element1 = element1.addTerm(new Term(-4, 3));

    assertEquals(element1.getCoefficient(5), 5);
    assertEquals(element1.getCoefficient(4), 4);
    assertEquals(element1.getCoefficient(3), -4);
    assertEquals(element1.getCoefficient(2), 0);
  }

  /**
   * Test the evaluate() method for the element constructor.
   */
  @Test
  public void testElementNodeEvaluate() {
    elementTest = empty.addTerm(new Term(5, 5));
    element1 = element1.addTerm(new Term(4, 4));
    element1 = element1.addTerm(new Term(-4, 3));

    assertEquals(element1.evaluate(5.0), 17625.0, 0.01);
  }

  /**
   * Test the derivative() method for the element constructor.
   */
  @Test
  public void testElementNodeDerivative() {
    elementTest = empty.addTerm(new Term(5, 5));
    element1 = element1.addTerm(new Term(4, 4));
    element1 = element1.addTerm(new Term(-4, 3));

    element1 = element1.derivative();
    assertEquals(element1.toString(), "25x^4+16x^3-12x^2");
  }

  /**
   * Test the toString() method for the element constructor.
   */
  @Test
  public void testElementNodeToString() {
    elementTest = empty.addTerm(new Term(5, 5));
    assertEquals(elementTest.toString(), "5x^5");
    element1 = element1.addTerm(new Term(4, 4));
    assertEquals(element1.toString(), "5x^5+4x^4");
    element1 = element1.addTerm(new Term(-4, 3));
    assertEquals(element1.toString(), "5x^5+4x^4-4x^3");
    element1 = element1.addTerm(new Term(3, 6));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3");
    element1 = element1.addTerm(new Term(9, 0));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3+9");
    element1 = element1.addTerm(new Term(0, 0));
    assertEquals(element1.toString(), "3x^6+5x^5+4x^4-4x^3+9");
    element1 = element1.addTerm(new Term(-4, 4));
    assertEquals(element1.toString(), "3x^6+5x^5-4x^3+9");
  }
}