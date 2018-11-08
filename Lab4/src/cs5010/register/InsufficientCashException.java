package cs5010.register;

/**
 * This class represents an exception where the cash register does not have
 * enough change to dispense the required amount.
 */
public class InsufficientCashException extends Exception {
  public InsufficientCashException(String msg) {
    super(msg);
  }
}
