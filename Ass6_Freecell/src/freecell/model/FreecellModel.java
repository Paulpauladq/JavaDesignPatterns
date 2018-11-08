package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import freecell.utils.Card;
import freecell.utils.ErrorMessage;
import freecell.utils.Suit;

/**
 * Freecell model class is a class designed for implementation the mechanism for
 * the game model. Its constructor can be called by the builder. It also has several
 * methods.
 */
public class FreecellModel implements FreecellOperations<Card> {

  private static final int DECK_SIZE = 52;
  private static final int VALUE_SIZE = 13;
  private static final int SUIT_SIZE = Suit.values().length;
  // OPEN, CASCADE, FOUNDATION

  private List<ArrayList<Card>> openPile;
  private List<ArrayList<Card>> cascadePile;
  private List<ArrayList<Card>> foundationPile;
  private int openPileNum;
  private int cascadePileNum;
  private boolean isGameStart;

  /**
   * The constructor takes two parameters to customize the pile number
   * for open pile and cascade pile.
   *
   * @param openPileNum    the open pile number integer
   * @param cascadePileNum the cascade pile number integer
   */
  public FreecellModel(int openPileNum, int cascadePileNum) {
    this.openPileNum = openPileNum;
    this.cascadePileNum = cascadePileNum;
    this.openPile = createPile(openPileNum);
    this.cascadePile = createPile(cascadePileNum);
    this.foundationPile = createPile(SUIT_SIZE);
  }

  /**
   * Helper to create and initialize the piles according to the
   * given pile number.
   *
   * @param pileNum the pile number
   * @return the 2-D array object
   */
  private ArrayList<ArrayList<Card>> createPile(int pileNum) {
    ArrayList<ArrayList<Card>> pile = new ArrayList<ArrayList<Card>>();
    for (int i = 0; i < pileNum; i++) {
      pile.add(new ArrayList<Card>());
    }
    return pile;
  }

  /**
   * The inner builder class implements the builder interface.
   */
  public static class FreecellBuilder implements FreecellOperationsBuilder {

    private static final int MIN_CASCADE_SIZE = 4;
    private static final int MIN_OPEN_SIZE = 1;
    private int openPileNum;
    private int cascadePileNum;

    /**
     * The builder default constructor. It takes 8 cascade pile as default and 4 open pile
     * as default.
     */
    public FreecellBuilder() {
      this.cascades(8);
      this.opens(4);
    }

    /**
     * This method is used to set the pile number for cascade pile. It takes a parameter
     * as the cascade number and return a builder object for cascading operation.
     *
     * @param c the pile number for cascade pile
     * @return the new FreecellOperationsBuilder object
     */
    @Override
    public FreecellOperationsBuilder cascades(int c) {
      if (c < MIN_CASCADE_SIZE) {
        throw new IllegalArgumentException(ErrorMessage.INVALID_CASCADE_PILE_NUMBER.getMessage());
      }
      this.cascadePileNum = c;
      return this;
    }

    /**
     * This method is used to set the pile number for open pile. It takes a parameter
     * as the open pile size and return a builder object for cascading operation.
     *
     * @param o the pile number for open pile
     * @return the new FreecellOperationsBuilder object
     */
    @Override
    public FreecellOperationsBuilder opens(int o) {
      if (o < MIN_OPEN_SIZE) {
        throw new IllegalArgumentException(ErrorMessage.INVALID_OPEN_PILE_NUMBER.getMessage());
      }
      this.openPileNum = o;
      return this;
    }

    /**
     * This method is used to call the constructor of the Freecell model. In this way, one can
     * use this method to build a new model.
     *
     * @param <Card> Card object
     * @return a Card object
     */
    @Override
    public <Card> FreecellOperations<Card> build() {
      return (FreecellOperations<Card>)
              new FreecellModel(this.openPileNum, this.cascadePileNum);
    }
  }

