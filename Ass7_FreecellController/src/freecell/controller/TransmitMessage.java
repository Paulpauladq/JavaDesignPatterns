package freecell.controller;

/**
 * Public enum class for transmit message.
 */
public enum TransmitMessage {
  PROMPT_FOR_SOUR_NUM("Enter a valid source pile number:"),
  PROMPT_FOR_CARD_INDEX("Enter a valid card index:"),
  PROMPT_FOR_DEST_NUM("Enter a valid destination number:"),
  INVALID_SOUR_NUM("Invalid source pile number! Please enter again:"),
  INVALID_CARD_INDEX("Invalid card index! Please enter again:"),
  INVALID_DEST_INDEX("Invalid destination pile number! Please enter again:"),
  GAME_IS_QUITED("Game quit prematurely."),
  GAME_OVER("Game over."),
  INVALID_MOVE("Invalid move. Try again.");

  private String message;

  /**
   * Private constructor.
   *
   * @param message the String message
   */
  private TransmitMessage(String message) {
    this.message = message;
  }

  /**
   * getMessage() method are used to get its message String.
   *
   * @return the message String
   */
  public String getMessage() {
    return message;
  }
}
