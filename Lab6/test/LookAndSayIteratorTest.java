import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;

/**
 * Test classes for the LookAndSayIterator.
 */
public class LookAndSayIteratorTest {

  private RIterator<BigInteger> iteratorTest;

  /**
   * Test default constructor.
   */
  @Test
  public void testDefConstructor() {
    iteratorTest = new LookAndSayIterator();
    assertEquals(new BigInteger("1"), iteratorTest.next());
  }

  /**
   * Test one argument constructor.
   */
  @Test
  public void testOneArgConstructor() {
    iteratorTest = new LookAndSayIterator(new BigInteger("1121"));
    assertEquals(new BigInteger("1121"), iteratorTest.next());
  }

  /**
   * Test one argument constructor.
   */
  @Test
  public void testTwoArgConstructor() {
    iteratorTest = new LookAndSayIterator(new BigInteger("1121"), new BigInteger("222222"));
    assertEquals(new BigInteger("1121"), iteratorTest.next());
    assertEquals(new BigInteger("211211"), iteratorTest.next());
  }

  /**
   * Test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOneArgConstructor1() {
    iteratorTest = new LookAndSayIterator(new BigInteger("-123"));
  }

  /**
   * Test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTwoArgConstructor1() {
    iteratorTest = new LookAndSayIterator(new BigInteger("-123"), new BigInteger("200"));
  }

  /**
   * Test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTwoArgConstructor2() {
    iteratorTest = new LookAndSayIterator(new BigInteger("240"), new BigInteger("200"));
  }

  /**
   * Test next().
   */
  @Test
  public void testNext() {
    iteratorTest = new LookAndSayIterator(new BigInteger("1121"));

    assertEquals(new BigInteger("1121"), iteratorTest.next());
    assertEquals(new BigInteger("211211"), iteratorTest.next());
    assertEquals(new BigInteger("12211221"), iteratorTest.next());
    assertEquals(new BigInteger("1122212211"), iteratorTest.next());
    assertEquals(new BigInteger("2132112221"), iteratorTest.next());
    assertEquals(new BigInteger("12111312213211"), iteratorTest.next());
    assertEquals(new BigInteger("11123113112211131221"), iteratorTest.next());
    assertEquals(new BigInteger("311213211321223113112211"), iteratorTest.next());
    assertEquals(new BigInteger("1321121113122113121122132113212221"), iteratorTest.next());
    assertEquals(new BigInteger("111312211231131122211311122122111312211312113211"),
            iteratorTest.next());
  }

  /**
   * Test prev().
   */
  @Test
  public void testPrev() {
    iteratorTest = new LookAndSayIterator(new BigInteger("311213211321223113112211"));

    assertEquals(new BigInteger("311213211321223113112211"), iteratorTest.prev());
    assertEquals(new BigInteger("11123113112211131221"), iteratorTest.prev());
    assertEquals(new BigInteger("12111312213211"), iteratorTest.prev());
    assertEquals(new BigInteger("2132112221"), iteratorTest.prev());
    assertEquals(new BigInteger("1122212211"), iteratorTest.prev());
    assertEquals(new BigInteger("12211221"), iteratorTest.prev());

  }

  /**
   * Test hasNext().
   */
  @Test
  public void testHasNext() {
    iteratorTest = new LookAndSayIterator(new BigInteger("1121"),
            new BigInteger("222222222222222222222"));

    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(true, iteratorTest.hasNext());
    iteratorTest.next();
    assertEquals(false, iteratorTest.hasNext());
    assertEquals(new BigInteger("311213211321223113112211"), iteratorTest.next());
    iteratorTest.next();
    assertEquals(false, iteratorTest.hasNext());
  }

  /**
   * Test hasPrev().
   */
  @Test
  public void testHasPrev() {
    iteratorTest = new LookAndSayIterator(new BigInteger("1121112"));
    assertEquals(false, iteratorTest.hasPrevious());
  }

  /**
   * Test hasPrev().
   */
  @Test
  public void testHasPrev2() {
    iteratorTest = new LookAndSayIterator(new BigInteger("11211122"));
    assertEquals(true, iteratorTest.hasPrevious());
  }
}