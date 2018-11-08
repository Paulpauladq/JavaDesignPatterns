package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import freecell.utils.Card;
import freecell.utils.ModelErrMess;
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

  protected List<ArrayList<Card>> openPile;
  protected List<ArrayList<Card>> cascadePile;
  protected List<ArrayList<Card>> foundationPile;
  protected int openPileNum;
  protected int cascadePileNum;
  protected boolean isGameStart;

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
    protected static final int DEF_CASCADE_SIZE = 8;
    protected static final int DEF_OPEN_SIZE = 4;
    protected static final int MIN_CASCADE_SIZE = 4;
    protected static final int MIN_OPEN_SIZE = 1;
    protected int openPileNum;
    protected int cascadePileNum;

    /**
     * The builder default constructor. It takes 8 cascade pile as default and 4 open pile
     * as default.
     */
    public FreecellBuilder() {
      this.cascades(DEF_CASCADE_SIZE);
      this.opens(DEF_OPEN_SIZE);
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
        throw new IllegalArgumentException(ModelErrMess.INVALID_CASCADE_PILE_NUMBER.getMessage());
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
        throw new IllegalArgumentException(ModelErrMess.INVALID_OPEN_PILE_NUMBER.getMessage());
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
      throw new IllegalArgumentException(ModelErrMess.NULL_DECK.getMessage());
    }
    if (deck.size() != DECK_SIZE) {
      throw new IllegalArgumentException(ModelErrMess.INVALID_DECK_SIZE.getMessage());
    }
    HashSet<Card> set = new HashSet<Card>(deck);
    if (set.size() != deck.size()) {
      throw new IllegalArgumentException(ModelErrMess.DUPLICATE_CARD_IN_DECK.getMessage());
    }
    for (Card card : deck) {
      if (card.getValue() <= 0 || card.getValue() > VALUE_SIZE) {
        throw new IllegalArgumentException(ModelErrMess.INVALID_CARD_VALUE.getMessage());
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
      throw new IllegalStateException(ModelErrMess.GAME_NOT_STARTED.getMessage());
    }

    if (!(pileNumber >= 0 && pileNumber <= getPile(source).size() - 1)
            || !(destPileNumber >= 0 && destPileNumber <= getPile(destination).size() - 1)) {
      throw new IllegalArgumentException(ModelErrMess.INVALID_PILE_NUMBER.getMessage());
    }

    List<Card> sourPile = getPile(source).get(pileNumber);
    List<Card> destPile = getPile(destination).get(destPileNumber);
    //isCardIndexValid(int cardIndex, int sourPileSize)
    if (!isCardIndexValid(cardIndex, sourPile.size())) {
      throw new IllegalArgumentException(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage());
    }
    Card sour = getPile(source).get(pileNumber).get(cardIndex);
    int cardToMove = sourPile.size() - cardIndex;

    if (destination == PileType.OPEN) {
      if (checkMoveToOpen(destPile.size(), cardToMove)) {
        validMoveCard(sourPile, destPile, cardIndex);
      } else {
        throw new IllegalArgumentException(ModelErrMess.INVALID_MOVE_OPERATION.getMessage());
      }
    }

    if (destination == PileType.CASCADE) {
      if (checkMoveToCascade(sourPile, cardIndex, sour, destPile)) {
        validMoveCard(sourPile, destPile, cardIndex);
      } else {
        throw new IllegalArgumentException(ModelErrMess.INVALID_MOVE_OPERATION.getMessage());
      }
    }

    if (destination == PileType.FOUNDATION) {
      if (checkMoveToFoundation(destPile, cardToMove, sour)) {
        validMoveCard(sourPile, destPile, cardIndex);
      } else {
        throw new IllegalArgumentException(ModelErrMess.INVALID_MOVE_OPERATION.getMessage());
      }
    }
  }

  /**
   * The method tests if the cardIndex is valid for source pile of cards.
   *
   * @param cardIndex    the input cardIndex.
   * @param sourPileSize the size of the source pile of cards.
   * @return true if the cardIndex is valid.
   */
  protected boolean isCardIndexValid(int cardIndex, int sourPileSize) {
    if (sourPileSize == 0) {
      return false;
    }
    return cardIndex == (sourPileSize - 1);
  }


  /**
   * The method tests if the move to open file is valid.
   *
   * @param destPileSize the size of the dest pile of cards.
   * @param cardToMove   the total card number to be moved.
   * @return true if the move to open is valid, false if invalid.
   */
  protected boolean checkMoveToOpen(int destPileSize, int cardToMove) {
    return destPileSize == 0 && cardToMove == 1;
  }

  /**
   * The method tests if the move to Cascade file is valid.
   *
   * @param sourPile  the source pile of cards.
   * @param cardIndex the input cardIndex.
   * @param sour      the card of the cardIndex in the sourPile.
   * @param destPile  the destination pile of cards.
   * @return true if  the move to Cascade is valid, false if invalid.
   */
  protected boolean checkMoveToCascade(List<Card> sourPile,
                                       int cardIndex, Card sour, List<Card> destPile) {
    if (destPile.size() == 0) {
      return true;
    }
    Card dest = destPile.get(destPile.size() - 1);
    return dest.hasOneGreaterValue(sour) && !dest.hasSameColor(sour);
  }

  /**
   * The method tests if the move to Foundation file is valid.
   *
   * @param cardToMove the total card number to be moved.
   * @param sour       the card of the cardIndex in the sourPile.
   * @param destPile   the destination pile of cards.
   * @return true if  the move to Foundation is valid, false if invalid.
   */
  protected boolean checkMoveToFoundation(List<Card> destPile, int cardToMove, Card sour) {
    if (cardToMove != 1) {  //move more than one card, false.
      return false;
    }
    if (destPile.size() == 0) { //Move only one card, destPile is empty.
      return sour.getValue() == 1;
    }
    //Move only one card, destPile is not empty.
    Card dest = destPile.get(destPile.size() - 1);
    return sour.hasOneGreaterValue(dest) && sour.hasSameSuit(dest);
  }

  /**
   * Valid move helper.
   *
   * @param sourPile  the source pile
   * @param destPile  the destination pile
   * @param cardIndex the card index
   */
  protected void validMoveCard(List<Card> sourPile, List<Card> destPile, int cardIndex) {
    Card toMove = sourPile.get(cardIndex);
    sourPile.remove(toMove);
    destPile.add(toMove);
  }

  /**
   * Private helper to get the designated pile.
   *
   * @param pile the pile parameter
   * @return the Card 2D list object
   */
  protected List<ArrayList<Card>> getPile(PileType pile) {
    switch (pile) {
      case OPEN:
        return this.openPile;
      case CASCADE:
        return this.cascadePile;
      case FOUNDATION:
        return this.foundationPile;
      default:
        throw new IllegalArgumentException(ModelErrMess.INVALID_PILE_TYPE.getMessage());
    }
  }

  /**
   * Private helper to check if the pile is empty.
   *
   * @param pile the pile parameter
   * @return the boolean to decide whether the game is over
   */
  protected boolean isPileEmpty(List<ArrayList<Card>> pile) {
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
