import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;
import freecell.utils.Card;
import freecell.utils.ModelErrMess;
import freecell.utils.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is used to test the multi move model. It tests its
 * method thoroughly and covers every possible scenarios of the methods.
 */
public class FreecellMultiMoveModelTest {

  private FreecellOperations<Card> multimoveTest;
  private FreecellOperations<Card> modelTest;
  private FreecellOperations<Card> modelTest2;
  private FreecellOperations<Card> modelTest3;
  private FreecellOperations<Card> modelTest4;
  private FreecellOperations<Card> modelTest5;
  private String standardDeckString;
  private String newDeckString;

  /**
   * Initialize the models and create decks.
   */
  @Before
  public void setUp() {
    multimoveTest = FreecellMultiMoveModel.getBuilder().build();
    modelTest = FreecellMultiMoveModel.getBuilder().opens(4).cascades(60).build();
    modelTest2 = new FreecellMultiMoveModel(4, 8);
    modelTest3 = new FreecellMultiMoveModel(1, 32);
    modelTest4 = new FreecellMultiMoveModel(4, 8);
    modelTest5 = new FreecellMultiMoveModel(4, 8);
    List<Card> emptyDeck = new ArrayList<Card>();
    List<Card> standardDeck = modelTest.getDeck();
    List<Card> newDeck = new ArrayList<>();
    for (int i = 13; i >= 1; i--) {
      for (Suit suit : Suit.values()) {
        newDeck.add(new Card(suit, i));
      }
    }
    multimoveTest.startGame(newDeck, false);
    modelTest.startGame(standardDeck, false);
    modelTest2.startGame(newDeck, false);
    modelTest3.startGame(newDeck, false);
    modelTest4.startGame(standardDeck, false);
    emptyDeck = new ArrayList<Card>();
    //modelTest.startGame(standardDeck, false);
    newDeckString = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
            + "C2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
            + "C3: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n"
            + "C4: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n"
            + "C5: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
            + "C6: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
            + "C7: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
            + "C8: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠";
  }

  /**
   * test for getGameState().
   */
  @Test
  public void getGameStateTest() {
    //getGameState returns an empty string if the game has not started.
    assertEquals("", modelTest5.getGameState());
    assertEquals(newDeckString, multimoveTest.getGameState());
  }

