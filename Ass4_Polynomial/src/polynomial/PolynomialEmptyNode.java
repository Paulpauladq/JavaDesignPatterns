package polynomial;

/**
 * This class implements the PolynomialNode. It represents the empty
 * Polynomial node.
 */
public class PolynomialEmptyNode implements PolynomialNode {

  /**
   * This method adds the passing term object to the front of the polynomial.
   * @param term the passing Term object
   * @return the polynomial node
   */
  @Override
  public PolynomialNode addFront(Term term) {
    return new PolynomialElementNode(term, this);
  }

  /**
   * This method adds the passing term object to the polynomial.
   * @param term the passing Term object
   * @return the PolynomialNode object
   */
  @Override
  public PolynomialNode addTerm(Term term) {
    return addFront(term);
  }

  /**
   * The method gets the degree of the node object.
   * @return the degree integer
   */
  @Override
  public int getDegree() {
    return 0;
  }

  /**
   * The method gets the coefficient of the node object.
   * @param power the power integer
   * @return the coefficient integer
   */
  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  /**
   * The methods calculate the double evaluation of the node.
   * @param value the passing value
   * @return the double evaluation
   */
  @Override
  public double evaluate(double value) {
    return 0.0;
  }

  /**
   * The methods calculate the derivative of the polynomial.
   * @return the polynomial node
   */
  @Override
  public PolynomialNode derivative() {
    return null;
  }

  /**
   * The methods return String for the polynomial.
   *
   * @return the polynomial String
   */
  @Override
  public String toString() {
    return "0";
  }
}
