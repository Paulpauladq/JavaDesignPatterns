import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test all the possible circumstances for
 * MultiAnswerQuestion class.
 */
public class MultiAnswerQuestionTest {

  private AbstractQuestion mulAnsQuestionTest;

  /**
   * Test the initialization for the MultiAnswerQuestionClass.
   */
  @Test
  public void testMulAnsQuestionInitialization() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "3 1 2 4";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getTextBody(),
            "D is a text body of MultiAnswer question!");
    assertEquals(mulAnsQuestionTest.getCorrectAnswer(),
            "3 1 2 4");
  }

  /**
   * Test when the options are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOptionNumberLow() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1";
    String[] options = new String[]{"Earth", "Moon"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the options are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOptionNumberHigh() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune", "Moon"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the options are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOptionDuplicate() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Venus"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerSingleHigh() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "9";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerMultiHigh() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 9";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerDuplicate() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 2";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerLengthHigh() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4 5 6 7 8 9";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the correct answer are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCorrectAnswerInvalidCharacter() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "-1 -2 &";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
  }

  /**
   * Test when the input is correct.
   */
  @Test
  public void testGetEvaluationRightSequence() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 4"), "Correct");
  }

  /**
   * Test when the input is correct.
   */
  @Test
  public void testGetEvaluationChangeSequence() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "4 2 3 1";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 4"), "Correct");
  }

  /**
   * Test when the input is correct.
   */
  @Test
  public void testGetEvaluationChangeFormat() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1432";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 4"), "Correct");
  }

  /**
   * Test when the input is correct.
   */
  @Test
  public void testCorrectAnswerChangeFormat() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 4 3 2";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1234"), "Correct");
  }

  /**
   * Test when the input is incorrect.
   */
  @Test
  public void testIncorrectAnswerMoreOptionSelected() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 4 5"), "Incorrect");
  }

  /**
   * Test when the input is incorrect.
   */
  @Test
  public void testIncorrectAnswerLessOptionSelected() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3"), "Incorrect");
  }

  /**
   * Test when the input is incorrect.
   */
  @Test
  public void testIncorrectAnswerWrongOptionSelected() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 5"), "Incorrect");
  }

  /**
   * Test when the input is incorrect.
   */
  @Test
  public void testIncorrectAnswerInvalidOptionSelected() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 9"), "Incorrect");
  }

  /**
   * Test when the input is incorrect.
   */
  @Test
  public void testIncorrectAnswerInvalidOptionSelected2() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3 4";
    String[] options =
            new String[]{"Mercury", "Venus", "Earth", "Mars",
                         "Jupiter", "Saturn", "Uranus", "Neptune"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getEvaluation("1 2 3 @"), "Incorrect");
  }

  /**
   * Test when the correct answer is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCorrectAnswerEmpty() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getTextBody(), "D is a text body of MultiAnswer question!");
    assertEquals(mulAnsQuestionTest.getCorrectAnswer(), "1 2 3");
  }

  /**
   * Test when the input answer is empty.
   */
  @Test
  public void testInputEmpty() {
    String textBody = "D is a text body of MultiAnswer question!";
    String correctAnswer = "1 2 3";
    String[] options = new String[]{"Earth", "Moon", "Sun", "Mars"};
    mulAnsQuestionTest = new MultiAnswerQuestion(textBody, correctAnswer, options);
    assertEquals(mulAnsQuestionTest.getTextBody(), "D is a text body of MultiAnswer question!");
    assertEquals(mulAnsQuestionTest.getEvaluation(""), "Incorrect");
  }

}