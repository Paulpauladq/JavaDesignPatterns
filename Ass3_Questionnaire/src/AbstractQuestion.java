/**
 * This is an abstract class which represents the general
 * question type. It has only one variable which is the textBody
 * String. It also overrides getTextBody() method and compareTo()
 * method for Question object sorting.
 */
public abstract class AbstractQuestion implements Question {

  protected String textBody;
  protected TypeOfQuestion questionType;

  /**
   * Protected constructor of AbstractQuestion class which takes
   * a String as the parameter. In its body, it initializes this
   * field.
   */
  protected AbstractQuestion(String textBody) {
    this.textBody = textBody;
  }

  /**
   * Override the getTextBody() method.
   *
   * @return the textBody String
   */
  @Override
  public String getTextBody() {
    return this.textBody;
  }

  /**
   * This method is used to get the question type.
   *
   * @return the TypeOfQuestion
   */
  @Override
  public TypeOfQuestion getQuestionType() {
    return questionType;
  }

  /**
   * Override the compareTo function to allow Question
   * object comparison.
   *
   * @param question the Question object as the parameter
   * @return a int representing the equality
   */
  @Override
  public int compareTo(Question question) {
    String thisText = this.getTextBody();
    String otherText = question.getTextBody();
    int thisPriority = this.getQuestionType().priority;
    int otherPriority = question.getQuestionType().priority;

    if (thisPriority - otherPriority == 0) {
      return thisText.compareTo(otherText);
    } else {
      return thisPriority - otherPriority;
    }
  }

  /**
   * The abstract method for its implementation class to implement.
   * It's used to get the Evaluation String of the input answer.
   *
   * @param inputAnswer the input answer String
   * @return the evaluation String
   */
  public abstract String getEvaluation(String inputAnswer);

}
