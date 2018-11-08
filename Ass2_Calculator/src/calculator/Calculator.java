package calculator;

/**
 * This is the interface for implementing all series of calculators.
 * It has two methods requires to be implemented. Input() takes a char
 * parameter and returns a Calculator object. GetResult() takes no
 * parameter and return a String object.
 */
public interface Calculator {
  /**
   * A method input that takes a single character as its only argument.
   * This method should return a Calculator object as a result of processing the input.
   * @param character a char
   * @return a Calculator
   */
  public Calculator input(char character);

  /**
   * A method getResult that does not take any arguments and
   * returns the current “result” of the calculator (i.e. the message
   * that we would normally see on the screen) as a String object.
   * @return a String object
   */
  public String getResult();
}
