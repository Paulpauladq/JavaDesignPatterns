package tradesimulator.model.operation;

/**
 * The enum represents the error message of the model.
 */
public enum ModelErrMess {
  PORTF_NAME_EXISTS("Portfolio name exists!"),
  TOO_MUCH_PORTF("There're too much portfolios!"),
  NO_SUCH_PORTF("No such portfolio!"),
  PORTF_SIZE_CANT_BE_NEG("Portfolio size can't be negative!"),
  BEGIN_DATE_IS_AFTER_END_DATE("Begin date can't be after the end date!"),
  INVEST_DATE_SHOULDNT_BE_FUTURE("Invest date can't be future date!"),
  ILLEGAL_MONEY("Illegal money amount!"),
  END_DATE_IS_AFTER_NOW("End date can't be after today!"),
  BEGIN_DATE_IS_AFTER_NOW("Begin date can't be after today!"),
  ILLEGAL_COMMISSION_FEE("Illegal commission fee!"),
  ILLEGAL_INTERVAL("Illegal intervals!");

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
