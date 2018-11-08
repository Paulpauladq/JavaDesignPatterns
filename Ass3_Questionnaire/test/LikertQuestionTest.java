import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test all the possible circumstances for
 * LikertQuestion class.
 */
public class LikertQuestionTest {

  private AbstractQuestion likertQuestionTest;

  /**
   * Set up and initialization.
   */
  @Before
  public void setUp() {
    String textBody = "C is a text body of Likert Question!";
    likertQuestionTest = new LikertQuestion(textBody);
  }


  /**
   * Test initialization for the LikertQuestion.
   */
  @Test
  public void testLikertQuestionInitialization() {
    assertEquals(likertQuestionTest.getTextBody(), "C is a text body of Likert Question!");
  }

  /**
   * Test getEvaluation() when answer is valid.
   */
  @Test
  public void testLikertValidInputAnswer() {
    assertEquals(likertQuestionTest.getEvaluation("1"), "Correct");
  }

  /**
   * Test getEvaluation() when answer is invalid.
   */
  @Test
  public void testLikertInvalidInputAnswer() {
    assertEquals(likertQuestionTest.getEvaluation("6"), "Incorrect");
  }

  /**
   * Test getEvaluation() when answer is invalid.
   */
  @Test
  public void testLikertInvalidInputAnswer2() {
    assertEquals(likertQuestionTest.getEvaluation("@"), "Incorrect");
  }

  /**
   * Test getEvaluation() when answer is invalid.
   */
  @Test
  public void testLikertInvalidInputAnswerLengthWrong() {
    assertEquals(likertQuestionTest.getEvaluation("2 323"), "Incorrect");
  }

}