package polynomial;

import java.util.Objects;

/**
 * This class represents each term of the polynomial. It has
 * two integer fields: coefficient and power.
 */
public class Term {

  private int coefficient;
  private int power;

  /**
   * This is the constructor of the Term class, it takes
   * two parameters -- coefficient and the power of the
   * term.
   *
   * @param coefficient the coefficient integer
   * @param power       the power integer
   */
  public Term(int coefficient, int power) {
    this.coefficient = coefficient;
    if (power < 0) {
      throw new IllegalArgumentException();
    }
    this.power = power;
  }

  /**
   * Get the coefficient field of the term class.
   * @return the coefficient integer
   */
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Get the power field of the term class.
   * @return the power integer
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Returns true if the power field of the current Term
   * class is smaller than that of the parameter Term class.
   * @param term a Term object
   * @return a boolean
   */
  public boolean hasSmallerPowerThan(Term term) {
    return this.power < term.getPower();
  }

  /**
   * Returns true if the power field of the current Term class equals
   * that of the parameter Term class.
   * @param term a Term object
   * @return a boolean
   */
  public boolean hasTheSamePowerTo(Term term) {
    return this.power == term.getPower();
  }

  /**
   * Merge the two terms of the same power, will throw Exception if their powers are not the same.
   * @param term the term needed to be merge
   * @return the new Term object after merging
   */
  public Term merge(Term term) throws IllegalArgumentException {
    if (this.getPower() != term.getPower()) {
      throw new IllegalArgumentException();
    } else {
      return new Term(this.getCoefficient() + term.getCoefficient(), this.getPower());
    }
  }

  /**
   * This method is used to calculate the derivative value of the term.
   * If the power equals zero, it should return a new empty term.
   *
   * @return the new Term object after the derivation operation.
   */
  public Term calculateDerivative() {
    if (this.getPower() == 0) {
      return new Term(0, 0);
    } else {
      return new Term(this.getCoefficient() * this.getPower(), this.getPower() - 1);
    }
  }

  /**
   * Returns the String form of the term class.
   * @return the String representation of the Term class
   */
  public String toString() {
    if (this.getCoefficient() == 0) {
      return "";
    } else if (this.getPower() == 0) {
      return String.valueOf(this.getCoefficient());
    } else {
      return String.format("%dx^%d", getCoefficient(), getPower());
    }
  }

  /**
   * This method calculate the value of the term when passing the designated
   * value.
   *
   * @param variable the passing value
   * @return the value double
   */
  public double calculateValue(double variable) {
    return this.getCoefficient() * Math.pow(variable, (double) this.getPower());
  }

  /**
   * Override the hashCode().
   * @return the hashCode integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }

  /**
   * This methods return true if the passing object
   * is the same as or has the same value as the current
   * class.
   *
   * @param obj the passing object Object
   * @return a boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Term)) {

      return false;
    }
    Term other = (Term) obj;
    return this.getPower() == other.getPower()
            && this.getCoefficient() == other.getCoefficient();
  }
}
