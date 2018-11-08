package calculator;

import java.util.HashSet;

/**
 * This is a abstractCalculator class which implements
 * the Calculator interface. This class implements a part of the
 * common functions for its children class. Also, it has two
 * constructors -- a normal constructor and a copy constructor.
 */
public abstract class AbstractCalculator implements Calculator {

  public String resultString;
  public int lastOperand;
  public int lastResult;
  public char currOperator;
  public char implicitOperator;
  public int implicitOperand;

  private HashSet<Character> validSet = new HashSet<Character>();
  private static final char[] VALIDCHARARRAY =
          new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'C', '+', '-', '*', '='};

  /**
   * The common constructor initializes all the fields of the Abstract class.
   */
  public AbstractCalculator() {
    this.resultString = "";
    this.lastOperand = 0;
    this.lastResult = 0;
    this.currOperator = '#';
    this.implicitOperator = '#';
    this.implicitOperand = 0;
  }

  /**
   * The copy constructor makes a deep copy of the original object.
   *
   * @param copy a Abstract object
   */
  public AbstractCalculator(AbstractCalculator copy) {
    this.resultString = copy.resultString;
    this.lastResult = copy.lastResult;
    this.lastOperand = copy.lastOperand;
    this.currOperator = copy.currOperator;
    this.implicitOperator = copy.implicitOperator;
    this.implicitOperand = copy.implicitOperand;
  }

  /**
   * A helper function which checks if the input is valid
   * (i.e. belongs to a set of designated characters).
   *
   * @param character a char parameter
   * @return a boolean
   */
  public boolean isInputValid(char character) {
    for (int i = 0; i < VALIDCHARARRAY.length; i++) {
      validSet.add(new Character(VALIDCHARARRAY[i]));
    }
    return validSet.contains(character);
  }

  /**
   * A abstract class of input.
   *
   * @param character a char
   * @return a Calculator object
   */
  @Override
  public abstract Calculator input(char character);

  /**
   * A implementation for the getResult() for the interface.
   *
   * @return a String object
   */
  @Override
  public String getResult() {
    return resultString;
  }
}
