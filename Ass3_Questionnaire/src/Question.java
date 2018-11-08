/**
 * This is an interface of all the question types.
 * It extends Comparable interface to allow comparison
 * between different Question classes. Each question,
 * irrespective of type, has the following common aspects:
 * 1. It has the text of the question itself.
 * 2. It allows a way to enter an answer as a String and get the
 * evaluation result of the answer.
 * The string it returns is either “Correct” or “Incorrect”.
 * It provides 3 method signatures -- getEvaluation(), getTextBody()
 * and getCorrectAnswer().
 */
public interface Question extends Comparable<Question> {

  //enumerate the question type and set their priority
  public enum TypeOfQuestion {
    YESNOQUESTION("YesNoQuestion", 1),
    MULTICHOICEQUESTION("MultiChoiceQuestion", 3),
    LIKERTQUESTION("LikertQuestion", 2),
    MULTIANSWERQUESTION("MultiAnswerQuestion", 4);

    String question;
    int priority;

    TypeOfQuestion(String question, int priority) {
      this.question = question;
      this.priority = priority;
    }
  }

  /**
   * This method allows a way to enter an answer as a String
   * and get the evaluation result of the answer. The String
   * it returns is either "Correct" or "Incorrect".
   *
   * @param inputAnswer the input String
   * @return the evaluation String
   */
  public String getEvaluation(String inputAnswer);

  /**
   * This method is to get the text body.
   *
   * @return the text String
   */
  public String getTextBody();

  /**
   * This method is to get the correct answer.
   *
   * @return the correct answer String
   */
  public String getCorrectAnswer();

  /**
   * This method is used to get the question type.
   * @return one of the TypeOfQuestion enumeration
   */
  public TypeOfQuestion getQuestionType();
}
