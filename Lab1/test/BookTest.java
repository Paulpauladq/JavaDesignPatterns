import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book pride;

  @Before
  public void setUp() {
    Person jane = new Person("Jane", "Austen", 1775);
    pride = new Book("prideAndPrejudice", jane, 100);
  }

  @Test
  public void testTitle() {
    assertEquals(pride.getTitle(), "prideAndPrejudice");
  }

  @Test
  public void testAuthorFirst() {
    assertEquals(pride.getAuthor().getFirstName(), "Jane");
  }

  @Test
  public void testAuthorLast() {
    assertEquals(pride.getAuthor().getLastName(), "Austen");
  }

  @Test
  public void testAuthorYear() {
    assertEquals(pride.getAuthor().getYearOfBirth(), 1775);
  }

  @Test
  public void testPrice() {
    assertEquals(pride.getPrice(), 100);
  }
}