package freecell.model;

import java.util.ArrayList;
import java.util.List;

import freecell.utils.Card;

/**
 * The class represents the multi move model of the freecell card game.
 */
public class FreecellMultiMoveModel extends FreecellModel implements FreecellOperations<Card> {
  /**
   * The constructor takes two parameters to customize the pile number for open pile and cascade
   * pile.
   *
   * @param openPileNum    the open pile number integer
   * @param cascadePileNum the cascade pile number integer
   */
  public FreecellMultiMoveModel(int openPileNum, int cascadePileNum) {
    super(openPileNum, cascadePileNum);
  }

  /**
   * The inner builder of FreecellMultiMoveModel class implements the builder interface.
   */
  public static class FreecellMultiMoveBuilder extends FreecellModel.FreecellBuilder {

    @Override
    public <Card> FreecellOperations<Card> build() {
      return (FreecellOperations<Card>)
              new FreecellMultiMoveModel(this.openPileNum, this.cascadePileNum);
    }
  }

  /**
   * getBuilder() method is used to get the builder.
   *
   * @return the builder object
   */
  public static FreecellOperationsBuilder getBuilder() {
    return new FreecellMultiMoveBuilder();
  }

  /**
   * The method overrides the method in FreecellModel.
   *
   * @param cardIndex    the input cardIndex.
   * @param sourPileSize the size of the source pile of cards.
   * @return true if the cardIndex is valid.
   */
  @Override
  protected boolean isCardIndexValid(int cardIndex, int sourPileSize) {
    if (sourPileSize == 0) {
      return false;
    }
    return cardIndex >= 0 && cardIndex <= sourPileSize - 1;
  }

  /**
   * The method overrides the method in FreecellModel.
   *
   * @param sourPile  the source pile of cards.
   * @param cardIndex the input cardIndex.
   * @param sour      the card of the cardIndex in the sourPile.
   * @param destPile  the destination pile of cards.
   * @return true if  the move to Cascade is valid, false if invalid.
   */
  @Override
  protected boolean checkMoveToCascade(List<Card> sourPile,
                                       int cardIndex, Card sour, List<Card> destPile) {
    List<Card> cardList = new ArrayList<>();
    if (destPile.size() != 0) { // Move to not empty destPile.
      Card dest = destPile.get(destPile.size() - 1);
      cardList.add(dest);
    }
    for (int i = cardIndex; i < sourPile.size(); i++) {
      cardList.add(sourPile.get(i));
    }
    for (int i = 0; i < cardList.size() - 1; i++) {
      if (cardList.get(i).hasSameColor(cardList.get(i + 1))
              || !cardList.get(i).hasOneGreaterValue(cardList.get(i + 1))) {
        return false;
      }
    }
    int emptyOpenNum = countEmpNum(openPile);
    int emptyCasNum = countEmpNum(cascadePile);
    double limit = (emptyOpenNum + 1) * Math.pow(2, emptyCasNum);
    return sourPile.size() - cardIndex <= limit;
  }

  /**
   * The method counts the number of null piles in a pile. The method is used in
   * CheckMoveToCascade.
   *
   * @param pile the pile to be counted.
   * @return number of null piles.
   */
  private int countEmpNum(List<ArrayList<Card>> pile) {
    int countEmpNum = 0;
    for (int i = 0; i < pile.size(); i++) {
      if (pile.get(i).size() == 0) {
        countEmpNum++;
      }
    }
    return countEmpNum;
  }

  /**
   * Valid move helper.
   *
   * @param sourPile  the source pile
   * @param destPile  the destination pile
   * @param cardIndex the card index
   */
  @Override
  protected void validMoveCard(List<Card> sourPile, List<Card> destPile, int cardIndex) {

    List<Card> cardList = new ArrayList<>();
    for (int i = cardIndex; i < sourPile.size(); i++) {
      cardList.add(sourPile.get(i));
    }
    int size = sourPile.size();
    for (int i = cardIndex; i < size; i++) {
      sourPile.remove(sourPile.size() - 1);
    }
    for (int i = 0; i < cardList.size(); i++) {
      destPile.add(cardList.get(i));
    }
  }
}


