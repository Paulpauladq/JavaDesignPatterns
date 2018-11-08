package polynomial;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class implements this interface in a class PolynomialImpl. This class has two
 * constructor. One takes no parameter and initialize the head node
 * with a empty node, another takes a String parameter and return a constructed object
 * as what provided in the string. Also it overrides all the methods in the interface.
 */
public class PolynomialImpl implements Polynomial {

  private PolynomialNode head;

  /**
   * The constructor takes no parameter and construct a PolynomialImpl object
   * with an empty node.
   */
  public PolynomialImpl() {
    head = new PolynomialEmptyNode();
  }

  /**
   * The second constructor takes a String parameter and return a constructed object
   * as what provided in the string. Also it overrides all the methods in the interface.
   * It will throw exception if the string's format is wrong.
   */
  public PolynomialImpl(String str) {
    head = new PolynomialEmptyNode();
    //the regular expression for valid polynomial
    if (Pattern.matches("[+-]?(\\d+)(x\\^)(\\d+)(((\\s)[+|-]"
            + "(\\d+)(x\\^)(\\d+))|((\\s)[+|-](\\d+)))*", str)) {
      Scanner scanner = new Scanner(str);

      while (scanner.hasNext()) {
        String strTemp = scanner.next();

        //assign the symbol
        int symbol = 1;
        if (strTemp.charAt(0) == '-') {
          symbol = -1;
        }

        if (strTemp.charAt(0) == '+' || strTemp.charAt(0) == '-') {
          strTemp = strTemp.substring(1);
        }
        //extract terms and handle each term
        Scanner scannerTemp = new Scanner(strTemp);
        if (strTemp.indexOf('x') != -1) {
          scannerTemp.useDelimiter("x\\^");
          int coeTemp = symbol * scannerTemp.nextInt();
          int powTemp = scannerTemp.nextInt();
          head = head.addTerm(new Term(coeTemp, powTemp));
        } else {
          int coeTemp = symbol * scannerTemp.nextInt();
          head = head.addTerm(new Term(coeTemp, 0));
        }
      }
      //the constant case
    } else if (Pattern.matches("[+-]?(\\d+)", str)) {
      head = head.addTerm(new Term(Integer.parseInt(str), 0));
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * A method addTerm that takes a coefficient and a power (both whole numbers) and adds
   * the resulting term to this polynomial. This method should not return anything. It
   * should throw an IllegalArgumentException if the term is invalid. The polynomial
   * should merge two term if the provided term has the same power with the term already
   * contained in the polynomial object. This term should be removed if their coefficients
   * are added up to 0.
   *
   * @param coefficient the coefficient integer
   * @param power       the power integer
   */
  @Override
  public void addTerm(int coefficient, int power) {
    Term newTerm = new Term(coefficient, power);
    head = head.addTerm(newTerm);
  }

  /**
   * A method getDegree that returns the degree of this polynomial.
   *
   * @return the degree integer
   */
  @Override
  public int getDegree() {
    return head.getDegree();
  }

  /**
   * A method getCoefficient that takes a power and returns the coefficient
   * for the term with that power.
   *
   * @param power the power integer
   * @return the coefficient integer
   */
  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  /**
   * A method evaluate that takes a double-precision decimal number and returns
   * a double-precision result which is the evaluation of this polynomial
   * using this argument’s value.
   *
   * @param value the passing variable
   * @return the calculated double
   */
  @Override
  public double evaluate(double value) {
    return head.evaluate(value);
  }

  /**
   * This method add two polynomial objects terms by terms. If the terms
   * have the same power, it should merge two terms by adding their coefficient.
   * If the sum of their coefficients is zero, it should remove this term. This
   * method is immutable.
   *
   * @param polynomial the passing polynomial object
   * @return the Polynomial object
   */
  @Override
  public Polynomial add(Polynomial polynomial) {
    Polynomial newPoly = new PolynomialImpl();
    //get head of the passing poly
    PolynomialNode addPolyHead = ((PolynomialImpl) polynomial).head;

    if (head.getClass() != PolynomialEmptyNode.class) {
      //copy element in this
      PolynomialElementNode iterator = (PolynomialElementNode) head;
      while (iterator.getNext().getClass() != PolynomialEmptyNode.class) {
        newPoly.addTerm(iterator.getTerm().getCoefficient(), iterator.getTerm().getPower());
        iterator = (PolynomialElementNode) iterator.getNext();
      }
      newPoly.addTerm(iterator.getTerm().getCoefficient(), iterator.getTerm().getPower());
    }

    if (addPolyHead.getClass() != PolynomialEmptyNode.class) {
      //copy element in passing parameter
      PolynomialElementNode addIterator = (PolynomialElementNode) addPolyHead;
      while (addIterator.getNext().getClass() != PolynomialEmptyNode.class) {
        newPoly.addTerm(addIterator.getTerm().getCoefficient(), addIterator.getTerm().getPower());
        addIterator = (PolynomialElementNode) addIterator.getNext();
      }
      newPoly.addTerm(addIterator.getTerm().getCoefficient(), addIterator.getTerm().getPower());
    }

    return newPoly;
  }

  /**
   * A method derivative that takes no parameters and returns the polynomial obtained by
   * differentiating this polynomial. This method should be immutable.
   */
  @Override
  public Polynomial derivative() {
    Polynomial newPoly = new PolynomialImpl();
    Polynomial derPoly = new PolynomialImpl();

    if (head.getClass() != PolynomialEmptyNode.class) {
      //copy element in this
      PolynomialElementNode iterator = (PolynomialElementNode) head;
      while (iterator.getNext().getClass() != PolynomialEmptyNode.class) {
        newPoly.addTerm(iterator.getTerm().getCoefficient(), iterator.getTerm().getPower());
        iterator = (PolynomialElementNode) iterator.getNext();
      }
      newPoly.addTerm(iterator.getTerm().getCoefficient(), iterator.getTerm().getPower());

      PolynomialElementNode derivativeHeadNode =
              (PolynomialElementNode) ((PolynomialImpl) newPoly).head.derivative();
      PolynomialElementNode derIterator = derivativeHeadNode;

      //take derivative of each term when traversing the list
      while (derIterator.getNext().getClass() != PolynomialEmptyNode.class) {

        derPoly.addTerm(derIterator.getTerm().getCoefficient(), derIterator.getTerm().getPower());
        derIterator = (PolynomialElementNode) derIterator.getNext();
      }
      derPoly.addTerm(derIterator.getTerm().getCoefficient(), derIterator.getTerm().getPower());
    }

    return derPoly;
  }

  /**
   * A method defines whether two polynomials are equal.
   *
   * @param polynomial the passing polynomial object
   * @return the boolean
   */
  @Override
  public boolean equals(Object polynomial) {
    if (this == polynomial) {
      return true;
    }
    if (!(polynomial instanceof Polynomial)) {
      return false;
    }
    return this.toString().equals(polynomial.toString());
  }

  /**
   * Get the hash code.
   * @return the hashcode integer.
   */
  @Override
  public int hashCode() {
    return Objects.hash(head);
  }

  /**
   * A method returns the String for the polynomial.

   *
   * @return the polynomial string
   */
  @Override
  public String toString() {
    return head.toString();
  }
}