  /**
   * test for multi move.
   */
  @Test
  public void multiMoveTest() {
    //System.out.println(newDeck);

    //System.out.println(multimoveTest.getGameState());
    //Valid move.
    try {
      multimoveTest.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 4);
      multimoveTest.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 3);
      multimoveTest.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 4);
      multimoveTest.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 3);
      multimoveTest.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 4);
    } catch (Exception e) {
      fail(" Exception should have been thrown! ");
    }
    //System.out.println(multimoveTest.getGameState());
    //Condition 3. Invalid move: Exceed moving number of Cards.
    try {
      multimoveTest.move(PileType.CASCADE, 4, 3, PileType.CASCADE, 3);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move operation!", e.getMessage());
    }
    //Cascade move to foundation.
    multimoveTest.move(PileType.CASCADE, 4, 8, PileType.FOUNDATION, 2);
    multimoveTest.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 3);
    //System.out.println(multimoveTest.getGameState());
    multimoveTest.move(PileType.CASCADE, 4, 7, PileType.FOUNDATION, 3);
    //Foundation move to Cascade.
    multimoveTest.move(PileType.FOUNDATION, 3, 1, PileType.CASCADE, 4);
    //System.out.println(multimoveTest.getGameState());
    //System.out.println(multimoveTest.getGameState());
    //Invalid move: Cascade to Open, 2 cards. Move more than one card to open.
    try {
      multimoveTest.move(PileType.CASCADE, 4, 6, PileType.OPEN, 3);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move operation!", e.getMessage());
    }
    //Invalid move: Cascade to Open, 2 cards. Move more than one card to Foundation.
    //System.out.println(multimoveTest.getGameState());
    multimoveTest.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 2);
    try {
      multimoveTest.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move operation!", e.getMessage());
    }
    //System.out.println(multimoveTest.getGameState());
    //System.out.println(multimoveTest.getGameState());
    multimoveTest.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 0);
    multimoveTest.move(PileType.CASCADE, 0, 5, PileType.CASCADE, 5);
    multimoveTest.move(PileType.CASCADE, 1, 6, PileType.OPEN, 2);
    multimoveTest.move(PileType.CASCADE, 6, 5, PileType.CASCADE, 1);
    multimoveTest.move(PileType.OPEN, 2, 0, PileType.CASCADE, 1);
    multimoveTest.move(PileType.CASCADE, 1, 5, PileType.CASCADE, 6);

    //Move more than 1 card to open file.
    try {
      multimoveTest.move(PileType.CASCADE, 4, 6, PileType.OPEN, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    //Move more than 1 card to foundation file.
    //System.out.println(multimoveTest.getGameState());
    try {
      multimoveTest.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    System.out.println(multimoveTest.getGameState());
    // Invalid move to foundation pile: the card suit is invalid.
    multimoveTest.move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    try {
      multimoveTest.move(PileType.CASCADE, 2, 5, PileType.FOUNDATION, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    // Invalid move: Move more than 1 card to foundation file begining with A.
    //System.out.println(modelTest4.getGameState());
    try {
      modelTest4.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    // Invalid move: move one card to foundation file, the card value is invalid.
    try {
      multimoveTest.move(PileType.CASCADE, 6, 5, PileType.FOUNDATION, 2);
      fail(" Exception should have been thrown! ");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    //System.out.println(modelTest.getGameState());
    modelTest.move(PileType.CASCADE, 51, 0, PileType.CASCADE, 52);
    //System.out.println(modelTest.getGameState());
    modelTest.move(PileType.CASCADE, 46, 0, PileType.CASCADE, 53);
    System.out.println(modelTest.getGameState());

  }

  /**
   * Condition 3, test for Card Number and move to empty Cascade.
   */
  @Test
  public void testForCardMoveNumberAndMoveToEmptyCascade() {
    System.out.println(modelTest3.getGameState());
    modelTest3.move(PileType.CASCADE, 18, 1, PileType.CASCADE, 13);
    try {
      modelTest3.move(PileType.CASCADE, 13, 1, PileType.CASCADE, 10);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTest3.move(PileType.CASCADE, 18, 0, PileType.OPEN, 0);
    modelTest3.move(PileType.CASCADE, 10, 2, PileType.CASCADE, 8);
    // Condition3, empty cascade pile.
    // All Open piles are not empty, with one empty cascade pile, invalid move for exceeding number.
    try {
      modelTest3.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 7);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTest3.move(PileType.OPEN, 0, 0, PileType.CASCADE, 13);
    System.out.println(modelTest3.getGameState());

    //Move more than 1 card to empty cascade.
    modelTest3.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 18);
    //System.out.println(modelTest3.getGameState());
    modelTest3.move(PileType.CASCADE, 14, 1, PileType.OPEN, 0);

    modelTest3.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 6);
    System.out.println(modelTest3.getGameState());
    //CONDITION3, all the open piles are not empty, and cascades are not empty.
    //In such case, Only 1 card can be moved.
    try {
      modelTest3.move(PileType.CASCADE, 6, 1, PileType.CASCADE, 2);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * test for valid cardIndex.
   */
  @Test
  public void MultiMoveofValidCardIndex() {
    //System.out.println(multimoveTest.getGameState());
    //Valid move: Moving more than one card.
    multimoveTest.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 4);
    multimoveTest.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 3);
    multimoveTest.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 4);
    multimoveTest.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 3);
    multimoveTest.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 4);
    System.out.println(multimoveTest.getGameState());
    //Invalid move: Empty source pile.
    try {
      multimoveTest.move(PileType.OPEN, 2, 0, PileType.CASCADE, 4);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
    }
    //Invalid move: cardIndex < 0.
    try {
      multimoveTest.move(PileType.CASCADE, 2, -1, PileType.CASCADE, 4);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
    }
    //Invalid move: cardIndex > max cardIndex.
    try {
      multimoveTest.move(PileType.CASCADE, 2, 20000, PileType.CASCADE, 4);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
    }
  }

  /**
   * Condition 1 and Conditon 2.
   */
  @Test
  public void MultiMoveTestForCascade() {
    multimoveTest.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 4);
    multimoveTest.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 3);
    multimoveTest.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 4);
    multimoveTest.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 6);
    System.out.println(multimoveTest.getGameState());
    // Condition1:
    // Invalid multi move for Cascade, the cards to be moved cannot build a build.
    try {
      multimoveTest.move(PileType.CASCADE, 6, 4, PileType.CASCADE, 3);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    // Condition 2:
    // Invalid multi move for Cascade, the first card cannot build a build with cards to be moved.
    try {
      multimoveTest.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 3);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    //Valid move: the first card of destinatino file and the cards to be moved can build a build.
    multimoveTest.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 3);

  }

  /**
   * Test invalid move with invalid source pile number.
   */
  @Test
  public void testInvalidMoveInvalidSourcePileNumber() {

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTest.move(sourcePile, -1, 6, destPile, 0);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
        try {
          modelTest.move(sourcePile, 100, 6, destPile, 0);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
      }
    }
  }

  /**
   * Test invalid move with invalid dest pile number.
   */
  @Test
  public void testInvalidMoveInvalidDestPileNumber() {

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTest.move(sourcePile, 0, 6, destPile, -1);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
        try {
          modelTest.move(sourcePile, 0, 6, destPile, 100);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_PILE_NUMBER.getMessage(), e.getMessage());
        }
      }
    }
  }

  /**
   * Test invalid card index for move.
   */
  @Test
  public void testInvalidCardIndexForMove() {

    for (PileType sourcePile : PileType.values()) {
      for (PileType destPile : PileType.values()) {
        try {
          modelTest.move(sourcePile, 0, -1, destPile, 1);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
        }
        try {
          modelTest.move(sourcePile, 0, 100, destPile, 1);
        } catch (IllegalArgumentException e) {
          assertEquals(ModelErrMess.INVALID_CARD_INDEX_FOR_MOVE.getMessage(), e.getMessage());
        }
      }
    }

    for (int i = 0; i < 6; i++) {
      try {
        modelTest2.move(PileType.CASCADE, 0, 5 - i, PileType.OPEN, 0);
      } catch (IllegalArgumentException e) {
        assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
      }
    }
  }

  /**
   * Test invalid move() to open pile.
   */
  @Test
  public void testInvalidMoveOperationToOpen() {
    System.out.println(modelTest.getGameState());
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    try {
      modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.OPEN, 0, 0, PileType.OPEN, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTest.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    try {
      modelTest.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to open pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToOpen() {
    System.out.println(modelTest.getGameState());
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
  }

  /**
   * Test invalid move() to cascade pile.
   */
  @Test
  public void testInvalidMoveOperationToCascade() {
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);

    try {
      modelTest.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to cascade pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToCascade() {
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 52);
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 4, 0, PileType.OPEN, 1);

    modelTest.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 6);
    modelTest.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 53);
    modelTest.move(PileType.OPEN, 0, 0, PileType.CASCADE, 54);
    modelTest.move(PileType.OPEN, 1, 0, PileType.CASCADE, 9);
  }

  /**
   * Test invalid move() to foundation pile.
   */
  @Test
  public void testInvalidMoveOperationToFoundation() {
    try {
      modelTest.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    modelTest.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.OPEN, 1);
    System.out.println(modelTest.getGameState());
    modelTest.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    try {
      modelTest.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
    try {
      modelTest.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(ModelErrMess.INVALID_MOVE_OPERATION.getMessage(), e.getMessage());
    }
  }

  /**
   * Test valid move() to foundation pile.
   */
  @Test(expected = Test.None.class)
  public void testValidMoveOperationToFoundation() {
    modelTest.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 52);
    modelTest.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);

    modelTest.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    modelTest.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 5, 0, PileType.OPEN, 1);

    modelTest.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 2);
    modelTest.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    modelTest.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
  }
}