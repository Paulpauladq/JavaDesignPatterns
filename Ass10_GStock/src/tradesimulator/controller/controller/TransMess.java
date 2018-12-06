package tradesimulator.controller.controller;

/**
 * Public enum class for transmit message.
 */
public enum TransMess {
  PROMPT_FOR_DATE("Enter a valid date(yyyy-MM-dd):"),
  PROMPT_FOR_VOLUME("Enter a valid volume:"),
  PROMPT_FOR_PORTF_NAME("Enter a valid portfolio name:"),
  PROMPT_FOR_STOCK_SYMBOL("Enter a valid stock symbol:"),
  PROMPT_FOR_STOCK_SYMBOL_LIST("Enter a valid stock symbol or q/Q to stop:"),
  PROMPT_FOR_RATIO("Enter a valid ratio for "),
  PROMPT_FOR_MONEY("Enter a valid money($):"),
  PROMPT_FOR_INTERVAL("Enter a valid interval:"),
  PROMPT_FOR_BEGIN_DATE("Enter a valid begin date(yyyy-MM-dd):"),
  PROMPT_FOR_END_DATE("Enter a valid end date(yyyy-MM-dd) or enter q/Q to make the plan ongoing:"),
  INVALID_DATE("Invalid date! Please enter again:"),
  INVALID_COMMANDS("Invalid commands! Please try again.\n"),
  INVALID_VOLUMES("Invalid volumes! Please enter again:"),
  INVALID_STOCK_SYMBOL("Invalid stock symbol! Please enter again:"),
  INVALID_STOCK_SYMBOL_LIST("Invalid symbol! Please try again.\n"),
  INVALID_MONEY("Invalid money! Please enter again:"),
  INVALID_INTERVAL("Invalid interval! Please enter again:"),
  INVALID_RATIO("Invalid ratio! Please try again."),
  SUCCESSFULLY_SELECT_RATIOS("Stocks and corresponding ratios are added successfully!"),
  PORTFOLIO_NAME_TOO_LONG("Portfolio name is too long!"),
  RATIO_SUM_DOESNT_EQUAL_ONE("The ratio sum doesn't equal 1! Please Try again!\n"),
  DUPLICATE_STOCK_SYMBOL("The stock symbol already exists!"),
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
