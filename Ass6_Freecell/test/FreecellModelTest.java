import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;
import freecell.utils.Card;
import freecell.utils.ErrorMessage;
import freecell.utils.Suit;

import static org.junit.Assert.assertEquals;

/**
 * Model test class are built for testing the FreecellModel class.
 * It covers every method and relevant scenarios of the model class.
 */
public class FreecellModelTest {

  private FreecellOperations<Card> modelTestDef;
  private FreecellOperations<Card> modelTestCus;
  private FreecellOperations<Card> modelTest;
  private List<Card> deck;
  private List<Card> emptyDeck;
  private String initStateNoShuffle;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    modelTestDef = FreecellModel.getBuilder().build();
    modelTestCus = FreecellModel.getBuilder().opens(4).cascades(60).build();
    modelTest = new FreecellModel(40, 8);
    deck = modelTest.getDeck();
    emptyDeck = new ArrayList<Card>();
    modelTest.startGame(deck, false);
    initStateNoShuffle = "F1:\n" + "F2:\n" + "F3:\n" + "F4:\n"
            + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n"
            + "C1: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n"
            + "C2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n"
            + "C3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n"
            + "C4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n"
            + "C5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n"
            + "C6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n"
            + "C7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n"
            + "C8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";
  }

  /**
   * Test valid constructor.
   */
  @Test(expected = Test.None.class)
  public void testValidConstructor() {
    modelTest = new FreecellModel(40, 8);
  }

  /**
   * Test valid default constructor.
   */
  @Test(expected = Test.None.class)
  public void testValidDefaultBuilder() {
    modelTest = FreecellModel.getBuilder().build();
  }

  /**
   * Test valid customized constructor.
   */
  @Test(expected = Test.None.class)
  public void testValidCustomedBuilder() {
    modelTest = FreecellModel.getBuilder().cascades(4).build();
    modelTest = FreecellModel.getBuilder().opens(8).build();
    modelTest = FreecellModel.getBuilder().opens(80).cascades(40).build();
    modelTest = FreecellModel.getBuilder().opens(40).cascades(80).build();
  }

  /**
   * Test valid customized constructor.
   */
  @Test(expected = Test.None.class)
  public void testValidCustomedBuilderMinimumPile() {
    modelTest = FreecellModel.getBuilder().opens(1).cascades(4).build();
    modelTest = FreecellModel.getBuilder().opens(1).build();
    modelTest = FreecellModel.getBuilder().cascades(4).build();
  }

  /**
   * Test invalid constructor takes negative number for open pile.
   */
  @Test
  public void testInvalidBuilderInvalidOpenPileNumberSingle() {
    try {
      modelTest = FreecellModel.getBuilder().opens(-1).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_OPEN_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid constructor takes negative number for cascade pile.
   */
  @Test
  public void testInvalidBuilderInvalidCascadePileNumberSingle() {
    try {
      modelTest = FreecellModel.getBuilder().cascades(-1).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_CASCADE_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid constructor takes zero for open pile.
   */
  @Test
  public void testInvalidBuilderInvalidOpenPileNumberZero() {
    try {
      modelTest = FreecellModel.getBuilder().opens(0).cascades(4).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_OPEN_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid constructor takes negative number for open pile.
   */
  @Test
  public void testInvalidBuilderInvalidOpenPileNumberNegative() {
    try {
      modelTest = FreecellModel.getBuilder().opens(-1).cascades(4).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_OPEN_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid constructor takes zero for cascade pile.
   */
  @Test
  public void testInvalidBuilderInvalidCascadePileNumberZero() {
    try {
      modelTest = FreecellModel.getBuilder().opens(2).cascades(0).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_CASCADE_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid constructor takes negative number for cascade pile.
   */
  @Test
  public void testInvalidBuilderInvalidCascadePileNumberNegative() {
    try {
      modelTest = FreecellModel.getBuilder().opens(2).cascades(-43).build();
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_CASCADE_PILE_NUMBER.getMessage(), e.getMessage());
    }
  }

  /**
   * Test getDeck() method.
   */
  @Test
  public void testGetDeck() {
    List<Card> expectedDeck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (Suit suit : Suit.values()) {
        expectedDeck.add(new Card(suit, i));
      }
    }
    Iterator<Card> expectedIter = expectedDeck.iterator();
    Iterator<Card> deckIter = deck.iterator();
    while (expectedIter.hasNext() && deckIter.hasNext()) {
      assertEquals(expectedIter.next().toString(), deckIter.next().toString());
    }
  }

  /**
   * Test start Game with null deck.
   */
  @Test
  public void testInvalidStartGameNullDeck() {
    try {
      modelTestDef.startGame(null, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.NULL_DECK.getMessage(), e.getMessage());
    }
    try {
      modelTestDef.startGame(null, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.NULL_DECK.getMessage(), e.getMessage());
    }
  }

  /**
   * Test start Game with empty deck and deck with wrong size.
   */
  @Test
  public void testInvalidStartGameDeckWrongSize() {
    try {
      modelTestDef.startGame(emptyDeck, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_DECK_SIZE.getMessage(), e.getMessage());
    }
    try {
      modelTestDef.startGame(emptyDeck, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_DECK_SIZE.getMessage(), e.getMessage());
    }
    try {
      deck = new ArrayList<>();
      for (int i = 1; i <= 10; i++) {
        for (Suit suit : Suit.values()) {
          emptyDeck.add(new Card(suit, i));
        }
      }
      modelTestDef.startGame(emptyDeck, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_DECK_SIZE.getMessage(), e.getMessage());
    }
    try {
      emptyDeck = new ArrayList<>();
      for (int i = 1; i <= 10; i++) {
        for (Suit suit : Suit.values()) {
          emptyDeck.add(new Card(suit, i));
        }
      }
      modelTestDef.startGame(emptyDeck, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_DECK_SIZE.getMessage(), e.getMessage());
    }
  }

  /**
   * Test Invalid startGame() with duplicate deck.
   */
  @Test
  public void testInvalidStartGameDuplicateDeck() {
    try {
      for (int i = 1; i <= 52; i++) {
        emptyDeck.add(new Card(Suit.clubs, 1));
      }
      modelTestDef.startGame(emptyDeck, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.DUPLICATE_CARD_IN_DECK.getMessage(), e.getMessage());
    }
    try {
      emptyDeck = new ArrayList<>();
      for (int i = 1; i <= 52; i++) {
        emptyDeck.add(new Card(Suit.clubs, 1));
      }
      modelTestDef.startGame(emptyDeck, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.DUPLICATE_CARD_IN_DECK.getMessage(), e.getMessage());
    }
  }

  /**
   * Test Invalid startGame() with invalid card value.
   */
  @Test
  public void testInvalidStartGameInvalidCardValue() {
    try {
      for (int i = 1; i <= 15; i++) {
        deck.add(new Card(Suit.clubs, i));
      }
      modelTestDef.startGame(deck, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ErrorMessage.INVALID_CARD_VALUE.getMessage(), e.getMessage());
    }
  }

  /**
   * Test startGame() without shuffle.
   */
  @Test
  public void testValidStartGameNoShuffle() {
    assertEquals("", modelTestDef.getGameState());
    modelTestDef.startGame(deck, false);
    assertEquals(initStateNoShuffle, modelTestDef.getGameState());
  }

  /**
   * Test startGame() with shuffle.
   */
  @Test(expected = Test.None.class)
  public void testValidStartGameShuffle() {
    assertEquals("", modelTestDef.getGameState());
    modelTestDef.startGame(deck, true);
  }

  /**
   * Test startGame() without shuffle.
   */
  @Test
  public void testStartGameInTheMiddleOfAGameNoShuffle() {
    String expectedState;
    assertEquals("", modelTestDef.getGameState());
    modelTestDef.startGame(deck, false);
    assertEquals(initStateNoShuffle, modelTestDef.getGameState());
    for (int i = 0; i < 4; i++) {
      modelTestDef.move(PileType.CASCADE, 0, 6 - i, PileType.OPEN, i);
    }
    expectedState = "F1:\n" + "F2:\n" + "F3:\n" + "F4:\n"
            + "O1: K♦\n" + "O2: J♦\n" + "O3: 9♦\n" + "O4: 7♦\n"
            + "C1: A♦, 3♦, 5♦\n"
            + "C2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n"
            + "C3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n"
            + "C4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n"
            + "C5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n"
            + "C6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n"
            + "C7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n"
            + "C8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";
    assertEquals(expectedState, modelTestDef.getGameState());
    modelTestDef.startGame(deck, false);
    assertEquals(initStateNoShuffle, modelTestDef.getGameState());
  }

  /**
   * Test invalid move before game started.
   */
  @Test
  public void testInvalidMoveBeforeGameStarted() {
    try {
      modelTestCus.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.GAME_NOT_STARTED.getMessage(), e.getMessage());
    }
  }

  /**
   * Test invalid move with invalid source pile number.
   */
  @Test
  public void testInvalidMoveInvalidSourcePileNumber() {
    modelTestCus.startGame(deck, false);

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTestCus.move(sourcePile, -1, 6, destPile, 0);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
        try {
          modelTestCus.move(sourcePile, 100, 6, destPile, 0);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
      }
    }
  }

  /**
   * Test invalid move with invalid dest pile number.
   */
  @Test
  public void testInvalidMoveInvalidDestPileNumber() {
    modelTestCus.startGame(deck, false);

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTestCus.move(sourcePile, 0, 6, destPile, -1);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
        try {
          modelTestCus.move(sourcePile, 0, 6, destPile, 100);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
      }
    }
  }

  /**
   * Test invalid card index for move.
   */
  @Test
  public void testInvalidCardIndexForMove() {
    modelTestCus.startGame(deck, false);

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTestCus.move(sourcePile, 0, -1, destPile, 1);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
        }
        try {
          modelTestCus.move(sourcePile, 0, 100, destPile, 1);
        } catch (IllegalArgumentException e) {
          assertEquals(ErrorMessage.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
        }
      }
    }

    modelTestDef.startGame(deck, false);
    for (int i = 0; i < 6; i++) {
      try {
        modelTestDef.move(PileType.CASCADE, 0, 5 - i, PileType.OPEN, 0);
      } catch (IllegalArgumentException e) {
        assertEquals(ErrorMessage.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
      }
    }
  }

  /**
   * Test invalid move() to open pile.
   */
  @Test
  public void testInvalidMoveOperationToOpen() {
    modelTestCus.startGame(deck, false);
    modelTestCus.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    try {
      modelTestCus.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.OPEN, 0, 0, PileType.OPEN, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    try {
      modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to open pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToOpen() {
    modelTestCus.startGame(deck, false);
    modelTestCus.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTestCus.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 2);
  }

  /**
   * Test invalid move() to cascade pile.
   */
  @Test
  public void testInvalidMoveOperationToCascade() {
    modelTestCus.startGame(deck, false);
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    modelTestCus.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);

    try {
      modelTestCus.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 3);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to cascade pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToCascade() {
    modelTestCus.startGame(deck, false);

    modelTestCus.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 52);
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    modelTestCus.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    modelTestCus.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.CASCADE, 4, 0, PileType.OPEN, 1);

    modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 6);
    modelTestCus.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 53);
    modelTestCus.move(PileType.OPEN, 0, 0, PileType.CASCADE, 54);
    modelTestCus.move(PileType.OPEN, 1, 0, PileType.CASCADE, 9);
  }

  /**
   * Test invalid move() to foundation pile.
   */
  @Test
  public void testInvalidMoveOperationToFoundation() {
    modelTestCus.startGame(deck, false);
    try {
      modelTestCus.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTestCus.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTestCus.move(PileType.CASCADE, 5, 0, PileType.OPEN, 1);
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    try {
      modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 3);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTestCus.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to foundation pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToFoundation() {
    modelTestCus.startGame(deck, false);

    modelTestCus.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 52);
    modelTestCus.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);

    modelTestCus.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    modelTestCus.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.CASCADE, 5, 0, PileType.OPEN, 1);

    modelTestCus.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 2);
    modelTestCus.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    modelTestCus.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
  }

  /**
   * Test isGameOver() not started with shuffle.
   */
  @Test
  public void testGameOverNoStartedShuffle() {
    modelTestDef.startGame(deck, true);
    assertEquals(false, modelTestDef.isGameOver());
  }

  /**
   * Test isGameOver() not started without shuffle.
   */
  @Test
  public void testGameOverNoStartedNoShuffle() {
    modelTestDef.startGame(deck, false);
    assertEquals(false, modelTestDef.isGameOver());
  }

  /**
   * Test isGameOver() without shuffle and invalid move after game over.
   */
  @Test
  public void testGameOverTrueNoShuffle() {
    modelTestCus.startGame(deck, false);
    assertEquals(false, modelTestCus.isGameOver());

    for (int i = 0; i <= 3; i++) {
      for (int j = 0; j <= 12; j++) {
        modelTestCus.move(PileType.CASCADE, 4 * j + i, 0, PileType.FOUNDATION, i);
      }
    }
    assertEquals(true, modelTestCus.isGameOver());
    //test move after game over
    try {
      modelTestCus.move(PileType.FOUNDATION, 0, 12, PileType.OPEN, 0);
    } catch (IllegalStateException e) {
      assertEquals(ErrorMessage.GAME_NOT_STARTED.getMessage(), e.getMessage());
    }
  }

  /**
   * Test getGameState().
   */
  @Test
  public void testGetGameState() {
    assertEquals("", modelTestDef.getGameState());
    modelTestDef.startGame(deck, false);
    assertEquals(initStateNoShuffle, modelTestDef.getGameState());

    modelTestDef.startGame(deck, true);
  }
}

