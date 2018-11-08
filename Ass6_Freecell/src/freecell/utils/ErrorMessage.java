package freecell.utils;

/**
 * The enum class for all the error messages.
 */
public enum ErrorMessage {
  INVALID_CARD_VALUE("Illegal card value!"),
  INVALID_CASCADE_PILE_NUMBER("Invalid cascade pile number!"),
  INVALID_OPEN_PILE_NUMBER("Invalid open pile number!"),
  NULL_DECK("Deck can't be null!"),
  INVALID_DECK_SIZE("Invalid deck size!"),
  DUPLICATE_CARD_IN_DECK("There exists duplicate cards in deck!"),
  GAME_NOT_STARTED("The game has not started!"),
  INVALID_PILE_NUMBER("Invalid pile number!"),
  INVALID_CARD_INDEX_FOR_MOVE("Invalid index for card to be moved!"),
  INVALID_MOVE_OPERATION("Invalid move operation!"),
  INVALID_PILE_TYPE("Invalid PileType!");

  private String message;

  /**
   * Constructor to assign the message String.
   *
   * @param message the passing String
   */
  private ErrorMessage(String message) {
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
