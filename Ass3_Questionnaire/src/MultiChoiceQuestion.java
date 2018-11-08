import java.util.HashMap;
import java.util.Map;

/**
 * MultiChoiceQuestion implements the AbstractQuestion class.
 * Multiple choice: this offers several options, only one of which is correct.
 * This type of question can be created by passing the text of the question,
 * the correct answer and the options. A question may have at least 3 options,
 * but no more than 8. An answer can be entered as one of the option numbers in
 * a string. For example, “1”, “3”, etc. Option numbers start at 1.
 */
public class MultiChoiceQuestion extends AbstractQuestion {

  private String correctAnswer;
  private String[] options;
  private static final int OPTIONSUPPERBOUND = 8;
  private static final int OPTIONSLOWERBOUUND = 3;
  private static final String CORRECTSTRING = new String("Correct");
  private static final String INCORRECTSTRING = new String("Incorrect");

  //hash map used to store the option String elements
  Map<Integer, String> optionMap = new HashMap<Integer, String>();

  /**
   * The constructor of MultiChoiceQuestion class takes three parameters --
   * textBody, correctAnswer and options String array.
   *
   * @param textBody      the textBody String
   * @param correctAnswer the correct answer String
   * @param options       the options String array
   */
  public MultiChoiceQuestion(String textBody, String correctAnswer, String[] options) {
    super(textBody);
    this.correctAnswer = correctAnswer;
    this.options = options;
    questionType = TypeOfQuestion.MULTICHOICEQUESTION;

    //check the number of options
    if (options.length < OPTIONSLOWERBOUUND || options.length > OPTIONSUPPERBOUND) {
      throw new IllegalArgumentException();
    } else {
      //put options into the hash map
      for (int i = 0; i < options.length; i++) {
        //check if options has duplicate ones
        if (optionMap.containsValue(options[i])) {
          throw new IllegalArgumentException();
        }
        optionMap.put(i + 1, options[i]);
      }
    }
    //check if the correct answer is valid
    if (!isValidCorrectAnswer(correctAnswer)) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * the helper function to check if the correct answer
   * is valid. it takes a answer String as its parameter.
   *
   * @param answer answer String
   * @return the check boolean
   */
  public boolean isValidCorrectAnswer(String answer) {
    if (answer.length() != 1) {
      return false;
    } else {
      return optionMap.containsKey(Integer.parseInt(correctAnswer));
    }
  }

  /**
   * Method implements getting the correct Answer.
   *
   * @return the correct answer String
   */
  public String getCorrectAnswer() {
    return this.correctAnswer;
  }

  /**
   * This method is used to get the options String array.
   *
   * @return the option String array
   */
  public String[] getOptions() {
    return this.options;
  }


  /**
   * Overrides the getEvaluation() method.
   *
   * @param inputAnswer the input answer String
   * @return the evaluation String
   */
  @Override
  public String getEvaluation(String inputAnswer) {
    if (inputAnswer.length() != 1) {
      return INCORRECTSTRING;
    }
    //check if input answer is valid
    else if (!optionMap.containsKey(Integer.parseInt(inputAnswer))) {
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
