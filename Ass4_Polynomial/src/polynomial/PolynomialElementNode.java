package polynomial;

/**
 * This class implements the PolynomialNode interface. It represents the
 * element node for a list node consisting of the term object. It has one
 * constructor which takes two parameters -- a Term object and a next pointer
 * references to the next Element Node object.
 */
public class PolynomialElementNode implements PolynomialNode {

  private Term term;
  private PolynomialNode next;

  /**
   * The constructor takes two parameters -- a Term object and a next pointer
   * references to the next Element Node object.
   *
   * @param term the Term object
   * @param next the next pointer
   */
  public PolynomialElementNode(Term term, PolynomialNode next) {
    this.term = term;
    this.next = next;
  }

  /**
   * This method gets the Term object contained in the ElementNode object.
   *
   * @return the Term object
   */
  public Term getTerm() {
    return this.term;
  }

  /**
   * This method gets the next pointer.
   *
   * @return the next pointer
   */
  public PolynomialNode getNext() {
    return this.next;
  }

  /**
   * This method adds the passing term object to the front of the polynomial.
   *
   * @param term the passing Term object
   * @return the polynomial node
   */
  @Override
  public PolynomialNode addFront(Term term) {
    return new PolynomialElementNode(term, this);
  }

  /**
   * This method adds the passing term object to the polynomial.
   *
   * @param term the passing Term object
   * @return the PolynomialNode object
   */
  @Override
  public PolynomialNode addTerm(Term term) {
    PolynomialElementNode prev = new PolynomialElementNode(null, null);
    PolynomialElementNode iterator = new PolynomialElementNode(null, null);
    PolynomialElementNode insertedTerm = new PolynomialElementNode(term, null);

    if (term.getPower() <= this.term.getPower()) {
      iterator = this;
      //->->->->->->search for the right insert position->->->->->->
      while (!iterator.term.hasSmallerPowerThan(term)) {
        //if has the same power
        if (iterator.term.hasTheSamePowerTo(term)) {
          if (iterator.term.getCoefficient() + term.getCoefficient() == 0) {
            prev.next = iterator.next;
            return this;
          } else {
            iterator.term = iterator.term.merge(term);
            return this;
          }
        }
        //!!!!!!!!!!!!!!!!!!!reaches end!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (iterator.next.getClass() == PolynomialEmptyNode.class) {
          iterator.next = insertedTerm;
          insertedTerm.next = new PolynomialEmptyNode();
          return this;
        }
        prev = iterator;
        iterator = (PolynomialElementNode) iterator.next;
      }
      prev.next = insertedTerm;
      insertedTerm.next = iterator;
      return this;
    } else {
      return new PolynomialElementNode(term, this);
    }
  }

  /**
   * The method gets the degree of the node object.
   *
   * @return the degree integer
   */
  @Override
  public int getDegree() {
    return this.term.getPower();
  }

  /**
   * The method gets the coefficient of the node object.
   *
   * @param power the power integer
   * @return the coefficient integer
   */
  @Override
  public int getCoefficient(int power) {
    PolynomialElementNode iterator = this;
    while (power != iterator.term.getPower()) {
      //##############when the pointer.next reaches empty###################
      if (iterator.next.getClass() == PolynomialEmptyNode.class) {
        return 0;
      }
      iterator = (PolynomialElementNode) iterator.next;
    }
    return iterator.getTerm().getCoefficient();
  }

  /**
   * The methods calculate the double evaluation of the node.
   *
   * @param value the passing value
   * @return the double evaluation
   */
  @Override
  public double evaluate(double value) {
    PolynomialElementNode iterator = this;
    double sum = 0.0;
    while (iterator.next.getClass() != PolynomialEmptyNode.class) {
      sum += iterator.term.calculateValue(value);
      iterator = (PolynomialElementNode) iterator.next;
    }
    sum += iterator.term.calculateValue(value);
    return sum;
  }

  /**
   * The methods calculate the derivative of the polynomial.
   *
   * @return the polynomial node
   */
  @Override
  public PolynomialNode derivative() {
    PolynomialElementNode iterator = this;
    while (iterator.next.getClass() != PolynomialEmptyNode.class) {
      iterator.term = iterator.term.calculateDerivative();
      iterator = (PolynomialElementNode) iterator.next;
    }
    iterator.term = iterator.term.calculateDerivative();
    return this;
  }

  /**
   * The methods return String for the polynomial.
   *
   * @return the polynomial String
   */
  @Override
  public String toString() {
    PolynomialElementNode iterator = this;
    StringBuilder resultStr = new StringBuilder();

    while (iterator.next.getClass() != PolynomialEmptyNode.class) {
      if (iterator.term.getCoefficient() > 0) {
        resultStr.append("+" + iterator.term.toString());
      } else {
        resultStr.append(iterator.term.toString());
      }
      iterator = (PolynomialElementNode) iterator.next;
    }
    //print the last element
    if (iterator.term.getCoefficient() > 0) {
      resultStr.append("+" + iterator.term.toString());
    } else {
      resultStr.append(iterator.term.toString());
    }

    if (resultStr.length() > 0) {
      return resultStr.toString().charAt(0) == '+' ?
              resultStr.toString().substring(1) : resultStr.toString();
    } else {
      return "0";
    }
  }
}
