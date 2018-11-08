import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import freecell.utils.Card;
import freecell.utils.Suit;
import freecell.utils.SuitColor;

import static org.junit.Assert.assertEquals;

/**
 * Card test class are built for testing the card class.
 * It covers every method and relevant scenarios of the card class.
 */
public class CardTest {

  private Card cardTest1;
  private Card cardTest2;

  ArrayList<Card> deck = new ArrayList<>();

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    cardTest1 = new Card(Suit.clubs, 13);
    cardTest2 = new Card(Suit.diamonds, 12);
    for (int i = 1; i < 14; i++) {
      for (Suit suit : Suit.values()) {
        deck.add(new Card(suit, i));
      }
    }
  }

  /**
   * Test toString() method.
   */
  @Test
  public void cardTestDeckToString() {

    Card card1 = new Card(Suit.clubs, 1);
    assertEquals("A♣", card1.toString());
    Card card2 = new Card(Suit.spades, 1);
    assertEquals("A♠", card2.toString());
    Card card3 = new Card(Suit.diamonds, 1);
    assertEquals("A♦", card3.toString());
    Card card4 = new Card(Suit.hearts, 1);
    assertEquals("A♥", card4.toString());

    Card card5 = new Card(Suit.clubs, 10);
    assertEquals("10♣", card5.toString());
    Card card6 = new Card(Suit.spades, 10);
    assertEquals("10♠", card6.toString());
    Card card7 = new Card(Suit.diamonds, 10);
    assertEquals("10♦", card7.toString());
    Card card8 = new Card(Suit.hearts, 10);
    assertEquals("10♥", card8.toString());

    Card card9 = new Card(Suit.clubs, 13);
    assertEquals("K♣", card9.toString());
    Card card10 = new Card(Suit.spades, 13);
    assertEquals("K♠", card10.toString());
    Card card11 = new Card(Suit.diamonds, 13);
    assertEquals("K♦", card11.toString());
    Card card12 = new Card(Suit.hearts, 13);
    assertEquals("K♥", card12.toString());
  }

  /**
   * Test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cardTestInvalidConstructorInvalidValueHigher() {
    cardTest1 = new Card(Suit.clubs, 14);
  }

  /**
   * Test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cardTestInvalidConstructorInvalidValueLower() {
    cardTest1 = new Card(Suit.clubs, 0);
  }

  /**
   * Test valid constructor.
   */
  @Test
  public void cardTestValidConstructor() {
    cardTest2 = new Card(Suit.spades, 1);
    assertEquals(1, cardTest2.getValue());
    assertEquals(Suit.spades, cardTest2.getSuit());
    assertEquals(SuitColor.black, cardTest2.getColor());
    assertEquals("A♠", cardTest2.toString());
  }

  /**
   * Test hasSameColor() method.
   */
  @Test
  public void cardTestHasSameColor() {
    cardTest2 = new Card(Suit.spades, 10);
    Card cardTest3 = new Card(Suit.spades, 10);
    assertEquals(true, cardTest1.hasSameColor(cardTest1));
    assertEquals(true, cardTest1.hasSameColor(cardTest2));
    assertEquals(true, cardTest2.hasSameColor(cardTest1));
    assertEquals(true, cardTest1.hasSameColor(cardTest3));
    assertEquals(true, cardTest2.hasSameColor(cardTest3));
  }

  /**
   * Test hasSameSuit() method.
   */
  @Test
  public void cardTestHasSameSuit() {
    assertEquals(false, cardTest1.hasSameSuit(cardTest2));
    cardTest2 = new Card(Suit.clubs, 10);
    assertEquals(true, cardTest1.hasSameSuit(cardTest2));
  }

  /**
   * Test hasOneGreaterValue() method.
   */
  @Test
  public void cardTestHasOneGreaterValue() {
    assertEquals(true, cardTest1.hasOneGreaterValue(cardTest2));
    assertEquals(false, cardTest1.hasOneGreaterValue(cardTest1));
    assertEquals(false, cardTest2.hasOneGreaterValue(cardTest1));
  }
}