import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;

  @Before
  public void setUp() {
    john = new Person("John", "Doe", 1945);
  }

  @Test
  public void testFirst() {
    assertEquals(john.getFirstName(), "John");

  }

  @Test
  public void testSecond() {
    assertEquals(john.getLastName(), "Doe");
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(john.getYearOfBirth(), 1945);
  }

}