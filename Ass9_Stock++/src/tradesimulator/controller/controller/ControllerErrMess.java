package tradesimulator.controller.controller;

/**
 * The enum class for all the error messages.
 */
public enum ControllerErrMess {
  APPENDABLE_OR_READABLE_IS_NULL("Readable or Appendable parameter is null!"),
  MODEL_IS_NULL("model parameter is null!"),
  APPEND_FAILS("Append operation fails!"),
  READ_FAILS("Read operation fails!"),
  EXECUTION_FAILS("Execution failed, please try again.");

  private String message;

  /**
   * Constructor to assign the message String.
   *
   * @param message the passing String
   */
  private ControllerErrMess(String message) {
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