  /**
   * getDeck() method creates a new deck and returns it.
   *
   * @return Card List object
   */
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= VALUE_SIZE; i++) {
      for (Suit suit : Suit.values()) {
        deck.add(new Card(suit, i));
      }
    }
    return deck;
  }

  /**
   * startGame() method initialize the game state, it takes a deck object and a boolean to
   * decide whether the deck should be shuffled. Then it deals the card into the cascade pile.
   *
   * @param deck    the deck to be dealt
   * @param shuffle if true, shuffle the deck else deal the deck as-is
   */
  public void startGame(List<Card> deck, boolean shuffle) throws IllegalArgumentException {
    if (deck == null) {
      throw new IllegalArgumentException(ErrorMessage.NULL_DECK.getMessage());
    }
    if (deck.size() != DECK_SIZE) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_DECK_SIZE.getMessage());
    }
    HashSet<Card> set = new HashSet<Card>(deck);
    if (set.size() != deck.size()) {
      throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CARD_IN_DECK.getMessage());
    }
    for (Card card : deck) {
      if (card.getValue() <= 0 || card.getValue() > VALUE_SIZE) {
        throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_VALUE.getMessage());
      }
    }

    this.openPile = createPile(openPileNum);
    this.cascadePile = createPile(cascadePileNum);

    if (shuffle) {
      Collections.shuffle(deck);
    }
    dealCards(deck);
    isGameStart = true;
  }

  /**
   * Helper to deal cards to cascade pile.
   *
   * @param deck Card list object
   */
  private void dealCards(List<Card> deck) {
    List<Card> deckCopy = new ArrayList<>();
    deckCopy.addAll(deck);
    for (int i = 0; i < 1 + deck.size() / cascadePile.size(); i++) {
      for (int j = 0; j < cascadePile.size(); j++) {
        if (!deckCopy.isEmpty()) {
          Card toDeal = deckCopy.get(0);
          cascadePile.get(j).add(toDeal);
          deckCopy.remove(0);
        }
      }
    }
  }

  /**
   * Move() method move the card from source pile to the destination pile if it
   * is valid. If the operation is illegal or the parameter is illegal, it will
   * throw an exception.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException illegal parameter
   * @throws IllegalStateException    illegal operation
   */
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    if (isGameOver() || isPileEmpty(cascadePile) && isPileEmpty(openPile)) {
      throw new IllegalStateException(ErrorMessage.GAME_NOT_STARTED.getMessage());
    }

    if (!(pileNumber >= 0 && pileNumber <= getPile(source).size() - 1)
            || !(destPileNumber >= 0 && destPileNumber <= getPile(destination).size() - 1)) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_PILE_NUMBER.getMessage());
    }

    if (cardIndex < 0 || cardIndex > getPile(source).get(pileNumber).size() - 1) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_INDEX_FOR_MOVE.getMessage());
    }

    if (cardIndex != getPile(source).get(pileNumber).size() - 1) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_INDEX_FOR_MOVE.getMessage());
    }

    List<Card> sourPile = getPile(source).get(pileNumber);
    List<Card> destPile = getPile(destination).get(destPileNumber);
    Card sour = getPile(source).get(pileNumber).get(cardIndex);

    if (destination == PileType.OPEN) {
      if (destPile.size() == 0) {
        validMoveCard(source, destination, pileNumber, destPileNumber, cardIndex);
      } else {
        throw new IllegalStateException(ErrorMessage.INVALID_MOVE_OPERATION.getMessage());
      }
    }

    if (destination == PileType.CASCADE) {
      if (destPile.size() == 0) {
        validMoveCard(source, destination, pileNumber, destPileNumber, cardIndex);
      } else {
        Card dest = destPile.get(destPile.size() - 1);
        if (dest.hasOneGreaterValue(sour) && !dest.hasSameColor(sour)) {
          validMoveCard(source, destination, pileNumber, destPileNumber, cardIndex);
        } else {
          throw new IllegalStateException(ErrorMessage.INVALID_MOVE_OPERATION.getMessage());
        }
      }
    }

    if (destination == PileType.FOUNDATION) {
      if (destPile.size() == 0) {
        if (sour.getValue() == 1) {
          validMoveCard(source, destination, pileNumber, destPileNumber, cardIndex);
        } else {
          throw new IllegalStateException(ErrorMessage.INVALID_MOVE_OPERATION.getMessage());
        }
      } else {
        Card dest = destPile.get(destPile.size() - 1);
        if (sour.hasOneGreaterValue(dest) && sour.hasSameSuit(dest)) {
          validMoveCard(source, destination, pileNumber, destPileNumber, cardIndex);
        } else {
          throw new IllegalStateException(ErrorMessage.INVALID_MOVE_OPERATION.getMessage());
        }
      }
    }
  }

  /**
   * Valid move helper.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   */
  private void validMoveCard(PileType source, PileType destination, int pileNumber,
                             int destPileNumber, int cardIndex) {
    Card toMove = getPile(source).get(pileNumber).get(cardIndex);
    getPile(source).get(pileNumber).remove(toMove);
    getPile(destination).get(destPileNumber).add(toMove);
  }

  /**
   * Private helper to get the designated pile.
   *
   * @param pile the pile parameter
   * @return the Card 2D list object
   */
  private List<ArrayList<Card>> getPile(PileType pile) {
    switch (pile) {
      case OPEN:
        return this.openPile;
      case CASCADE:
        return this.cascadePile;
      case FOUNDATION:
        return this.foundationPile;
      default:
        throw new IllegalArgumentException(ErrorMessage.INVALID_PILE_TYPE.getMessage());
    }
  }

  /**
   * Private helper to check if the pile is empty.
   *
   * @param pile the pile parameter
   * @return the boolean to decide whether the game is over
   */
  private boolean isPileEmpty(List<ArrayList<Card>> pile) {
    boolean isEmpty = true;
    for (ArrayList<Card> list : pile) {
      if (!list.isEmpty()) {
        isEmpty = false;
      }
    }
    return isEmpty;
  }

  /**
   * Check if the game is over.
   *
   * @return the boolean to decide whether the game is over
   */
  public boolean isGameOver() {
    int foundationCardNum = 0;
    for (ArrayList<Card> list : foundationPile) {
      foundationCardNum += list.size();
    }
    return foundationCardNum == DECK_SIZE;
  }

  /**
   * getGameState() method is used to get the game state.
   *
   * @return the game state String
   */
  public String getGameState() {
    if (!isGameStart) {
      return "";
    }
    StringBuilder gameState = new StringBuilder();
    //String gamestate = new String();
    gameState.append(getGameStateHelper("F", foundationPile));
    gameState.append(getGameStateHelper("O", openPile));
    gameState.append(getGameStateHelper("C", cascadePile));
    return gameState.substring(0, gameState.length() - 1);
  }

  /**
   * Private helper for the getState() method.
   *
   * @param a    input String
   * @param pile the pile object
   * @return the state String
   */
  private String getGameStateHelper(String a, List<ArrayList<Card>> pile) {
    String gameStateAdd = new String();
    for (int i = 0; i < pile.size(); i++) {
      gameStateAdd += a + (i + 1) + ":";
      if (!pile.get(i).isEmpty()) {
        for (Card card : pile.get(i)) {
          gameStateAdd += " " + card.toString() + ",";
        }
      }
      if (gameStateAdd.charAt(gameStateAdd.length() - 1) != ':') {
        gameStateAdd = gameStateAdd.substring(0, gameStateAdd.length() - 1);
      }
      gameStateAdd += "\n";
    }
    return gameStateAdd;
  }

  /**
   * getBuilder() method is used to get the builder.
   *
   * @return the builder object
   */
  public static FreecellOperationsBuilder getBuilder() {
    return new FreecellBuilder();
  }
}
