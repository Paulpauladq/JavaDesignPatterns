import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test all the possible circumstances for
 * YesNoQuestion class.
 */
public class YesNoQuestionTest {

  private AbstractQuestion yesNoQuestionTest;

  /**
   * Set up and initialization.
   */
  @Before
  public void setUp() {
    String textBody = "A is a text body of YesNo question!";
    String correctAnswer = "Yes";
    yesNoQuestionTest = new YesNoQuestion(textBody, correctAnswer);
  }

  /**
   * test if the initialization is valid.
   */
  @Test
  public void testValidInitialization() {
    assertEquals(yesNoQuestionTest.getTextBody(), "A is a text body of YesNo question!");
    assertEquals(yesNoQuestionTest.getCorrectAnswer(), "Yes");
  }

  /**
   * Test if the IllegalArgumentException is thrown properly when the correct
   * Answer is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswer() {
    String textBody = "A is a text body of YesNo question!";
    String correctAnswer = "Yep";
    yesNoQuestionTest = new YesNoQuestion(textBody, correctAnswer);
  }

  /**
   * Test if getEvaluation() methods returns Correct when the input equals correct
   * answer.
   */
  @Test
  public void testGetEvaluationCorrect() {
    String eva = yesNoQuestionTest.getEvaluation("Yes");
    assertEquals(eva, "Correct");
  }

  /**
   * Test if getEvaluation() methods returns Incorrect when the input doesn't
   * equal correct answer.
   */
  @Test
  public void testGetEvaluationIncorrect() {
    String eva = yesNoQuestionTest.getEvaluation("No");
    assertEquals(eva, "Incorrect");
  }

  /**
   * Test if getEvaluation() methods returns Incorrect when the input answer
   * is invalid.
   */
  @Test
  public void testGetEvaluationInvalidInput() {
    String eva = yesNoQuestionTest.getEvaluation("hahaha");
    assertEquals(eva, "Incorrect");
  }

}