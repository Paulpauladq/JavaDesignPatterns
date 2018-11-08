import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AbstractQuestionTest {

  private AbstractQuestion question;

  /**
   * Test the question priority when creating a questionnaire.
   */
  @Test
  public void testQuestionSort() {
    String[] options1 = new String[]{"A", "B", "C", "D"};
    AbstractQuestion yesNo1 = new YesNoQuestion("Aas is a yes no!", "Yes");
    AbstractQuestion yesNo2 = new YesNoQuestion("Aab is a yes no!", "Yes");
    AbstractQuestion yesNo3 = new YesNoQuestion("Acb is a yes no!", "No");
    AbstractQuestion yesNo4 = new YesNoQuestion("AEw is a yes no!", "Yes");
    AbstractQuestion multiChoice1 =
            new MultiChoiceQuestion("CAAs is a multi choice!", "1", options1);
    AbstractQuestion multiChoice2 =
            new MultiChoiceQuestion("CAA is a multi choice!", "2", options1);
    AbstractQuestion multiChoice3 =
            new MultiChoiceQuestion("CCA is a multi choice!", "1", options1);
    AbstractQuestion multiChoice4 =
            new MultiChoiceQuestion("CBs is a multi choice!", "2", options1);
    AbstractQuestion likert1 = new LikertQuestion("DAA is a likert!");
    AbstractQuestion likert2 = new LikertQuestion("Drb is a likert!");
    AbstractQuestion likert3 = new LikertQuestion("Dbc is a likert!");
    AbstractQuestion likert4 = new LikertQuestion("DCs is a likert!");
    AbstractQuestion mulAns1 =
            new MultiAnswerQuestion("CAA is a multi answer!", "1 2 3 4", options1);
    AbstractQuestion mulAns2 =
            new MultiAnswerQuestion("arb is a multi answer!", "1 2 3 4", options1);

    Question[] questionnaire = {yesNo1, yesNo2, yesNo3, yesNo4, multiChoice1,
                                multiChoice2, multiChoice3, multiChoice4, likert1, likert2,
                                likert3, likert4, mulAns1, mulAns2};
    Arrays.sort(questionnaire);


    List<String> questionArr = new ArrayList<String>();

    //create the correct answer list for assertEqual comparison
    List<String> correctQuestionArr = new ArrayList<String>();

    correctQuestionArr.add("AEw is a yes no!");
    correctQuestionArr.add("Aab is a yes no!");
    correctQuestionArr.add("Aas is a yes no!");
    correctQuestionArr.add("Acb is a yes no!");
    correctQuestionArr.add("DAA is a likert!");
    correctQuestionArr.add("DCs is a likert!");
    correctQuestionArr.add("Dbc is a likert!");
    correctQuestionArr.add("Drb is a likert!");
    correctQuestionArr.add("CAA is a multi choice!");
    correctQuestionArr.add("CAAs is a multi choice!");
    correctQuestionArr.add("CBs is a multi choice!");
    correctQuestionArr.add("CCA is a multi choice!");
    correctQuestionArr.add("CAA is a multi answer!");
    correctQuestionArr.add("arb is a multi answer!");

    for (int i = 0; i < questionnaire.length; i++) {
      questionArr.add(questionnaire[i].getTextBody());
      assertEquals(questionnaire[i].getTextBody(), correctQuestionArr.get(i));
    }
  }
}