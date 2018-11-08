package polynomial;

/**
 * This class is the interface of the PolynomialNode. It defines a
 * class that has the same architecture with the polynomial node.
 */
public interface PolynomialNode {

  /**
   * This method adds the passing term object to the front of the polynomial.
   * @param term the passing Term object
   * @return the polynomial node
   */
  PolynomialNode addFront(Term term);

  /**
   * This method adds the passing term object to the polynomial.
   * @param term the passing Term object
   * @return the PolynomialNode object
   */
  PolynomialNode addTerm(Term term);

  /**
   * The method gets the degree of the node object.
   * @return the degree integer
   */
  int getDegree();

  /**
   * The method gets the coefficient of the node object.
   * @param power the power integer
   * @return the coefficient integer
   */
  int getCoefficient(int power);

  /**
   * The methods calculate the double evaluation of the node.
   * @param value the passing value
   * @return the double evaluation
   */
  double evaluate(double value);

  /**
   * The methods calculate the derivative of the polynomial.
   * @return the polynomial node
   */
  PolynomialNode derivative();

  /**
   * The methods return String for the polynomial.
   *
   * @return the polynomial String
   */
  String toString();
}
