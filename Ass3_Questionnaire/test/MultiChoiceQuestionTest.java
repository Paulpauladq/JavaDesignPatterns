import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test all the possible circumstances for
 * MultiChoiceQuestion class.
 */
public class MultiChoiceQuestionTest {

  private AbstractQuestion mulChoQuestionTest;

  /**
   * Test Initialization of the MultiChoiceQuestion class.
   */
  @Test
  public void testMultiChoiceInitialization() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "1";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getTextBody(), "B is a text body of MultiChoice question!");
    assertEquals(mulChoQuestionTest.getCorrectAnswer(), "1");
  }

  /**
   * Test when the options are invalid -- less than the lower bound.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOptionNumberLow() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "1";
    String[] options = new String[]{"Earth", "Moon"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the options are invalid -- higher than the upper bound.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOptionNumberHigh() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "1";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth",
                         "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Moon"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerHigh() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "9";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerChar() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "&";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test getEvaluation() when the correct are correct.
   */
  @Test
  public void testCorrectAnswer() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getEvaluation("4"), "Correct");
  }

  /**
   * Test getEvaluation() when the correct are incorrect.
   */
  @Test
  public void testIncorrectAnswer() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getEvaluation("5"), "Incorrect");
  }

  /**
   * Test when the options have some duplicate ones.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateOptions() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Earth", "Uranus", "Neptune"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer's length is more than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCorrectAnswerLengthLonger() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "12 3";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getTextBody(), "B is a text body of MultiChoice question!");
    assertEquals(mulChoQuestionTest.getCorrectAnswer(), "1");
  }

  /**
   * Test when the correct answer is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCorrectAnswerEmpty() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getTextBody(), "B is a text body of MultiChoice question!");
    assertEquals(mulChoQuestionTest.getCorrectAnswer(), "1");
  }

  /**
   * Test when the input answer is empty.
   */
  @Test
  public void testInputEmpty() {
    String textBody = "B is a text body of MultiChoice question!";
    String correctAnswer = "1";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulChoQuestionTest = new MultiChoiceQuestion(textBody, correctAnswer, options);
    assertEquals(mulChoQuestionTest.getTextBody(), "B is a text body of MultiChoice question!");
    assertEquals(mulChoQuestionTest.getEvaluation(""), "Incorrect");
  }
}