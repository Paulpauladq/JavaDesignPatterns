package polynomial;

/**
 * This interface describes a polynomial class.
 * A polynomial is made of several terms, each term having a coefficient and a variable
 * raised to a power. Polynomials are one-variable if all their terms contain only one variable.
 * Polynomial that defines the above operations, specifically with the following method signatures:
 * 1) A method addTerm that takes a coefficient and a power (both whole numbers) and adds
 * the resulting term to this polynomial. This method should not return anything.
 * It should throw an IllegalArgumentException if the term is invalid.
 * 2) A method getDegree that returns the degree of this polynomial.
 * 3) A method getCoefficient that takes a power and returns the coefficient for the
 * term with that power.
 * 4) A method evaluate that takes a double-precision decimal number and returns a
 * double-precision result which is the evaluation of this polynomial using this argument’s value.
 * 5) A method add that takes another Polynomial object and returns the polynomial obtained by
 * adding the two polynomials. Any implementation should ensure that this method does not mutate
 * either polynomial.
 * 6)A method derivative that takes no parameters and returns the polynomial obtained by
 * differentiating this polynomial.
 */
public interface Polynomial {


  /**
   * A method addTerm that takes a coefficient and a power (both whole numbers) and adds the
   * resulting term to this polynomial. This method should not return anything. It should
   * throw an IllegalArgumentException if the term is invalid.
   * @param coefficient the coefficient integer
   * @param power the power integer
   */
  void addTerm(int coefficient, int power);

  /**
   * A method getDegree that returns the degree of this polynomial.
   * @return the degree integer
   */
  int getDegree();


  /**
   * A method getCoefficient that takes a power and returns the coefficient for the
   * term with that power.
   * @param power the power integer
   * @return the coefficient of the specific power
   */
  int getCoefficient(int power);

  /**
   * A method evaluate that takes a double-precision decimal number and returns a
   * double-precision result which is the evaluation of this polynomial using this
   * argument’s value.
   * @param value the passing variable
   * @return the double value
   */
  double evaluate(double value);

  /**
   * A method add that takes another Polynomial object and returns the polynomial
   * obtained by adding the two polynomials. Any implementation should ensure that
   * this method does not mutate either polynomial.
   * @param polynomial the Polynomial object to be added
   * @return the new Polynomial object
   */
  Polynomial add(Polynomial polynomial);

  /**
   * A method derivative that takes no parameters and returns the polynomial obtained
   * by differentiating this polynomial.
   * @return the Polynomial object after derivation
   */
  Polynomial derivative();

  /**
   * A method defines whether two polynomials are equal.
   * @param polynomial the passing polynomial object
   * @return the boolean value
   */
  boolean equals(Object polynomial);

  /**
   * A method returns the String for the polynomial.
   * @return String for polynomial
   */
  String toString();
}

