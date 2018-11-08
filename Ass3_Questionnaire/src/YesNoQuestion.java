import java.util.HashSet;
import java.util.Set;

/**
 * This class are defined as a special type of class which
 * contains only two correct answers: "Yes" or "No". this
 * can be answered in one of two ways: yes or no. This type
 * of question can be created by passing the text of the
 * question and the correct answer. The only valid answer
 * to this type of question is a “Yes” or “No”.
 */
public class YesNoQuestion extends AbstractQuestion {

  //valid hashset for the valid answers
  private static final Set<String> VALIDSET;
  public String correctAnswer;

  static {
    VALIDSET = new HashSet<String>();
    VALIDSET.add("Yes");
    VALIDSET.add("No");
  }

  private static final String CORRECTSTRING = new String("Correct");
  private static final String INCORRECTSTRING = new String("Incorrect");

  /**
   * The constructor of YesNoQuestion takes two parameters -- textBody and
   * correctAnswer and initializes them in its body. It also checks the
   * validity of the correctAnswer using the HashSet.
   */
  public YesNoQuestion(String textBody, String correctAnswer) {
    super(textBody);
    questionType = TypeOfQuestion.YESNOQUESTION;
    this.correctAnswer = correctAnswer;
    if (!VALIDSET.contains(correctAnswer)) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This method is used to get the correct answer String.
   *
   * @return the correct answer String
   */
  public String getCorrectAnswer() {
    return this.correctAnswer;
  }

  /**
   * This method overrides the getEvaluation(). It checks if
   * input answer is valid, will return "Correct" if input equals
   * real answer and vice versa.
   *
   * @param inputAnswer the input answer String
   * @return the evaluation String
   */
  @Override
  public String getEvaluation(String inputAnswer) {
    //check if input answer is valid
    if (!VALIDSET.contains(inputAnswer)) {
      return INCORRECTSTRING;
    }
    //if input = correct, return true
    else if (inputAnswer.equals(this.correctAnswer)) {
      return CORRECTSTRING;
    } else {
      return INCORRECTSTRING;
    }
  }
}
