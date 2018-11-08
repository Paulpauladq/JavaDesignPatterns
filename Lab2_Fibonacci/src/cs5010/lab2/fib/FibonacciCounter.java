package cs5010.lab2.fib;

/**
 * An interface that represents a single Fibonacci counter.
 */
public interface FibonacciCounter {
  /**
   * A method that returns a FibonacciCounter object with its count incremented by 1.
   *
   * @return a FibonacciCounter object with its count increment by 1
   */
  public FibonacciCounter incrementCounter();

  /**
   * A method that returns a FibonacciCounter object with its count decrement by 1.
   *
   * @return a FibonacciCounter object with its count decrement by 1
   */
  public FibonacciCounter decrementCounter();

  /**
   * A method that returns the current count of the counter.
   *
   * @return the current count of the counter
   */
  public int currentCount();

  /**
   * A method that returns the Fibonacci number corresponding to the current count.
   *
   * @return the Fibonacci number corresponding to the current count
   */
  public int calculateFibonacci();
}
