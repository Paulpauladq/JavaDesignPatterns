package freecell.utils;

/**
 * This class is used to represent the card class.
 */
public class Card {

  private Suit suit;
  private int value;
  private SuitColor color;

  /**
   * This constructor is used to represent the card object. It takes two parameters -- suit
   * and value of the card to build a card. If the value is invalid, it will throw a exception.
   *
   * @param suit  the card suit enum
   * @param value the card value integer
   */
  public Card(Suit suit, int value) {
    this.suit = suit;
    if (value < 1 || value > 13) {
      throw new IllegalArgumentException(ModelErrMess.INVALID_CARD_VALUE.getMessage());
    }
    this.value = value;
    switch (suit) {
      case diamonds:
      case hearts:
        color = SuitColor.red;
        break;
      case clubs:
      case spades:
        color = SuitColor.black;
        break;
      default:
    }
  }

  /**
   * This method is used to get the value of the card.
   *
   * @return the value enum of the card
   */
  public int getValue() {
    return this.value;
  }

  /**
   * This method is used to get the suit of the card.
   *
   * @return the suit enum of the card
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * This method is used to get the color of the card.
   *
   * @return the color enum of the card
   */
  public SuitColor getColor() {
    return this.color;
  }

  /**
   * This method is used to print out the card information in a symbol&number manner.
   *
   * @return the card String
   */
  public String toString() {
    String valueSymbol = new String();
    String suitSymbol = new String();
    if (value >= 2 && value <= 10) {
      valueSymbol = Integer.toString(value);
    } else {
      switch (this.value) {
        case 1:
          valueSymbol = "A";
          break;
        case 11:
          valueSymbol = "J";
          break;
        case 12:
          valueSymbol = "Q";
          break;
        case 13:
          valueSymbol = "K";
          break;
        default:
      }
    }
    switch (this.suit) {
      case diamonds:
        suitSymbol = "♦";
        break;
      case clubs:
        suitSymbol = "♣";
        break;
      case hearts:
        suitSymbol = "♥";
        break;
      case spades:
        suitSymbol = "♠";
        break;
      default:
    }
    return valueSymbol + suitSymbol;
  }

  /**
   * This method is used to check whether two Card object has the same color.
   *
   * @param card the passing Card class
   * @return the boolean to check whether two Card has the same color
   */
  public boolean hasSameColor(Card card) {
    return this.color.equals(card.color);
  }

  /**
   * This method is used to check whether two Card object has the same suit.
   *
   * @param card the passing Card class
   * @return the boolean to check whether two Card has the same suit
   */
  public boolean hasSameSuit(Card card) {
    return this.suit.equals(card.suit);
  }

  /**
   * This method is used to check whether the this card has one greater value than the
   * passing card.
   *
   * @param card the passing Card class
   * @return the boolean to check whether two Card has the same suit
   */
  public boolean hasOneGreaterValue(Card card) {
    return this.getValue() - card.getValue() == 1;
  }
}