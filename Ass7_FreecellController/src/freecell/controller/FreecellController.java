package freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import freecell.model.FreecellOperations;
import freecell.model.PileType;
import freecell.utils.Card;
import freecell.utils.ModelErrMess;

/**
 * This class is the controller to control the game model for Freecell(both
 * single move and multi move). It contains a constructor takes two
 * parameter and a playGame method to play the game.
 */
public class FreecellController implements IFreecellController<Card> {

  private final Readable in;
  private final Appendable out;

  private static final String PILE_REGEX = "[CFO][\\d]+";
  private static final String CARD_REGEX = "[\\d]+";
  private static final String QUIT_REGEX = "[q|Q]";

  private GameState gameState;

  private PileType sourPile;
  private PileType destPile;

  private int sourNum;
  private int cardIndex;
  private int destNum;

  /**
   * A enum of the game state for state transfer.
   */
  public enum GameState {
    INIT_STATE,
    WAIT_SOUR_NUM,
    WAIT_CARD_INDEX,
    WAIT_DEST_NUM,
    MAKE_MOVE
  }

  /**
   * The freecell controller takes two parameters: the first one is Readable and
   * the second one is Appendable for output.
   *
   * @param rd Readable object
   * @param ap Appendable object
   * @throws IllegalArgumentException the illegal argument exception
   */
  public FreecellController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException(
              ControllerErrMess.APPENDABLE_OR_READABLE_IS_NULL.getMessage());
    }
    in = rd;
    out = ap;
  }

  /**
   * Start and play a new game of freecell with the provided deck. This deck
   * should be used as-is. This method returns only when the game is over
   * (either by winning or by quitting)
   *
   * @param deck    the deck to be used to play this game
   * @param model   the model for the game
   * @param shuffle shuffle the deck if true, false otherwise
   * @throws IllegalArgumentException if the deck is null or invalid, or if the
   *                                  model is null
   * @throws IllegalStateException    if the controller is unable to read input
   *                                  or transmit output
   */
  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, boolean shuffle)
          throws IllegalArgumentException, IllegalStateException {
    if (deck == null || model == null) {
      throw new IllegalArgumentException(ControllerErrMess.DECK_OR_MODEL_IS_NULL.getMessage());
    }

    try {
      model.startGame(deck, shuffle);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(ModelErrMess.INVALID_DECK_SIZE.getMessage());
    }

    gameState = GameState.INIT_STATE;
    //take the input
    Scanner scan = new Scanner(this.in).useDelimiter("[ \\n]");
    while (true) {
      boolean isGameOver;
      switch (gameState) {
        case INIT_STATE:
          isGameOver = isOver(model);
          if (isGameOver) {
            return;
          }
          break;
        case WAIT_SOUR_NUM:
          isGameOver = waitSourNum(scan);
          if (isGameOver) {
            return;
          }
          break;
        case WAIT_CARD_INDEX:
          isGameOver = waitCardIndex(scan);
          if (isGameOver) {
            return;
          }
          break;
        case WAIT_DEST_NUM:
          isGameOver = waitDestNum(scan);
          if (isGameOver) {
            return;
          }
          break;
        case MAKE_MOVE:
          makeMove(model);
          break;
        default:
      }
    }
  }

  /**
   * The private helper to append a message to the appendable object.
   *
   * @param transMes the message String
   * @throws IllegalStateException if the append operation fails
   */
  private void appendMess(String transMes) throws IllegalStateException {
    try {
      out.append(transMes);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException(ControllerErrMess.APPEND_FAILS.getMessage());
    }
  }

  /**
   * The boolean to test whether the game is over at the initialization state.
   *
   * @param model the game model provided as a parameter
   * @return the boolean value
   * @throws IllegalStateException if the append operation fails
   */
  private boolean isOver(FreecellOperations<Card> model) throws IllegalStateException {
    appendMess(model.getGameState());
    if (model.isGameOver()) {
      appendMess(TransmitMessage.GAME_OVER.getMessage());
      return true;
    } else {
      gameState = GameState.WAIT_SOUR_NUM;
      return false;
    }
  }

  /**
   * The method is used to check whether the state is waiting the source pile number.
   *
   * @param scan the input Scanner object
   * @return the boolean value
   * @throws IllegalStateException if append operation fails
   */
  private boolean waitSourNum(Scanner scan) throws IllegalStateException {

    if (!scan.hasNext()) {
      throw new IllegalArgumentException(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage());
    }
    String token = scan.next();
    appendMess(TransmitMessage.PROMPT_FOR_SOUR_NUM.getMessage());
    //C1,O1, F1
    if (token.matches(PILE_REGEX)) {
      sourPile = selectPile(token.charAt(0));
      sourNum = Integer.parseInt(token.substring(1));
      gameState = GameState.WAIT_CARD_INDEX;
      return false;
    }
    //q,Q
    else if (token.matches(QUIT_REGEX)) {
      appendMess(TransmitMessage.GAME_IS_QUITED.getMessage());
      return true;
    }
    //invalid input
    else {
      appendMess(TransmitMessage.INVALID_SOUR_NUM.getMessage());
      gameState = GameState.WAIT_SOUR_NUM;
      return false;
    }
  }

  /**
   * The method is used to check whether the state is waiting the card index.
   *
   * @param scan the input Scanner object
   * @return the boolean value
   * @throws IllegalStateException if append operation fails
   */
  private boolean waitCardIndex(Scanner scan) throws IllegalStateException {
    if (!scan.hasNext()) {
      throw new IllegalArgumentException(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage());
    }
    String token = scan.next();
    appendMess(TransmitMessage.PROMPT_FOR_CARD_INDEX.getMessage());
    if (token.matches(CARD_REGEX)) {
      cardIndex = Integer.parseInt(token);
      gameState = GameState.WAIT_DEST_NUM;
      return false;
    } else if (token.matches(QUIT_REGEX)) {
      appendMess(TransmitMessage.GAME_IS_QUITED.getMessage());
      return true;
    } else {
      appendMess(TransmitMessage.INVALID_CARD_INDEX.getMessage());
      gameState = GameState.WAIT_CARD_INDEX;
      return false;
    }
  }

  /**
   * The method is used to check whether the state is waiting the destination pile number.
   *
   * @param scan the input Scanner object
   * @return the boolean value
   * @throws IllegalStateException if append operation fails
   */
  private boolean waitDestNum(Scanner scan) throws IllegalStateException {
    if (!scan.hasNext()) {
      throw new IllegalArgumentException(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage());
    }
    String token = scan.next();
    appendMess(TransmitMessage.PROMPT_FOR_DEST_NUM.getMessage());
    if (token.matches(PILE_REGEX)) {
      destPile = selectPile(token.charAt(0));
      destNum = Integer.parseInt(token.substring(1));
      gameState = GameState.MAKE_MOVE;
      return false;
    } else if (token.matches(QUIT_REGEX)) {
      appendMess(TransmitMessage.GAME_IS_QUITED.getMessage());
      return true;
    } else {
      appendMess(TransmitMessage.INVALID_DEST_INDEX.getMessage());
      gameState = GameState.WAIT_DEST_NUM;
      return false;
    }
  }

  /**
   * Check the input and make move.
   *
   * @param model the input game model
   * @throws IllegalStateException if the append operation fails
   */
  private void makeMove(FreecellOperations<Card> model) throws IllegalStateException {
    try {
      model.move(sourPile, sourNum - 1, cardIndex - 1, destPile, destNum - 1);
      gameState = GameState.INIT_STATE;
    } catch (IllegalStateException | IllegalArgumentException e) {
      appendMess(TransmitMessage.INVALID_MOVE.getMessage() + e.getMessage());
      gameState = GameState.WAIT_SOUR_NUM;
    }
  }

  /**
   * Private helper to select a pile.
   *
   * @param input the input char
   * @return the PileType
   */
  private PileType selectPile(char input) {
    switch (input) {
      case 'C':
        return PileType.CASCADE;
      case 'O':
        return PileType.OPEN;
      case 'F':
        return PileType.FOUNDATION;
      default:
        return null;
    }
  }
}