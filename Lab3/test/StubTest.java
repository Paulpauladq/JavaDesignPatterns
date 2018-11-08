import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import grades.Gradebook;
import grades.StudentRecord;

import static org.junit.Assert.assertEquals;

public class StubTest {

  Gradebook records;
  List<Double> weights;
  List<Double> finalScores;
  List<String> letterGrades;
  List<String> firstNames, lastNames;
  private final int NumAssignments = 4;
  List<String> letters;
  List<Double> thresholds;

  /**
   * Set up the new GradeBook object, add the data from the
   * excel sheet into relevant ArrayList.
   */
  @Before
  public void setup() {
    letters = Arrays.asList(new String[]{"F", "D-", "D", "D+", "C-", "C", "C+",
            "B-", "B", "B+", "A-", "A"});
    thresholds = Arrays.asList(new Double[]{60.0, 63.0, 66.0, 70.0, 73.0, 76.0,
            80.0, 83.0, 86.0, 90.0, 93.0, 100.0});
    records = new Gradebook(letters, thresholds);
    finalScores = new ArrayList<Double>();
    letterGrades = new ArrayList<String>();
    firstNames = new ArrayList<String>();
    lastNames = new ArrayList<String>();
    int i = 0;
    while (i < input.length) {
      String fName = input[i];
      String lName = input[i + 1];
      double[] points = new double[NumAssignments];
      for (int j = 0; j < NumAssignments; j++) {
        points[j] = 100 * Double.parseDouble(input[i + 2 + j]);
      }

      finalScores.add(Double.parseDouble(input[i + 2 + NumAssignments]));
      letterGrades.add(input[i + 2 + NumAssignments + 1]);
      firstNames.add(fName);
      lastNames.add(lName);

      i = i + 4 + NumAssignments;
      records.addStudent(new StudentRecord(fName, lName, points));
    }

    weights = new ArrayList<Double>();
    weights.add(20.0);
    weights.add(30.0);
    weights.add(40.0);
    weights.add(10.0);
  }

  /**
   * Test the getFinalScores method.
   */
  @Test
  public void testIndividualGrades() {
    List<Double> finals = records.getFinalScores(weights);
    for (int i = 0; i < finalScores.size(); i++) {
      assertEquals(finalScores.get(i), finals.get(i), 0.001);
    }
  }

  /**
   * Test the countLetterGrade method with the specific grade letter.
   */
  @Test
  public void testCountLetterGrade() {
    String letterGradeTest = "A-";
    int countGrade = 0;
    for (int i = 0; i < firstNames.size(); i++) {
      if (letterGrades.get(i).equals(letterGradeTest)) {
        countGrade++;
      }
    }
    assertEquals(records.countLetterGrade(letterGradeTest, weights), countGrade);
  }

  /**
   * Test the getStuduentNames() method.
   */
  @Test
  public void testGetStudentNames() {
    List<String> names = records.getStudentNames();
    for (int i = 0; i < firstNames.size(); i++) {
      assertEquals(firstNames.get(i) + " " + lastNames.get(i), names.get(i));
    }
  }

  /**
   * Test the averageScoreForName() method for specific test name.
   */
  @Test
  public void testGetAverageScoreForName() {
    String testName = "Rohan";
    double gradesByName = records.averageScoreForName(testName, weights);
    double sumByName = 0.0;
    int count = 0;
    for (int i = 0; i < firstNames.size(); i++) {
      if (firstNames.get(i).equals(testName)) {
        sumByName += finalScores.get(i);
        count++;
      }
    }
    double averageByName = sumByName / count;
    assertEquals(averageByName, gradesByName, 0.0001);
  }

  /**
   * Test the countAboveAverge() method.
   */
  @Test
  public void testCountAboveAverage() {
    double sumTotal = 0.0;
    long countTotal = 0;
    long countAbove = 0;
    for (int i = 0; i < firstNames.size(); i++) {
      sumTotal += finalScores.get(i);
      countTotal++;
    }
    double averageScore = sumTotal / countTotal;

    for (int i = 0; i < finalScores.size(); i++) {
      if (finalScores.get(i) > averageScore) {
        countAbove++;
      }
    }
    assertEquals(records.countAboveAverage(weights), countAbove);
  }

  // Data from the Excel file, to be used for testing

  String[] input = {"Amit"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Clark"
          , "Freifeld"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Aniruddha"
          , "Tapas"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Aditya"
          , "Sathyanarayan"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Ritika"
          , "Nair"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Rohan"
          , "Chitnis"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Amit"
          , "Qu"
          , "0.9"
          , "0.9"
          , "0.8"
          , "0.85"
          , "85.5"
          , "B"
          , "Amit"
          , "Tang"
          , "0.95"
          , "0.9"
          , "0.89"
          , "0.98"
          , "91.4"
          , "A-"
          , "Aditya"
          , "Liao"
          , "0.9"
          , "0.6"
          , "0.8"
          , "0.8"
          , "76"
          , "C+"
          , "Clark"
          , "Li"
          , "0.5"
          , "0.5"
          , "0.7"
          , "0.7"
          , "60"
          , "D-"
          , "Clark"
          , "Xie"
          , "0.9"
          , "0.9"
          , "0.9"
          , "0.9"
          , "90"
          , "A-"
          , "Rohan"
          , "Zhang"
          , "0.8"
          , "0.6"
          , "0.5"
          , "0.9"
          , "63"
          , "D"
          , "Rohan"
          , "Wan"
          , "0.75"
          , "0.9"
          , "0.9"
          , "0.9"
          , "87"
          , "B+"
          , "Ritika"
          , "Jin"
          , "0.9"
          , "0.9"
          , "0.9"
          , "1"
          , "91"
          , "A-"
          , "Aniruddha"
          , "Qiao"
          , "0.8"
          , "0.8"
          , "0.8"
          , "0.8"
          , "80"
          , "B-"
          , "Ritika"
          , "Qi"
          , "0.7"
          , "0"
          , "0"
          , "0.6"
          , "20"
          , "F"
  };

}