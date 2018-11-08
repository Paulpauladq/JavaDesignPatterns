import java.util.HashSet;
import java.util.Set;

/**
 * The MultiAnswerQuestion extends MultiChoiceQuestion class.
 * this offers several options, but there are multiple correct answers.
 * This type of question can be created by passing the text of the question,
 * the correct answer and the options. A question may have at least 3 options,
 * but no more than 8. Both the correct answer and the user’s answer are entered
 * as the option numbers in a string. For example, “1”, “1 3”, “4 1”, etc. Option
 * numbers start at 1. An answer is correct if and only if it contains all the
 * correct options and none of the incorrect ones.
 */
public class MultiAnswerQuestion extends MultiChoiceQuestion {

  private static final String CORRECTSTRING = new String("Correct");
  private static final String INCORRECTSTRING = new String("Incorrect");

  /**
   * The constructor takes 3 parameters as its parent class does.
   *
   * @param textBody      the text body String.
   * @param correctAnswer the correct answer String.
   * @param options       the options String array.
   */
  public MultiAnswerQuestion(String textBody, String correctAnswer, String[] options) {
    super(textBody, correctAnswer, options);
    questionType = TypeOfQuestion.MULTIANSWERQUESTION;
  }

  /**
   * This method is used to check if the correct answer.
   * @param answer answer String
   * @return the boolean value
   */
  public boolean isValidCorrectAnswer(String answer) {
    Set<Character> storeSet = new HashSet<Character>();
    //remove all the white space of the correct answer
    String trimAnswer = answer.replaceAll("\\s", "");
    //length isn't right
    if (trimAnswer.length() > optionMap.size() || trimAnswer.length() == 0) {
      return false;
    }
    //the answer is beyond the valid range
    for (int i = 0; i < trimAnswer.length(); i++) {
      if (!optionMap.containsKey(Character.getNumericValue(trimAnswer.charAt(i)))) {
        return false;
      }
      //duplicate correct answers
      if (storeSet.contains(trimAnswer.charAt(i))) {
        return false;
      }
      storeSet.add(trimAnswer.charAt(i));
    }
    return true;
  }

  /**
   * Overrides the getEvaluation() method.
   *
   * @param inputAnswer the input answer String
   * @return the evaluation String
   */
  public String getEvaluation(String inputAnswer) {
    String correctTrim =
            this.getCorrectAnswer().replaceAll("\\s", "");
    String inputTrim =
            inputAnswer.replaceAll("\\s", "");
    //check the string length equality
    if (correctTrim.length() != inputTrim.length()) {
      return INCORRECTSTRING;
    }

    //use count to store and find out if they have the same element
    int[] letterCounter = new int[8];
    for (int i = 0; i < correctTrim.length(); i++) {
      if (inputTrim.charAt(i) - '1' < 0
              || inputTrim.charAt(i) - '1' > correctTrim.length()) {
        return INCORRECTSTRING;
      }
      letterCounter[correctTrim.charAt(i) - '1']++;
      letterCounter[inputTrim.charAt(i) - '1']--;
    }

    for (int count : letterCounter) {
      if (count != 0) {
        return INCORRECTSTRING;
      }
    }
    return CORRECTSTRING;
  }
}