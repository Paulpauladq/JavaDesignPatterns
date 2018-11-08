package cs5010.lab2.fib;

/**
 * A class implements the Fibonacci counter.
 */
public class RealFibCounter implements FibonacciCounter {

  public int count;

  /**
   * Construct a RealFibCounter object that initialize its count to 1.
   */
  public RealFibCounter() {
    this.count = 1;
  }

  /**
   * A method that returns a FibonacciCounter object with its count incremented by 1.
   *
   * @return a FibonacciCounter object with its count increment by 1
   */
  @Override
  public FibonacciCounter incrementCounter() {
    this.count++;

    if (this.count == Integer.MAX_VALUE || this.count < 0) {
      throw new ArithmeticException("The count overflows!");
    }
    return this;
  }

  /**
   * A method that returns a FibonacciCounter object with its count decremented by 1.
   *
   * @return a FibonacciCounter object with its count decremented by 1
   */
  @Override
  public FibonacciCounter decrementCounter() {
    if (count > 1) {
      this.count--;
    } else {
      throw new ArithmeticException("Illegal Operation! Counter cannnot be less than 1!");
    }
    return this;
  }

  /**
   * A method that returns the current count of the counter.
   *
   * @return the current count of the counter
   */
  @Override
  public int currentCount() {
    return this.count;
  }

  /**
   * A method that returns the Fibonacci number corresponding to the current count.
   *
   * @return the Fibonacci number corresponding to the current count
   */
  @Override
  public int calculateFibonacci() {
    count = this.currentCount() - 1;
    int[] fibArr = new int[count + 2];

    fibArr[0] = 0;
    fibArr[1] = 1;

    for (int i = 2; i <= count; i++) {
      fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
    }

    if (fibArr[count] < 0) {
      throw new ArithmeticException("The Fibonacci number overflows!");
    }
    return fibArr[count];
  }
}
