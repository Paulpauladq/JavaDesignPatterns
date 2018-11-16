package tradesimulator.controller.controller;

/**
 * Public enum class for transmit message.
 */
public enum TransMess {
  PROMPT_FOR_DATE("Enter a valid date(yyyy-MM-dd):"),
  PROMPT_FOR_VOLUME("Enter a valid volume:"),
  PROMPT_FOR_PORTF_NAME("Enter a valid portfolio name:"),
  PROMPT_FOR_STOCK_SYMBOL("Enter a valid stock symbol:"),
  INVALID_DATE("Invalid date! Please enter again:"),
  INVALID_COMMANDS("Invalid commands! Please try again.\n"),
  INVALID_VOLUMES("Invalid volumes! Please enter again:"),
  INVALID_STOCK_SYMBOL("Invalid stock symbol! Please enter again:"),
  PORTFOLIO_NAME_TOO_LONG("Portfolio name is too long!"),
  SIMULATOR_IS_QUITED("Simulator quit prematurely.");

  private String message;

  /**
   * Private constructor.
   *
   * @param message the String message
   */
  private TransMess(String message) {
    this.message = message;
  }

  /**
   * getMessage() method are used to get its message String.
   *
   * @return the message String
   */
  public String getMess() {
    return message;
  }
}
