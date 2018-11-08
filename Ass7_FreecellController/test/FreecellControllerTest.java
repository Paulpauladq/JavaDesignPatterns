import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import freecell.controller.ControllerErrMess;
import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.FreecellOperations;
import freecell.utils.Card;

import static org.junit.Assert.assertEquals;

/**
 * The testing class for FreecellController object.
 */
public class FreecellControllerTest {

  private IFreecellController<Card> controllerTest;
  private Reader stringReader;
  private StringBuffer out;
  private StringBuilder log;
  private FreecellOperations<Card> mockModel;
  private List<Card> deck;
  private FreecellOperations<Card> mockModelOver;

  /**
   * Initialize and set up variables.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    out = new StringBuffer();
    String mockState = "MOCK GAME STATE";
    mockModelOver = new MockModelOver(log, mockState);
    mockModel = new MockModel(log, mockState);
    deck = new ArrayList<>();
  }

  /**
   * Test playGame() from cascade pile to foundation pile.
   */
  @Test
  public void testPlayGameRegularInputCasToFou() {
    stringReader = new StringReader("C1 2 F1 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, false);
    assertEquals("Start game with original deck!\n"
            + "Source pile:CASCADE0\n"
            + "Card Index:1\n"
            + "Destination pile:FOUNDATION0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from cascade pile to open pile.
   */
  @Test
  public void testPlayGameRegularInputCasToOpen() {
    stringReader = new StringReader("C10 2 O3 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, false);
    assertEquals("Start game with original deck!\n"
            + "Source pile:CASCADE9\n"
            + "Card Index:1\n"
            + "Destination pile:OPEN2\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from cascade pile to cascade pile.
   */
  @Test
  public void testPlayGameRegularInputCasToCas() {
    stringReader = new StringReader("C99 20 C322 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, false);
    assertEquals("Start game with original deck!\n"
            + "Source pile:CASCADE98\n"
            + "Card Index:19\n"
            + "Destination pile:CASCADE321\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from open pile to open pile.
   */
  @Test
  public void testPlayGameRegularInputOpeToOpe() {
    stringReader = new StringReader("O9 3 O31 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:OPEN8\n"
            + "Card Index:2\n"
            + "Destination pile:OPEN30\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from open pile to cascade pile.
   */
  @Test
  public void testPlayGameRegularInputOpeToCas() {
    stringReader = new StringReader("O6 3 C1 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:OPEN5\n"
            + "Card Index:2\n"
            + "Destination pile:CASCADE0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from open pile to foundation pile.
   */
  @Test
  public void testPlayGameRegularInputOpeToFoundation() {
    stringReader = new StringReader("O6 3 F1 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:OPEN5\n"
            + "Card Index:2\n"
            + "Destination pile:FOUNDATION0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from foundation pile to open pile.
   */
  @Test
  public void testPlayGameRegularInputFouToOpe() {
    stringReader = new StringReader("F62 3 O1 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:FOUNDATION61\n"
            + "Card Index:2\n"
            + "Destination pile:OPEN0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from foundation pile to cascade pile.
   */
  @Test
  public void testPlayGameRegularInputFouToCas() {
    stringReader = new StringReader("F62 3 C12 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:FOUNDATION61\n"
            + "Card Index:2\n"
            + "Destination pile:CASCADE11\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() from foundation pile to foundation pile.
   */
  @Test
  public void testPlayGameRegularInputFouToFou() {
    stringReader = new StringReader("F62 32 F12 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);

    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:FOUNDATION61\n"
            + "Card Index:31\n"
            + "Destination pile:FOUNDATION11\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() move operations multiple times.
   */
  @Test
  public void testPlayGameRegularInputMultipleTimes() {
    stringReader = new StringReader("F62 32 F12 O1 2 O3 F1 33 F5 C2 4 O1 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);

    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:FOUNDATION61\n"
            + "Card Index:31\n"
            + "Destination pile:FOUNDATION11\n"
            + "Source pile:OPEN0\n"
            + "Card Index:1\n"
            + "Destination pile:OPEN2\n"
            + "Source pile:FOUNDATION0\n"
            + "Card Index:32\n"
            + "Destination pile:FOUNDATION4\n"
            + "Source pile:CASCADE1\n"
            + "Card Index:3\n"
            + "Destination pile:OPEN0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() method using invalid input.
   */
  @Test
  public void testPlayGameInvalidInputSourPile() {
    stringReader = new StringReader("W12 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() method using invalid input.
   */
  @Test
  public void testPlayGameInvalidInputSourPileMultiTime() {
    stringReader = new StringReader("W12 FOUJ CHINA 12 NB SOS O12 3 F1 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:OPEN11\n"
            + "Card Index:2\n"
            + "Destination pile:FOUNDATION0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() method using invalid input.
   */
  @Test
  public void testPlayGameInvalidInputCardIndMultiTime() {
    stringReader = new StringReader("O43 @# 1-1 BUBU NUM !@#$%^&* 22 O1 Q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:OPEN42\n"
            + "Card Index:21\n"
            + "Destination pile:OPEN0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() method using invalid input.
   */
  @Test
  public void testPlayGameInvalidInputDestPileMultiTime() {
    stringReader = new StringReader("C32 11 ji *&* +_+ -_- !@#$%^&* ((M(m9 R2O1 F1 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:CASCADE31\n"
            + "Card Index:10\n"
            + "Destination pile:FOUNDATION0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() method using quit at the beginning.
   */
  @Test
  public void testPlayGameEmpty() {
    stringReader = new StringReader("q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }


  /**
   * Test playGame() with consecutive empty String.
   */
  @Test
  public void testPlayGameEmpty2() {
    stringReader = new StringReader("C1     2 F1 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModel, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:CASCADE0\n"
            + "Card Index:1\n"
            + "Destination pile:FOUNDATION0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Game quit prematurely.\n", out.toString());
  }

  /**
   * Test playGame() with consecutive empty String.
   */
  @Test
  public void testPlayGameEmptyStringReader() {
    stringReader = new StringReader("            ");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n", out.toString());
  }

  /**
   * Test playGame() when deck is null.
   */
  @Test
  public void testPlayGameDeckIsNull() {
    deck = null;
    stringReader = new StringReader("C1 2 C1 q");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.DECK_OR_MODEL_IS_NULL.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("", log.toString());
    assertEquals("", out.toString());
  }

  /**
   * Test playGame() when model is null.
   */
  @Test
  public void testPlayGameModelIsNull() {
    mockModel = null;
    stringReader = new StringReader("C1 2 C1 q");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.DECK_OR_MODEL_IS_NULL.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("", log.toString());
    assertEquals("", out.toString());
  }

  /**
   * Test playGame() when Readable object is null.
   */
  @Test
  public void testPlayGameReadableObjIsNull() {
    stringReader = null;
    try {
      controllerTest = new FreecellController(stringReader, out);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.APPENDABLE_OR_READABLE_IS_NULL.getMessage(), e.getMessage());
    }

    assertEquals("", log.toString());
    assertEquals("", out.toString());
  }

  /**
   * Test playGame() when Appendable object is null.
   */
  @Test
  public void testPlayGameAppendableObjIsNull() {
    out = null;
    try {
      controllerTest = new FreecellController(stringReader, out);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.APPENDABLE_OR_READABLE_IS_NULL.getMessage(), e.getMessage());
    }
    assertEquals("", log.toString());
  }

  /**
   * Test playGame() quitting wrongly.
   */
  @Test
  public void testPlayGameQuitNotRight1() {
    stringReader = new StringReader("C1 2 C1 w");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n"
            + "Source pile:CASCADE0\n"
            + "Card Index:1\n"
            + "Destination pile:CASCADE0\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Invalid source pile number! Please enter again:\n", out.toString());
  }

  /**
   * Test playGame() quitting wrongly.
   */
  @Test
  public void testPlayGameQuitNotRight2() {
    stringReader = new StringReader("C1 w");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Invalid card index! Please enter again:\n", out.toString());
  }

  /**
   * Test playGame() quitting wrongly.
   */
  @Test
  public void testPlayGameQuitNotRight3() {
    stringReader = new StringReader("C1 2 w");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n"
            + "Enter a valid source pile number:\n"
            + "Enter a valid card index:\n"
            + "Enter a valid destination number:\n"
            + "Invalid destination pile number! Please enter again:\n", out.toString());
  }

  /**
   * Test playGame() with empty String input.
   */
  @Test
  public void testPlayGameEmptyReader() {
    stringReader = new StringReader("");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, true);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n", out.toString());
  }


  /**
   * Test playGame() when game is over.
   */
  @Test
  public void testPlayGameGameOver() {
    stringReader = new StringReader("C1 2 C1 q");
    controllerTest = new FreecellController(stringReader, out);
    controllerTest.playGame(deck, mockModelOver, true);
    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with shuffled deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n" +
            "Game over.\n", out.toString());
  }

  /**
   * Test playGame() when append fails.
   */
  @Test
  public void testPlayGameAppendFails() {
    stringReader = new StringReader("");
    controllerTest = new FreecellController(stringReader, out);
    try {
      controllerTest.playGame(deck, mockModel, false);
    } catch (IllegalArgumentException e) {
      assertEquals(ControllerErrMess.SCANNER_DOESNT_HAS_NEXT.getMessage(), e.getMessage());
    }

    System.out.println(log.toString());
    System.out.println(out.toString());
    assertEquals("Start game with original deck!\n", log.toString());
    assertEquals("MOCK GAME STATE\n", out.toString());
  }
}