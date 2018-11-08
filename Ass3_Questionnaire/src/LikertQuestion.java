import java.util.HashMap;
import java.util.Map;

/**
 * LikertQuestion class extends AbstractQuestion class.
 * this can be answered on a fixed, 5-point Likert scale (Strongly Agree, Agree,
 * Neither Agree nor Disagree, Disagree, Strongly Disagree). This type of question
 * can be created by passing the text of the question. Since this question asks an
 * opinion, there is no “correct” answer. An answer can be entered as one of the
 * option numbers, numbered from 1 in the above order. Any valid option number is
 * a “correct” answer.
 */
public class LikertQuestion extends AbstractQuestion {
  //the scale hastset which stores the valid answer set
  private static final int LIKERTOPTIONSNUMBER = 5;
  private static final String CORRECTSTRING = new String("Correct");
  private static final String INCORRECTSTRING = new String("Incorrect");

  private static final Map<Integer, String> optionMap = new HashMap<Integer, String>();

  static {
    optionMap.put(1, "Strongly Agree");
    optionMap.put(2, "Agree");
    optionMap.put(3, "Neither Agree nor Disagree");
    optionMap.put(4, "Disagree");
    optionMap.put(5, "Strongly Disagree");
  }

  /**
   * Constructor of LikertQuestion takes one parameter -- textBody.
   *
   * @param textBody textBody String
   */
  public LikertQuestion(String textBody) {
    super(textBody);
    questionType = TypeOfQuestion.LIKERTQUESTION;
  }

  /**
   * The method used to get the correct answer.
   *
   * @return the correct answer string(null in this case)
   */
  @Override
  public String getCorrectAnswer() {
    return null;
  }

  /**
   * Overrides the getEvaluation() methods.
   *
   * @param inputAnswer the input answer String
   * @return the evaluation String
   */
  @Override
  public String getEvaluation(String inputAnswer) {
    //check length
    if (inputAnswer.length() != 1) {
      return INCORRECTSTRING;
    }
    //check if input is invalid
    else if (inputAnswer.charAt(0) - '1' < 0
            || inputAnswer.charAt(0) - '1' > LIKERTOPTIONSNUMBER) {
      return INCORRECTSTRING;
    }
    //check if input answer is incorrect
    else if (!optionMap.containsKey(Integer.parseInt(inputAnswer))) {
      return INCORRECTSTRING;
    } else {
      return CORRECTSTRING;
    }
  }
}
