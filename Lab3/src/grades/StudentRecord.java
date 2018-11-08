package grades;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRecord {
  private final String firstName;
  private final String lastName;
  private final List<Double> percentagePoints;

  public StudentRecord(String fName, String lName, double[] points) {
    this.firstName = fName;
    this.lastName = lName;
    this.percentagePoints =
            Arrays.stream(points).boxed().collect(Collectors.toList());
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public double getGrade(List<Double> weights) throws IllegalArgumentException {
    if (weights.size() != percentagePoints.size()) {
      throw new IllegalArgumentException("Number of weights is not equal to "
              + "number of assignments");
    }
    double score = 0;
    for (int i = 0; i < weights.size(); i++) {
      score += weights.get(i) * percentagePoints.get(i);
    }
    return score / 100;
  }
}
