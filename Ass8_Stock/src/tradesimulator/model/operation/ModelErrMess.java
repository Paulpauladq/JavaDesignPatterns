package tradesimulator.model.operation;

public enum ModelErrMess {
  PORTF_NAME_EXISTS("Portfolio name exists!"),
  TOO_MUCH_PORTF("There're too much portfolios!"),
  NO_MUCH_PORTF("No such portfolio!"),
  PORTF_SIZE_CANT_BE_NEG("Portfolio size can't be negative!");

  private String message;

  /**
   * Constructor to assign the message String.
   *
   * @param message the passing String
   */
  private ModelErrMess(String message) {
    this.message = message;
  }

  /**
   * getMessage() method to get the message String.
   *
   * @return the message String
   */
  public String getMess() {
    return this.message;
  }
}
