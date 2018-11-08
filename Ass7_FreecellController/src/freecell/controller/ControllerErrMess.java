package freecell.controller;

/**
 * The enum class for all the error messages.
 */
public enum ControllerErrMess {
  APPENDABLE_OR_READABLE_IS_NULL("Readable or Appendable parameter is null!"),
  DECK_OR_MODEL_IS_NULL("Deck or model parameter is null!"),
  APPEND_FAILS("Append operation fails!"),
  SCANNER_DOESNT_HAS_NEXT("Scanner doesn't has next element!");

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
  public String getMessage() {
    return this.message;
  }
}
