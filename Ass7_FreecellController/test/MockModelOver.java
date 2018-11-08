import java.util.List;

import freecell.model.FreecellOperations;
import freecell.model.PileType;
import freecell.utils.Card;

/**
 * This class is a dummy class model used to factor out controller when testing.
 * THe methods in this class mocks the real freecell model, but do dummy operations.
 */
public class MockModelOver implements FreecellOperations<Card> {
  private StringBuilder log;
  private final String uniqueCode;

  /**
   * Constructor of the mock model.
   *
   * @param log        the log builder
   * @param uniqueCode the unique code represents the game state String
   */
  public MockModelOver(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  /**
   * Dummy getDeck().
   *
   * @return the Card List object
   */
  @Override
  public List<Card> getDeck() {
    return null;
  }

  /**
   * Dummy startGame() method.
   *
   * @param deck    the deck to be dealt
   * @param shuffle if true, shuffle the deck else deal the deck as-is
   * @throws IllegalArgumentException if exception happens
   */
  @Override
  public void startGame(List<Card> deck, boolean shuffle) throws IllegalArgumentException {
    if (shuffle) {
      log.append("Start game with shuffled deck!\n");
    } else {
      log.append("Start game with original deck!\n");
    }
  }

  /**
   * Dummy move().
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if it happens
   * @throws IllegalStateException    if it happens
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
          throws IllegalArgumentException, IllegalStateException {

    log.append("Source pile:" + source.toString() + pileNumber + "\n"
            + "Card Index:" + cardIndex + "\n" + "Destination pile:" + destination.toString()
            + destPileNumber + "\n");
  }

  /**
   * Dummy isGameOver().
   *
   * @return false
   */
  @Override
  public boolean isGameOver() {
    return true;
  }

  /**
   * Dummy getGameState().
   *
   * @return the unique code String
   */
  @Override
  public String getGameState() {
    return uniqueCode;
  }
}
