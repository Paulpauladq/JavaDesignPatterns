import org.junit.Before;
import org.junit.Test;

import listadt.EnhancedListADT;
import listadt.ImmutableListImpl;
import listadt.ListADT;
import listadt.MutableListImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is meant to test all the function of EnhancedListADT.
 */
public class EnhancedListADTTest {
  private EnhancedListADT<Integer> immuIntTest;
  private EnhancedListADT<Integer> muIntTest;
  private EnhancedListADT<Character> immuCharTest;
  private EnhancedListADT<Character> muCharTest;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    immuIntTest = new ImmutableListImpl<Integer>();
    muIntTest = new MutableListImpl<Integer>();

    immuCharTest = new ImmutableListImpl<Character>();
    muCharTest = new MutableListImpl<Character>();
  }

  /**
   * Demonstration function to demonstrate how to use all the methods.
   */
  @Test
  public void demonstration() {
    //add elements to mutable integer list
    muIntTest.add(0, 1);
    muIntTest.add(1, 12);
    muIntTest.add(2, 5);
    muIntTest.add(3, 21);
    assertEquals("(1 12 5 21)", muIntTest.toString());
    //add elements to mutable char list
    muCharTest.add(0, 'd');
    muCharTest.add(1, 'c');
    muCharTest.add(2, '2');
    muCharTest.add(3, 'a');
    assertEquals("(d c 2 a)", muCharTest.toString());
    String expected = "Unsupported Operation !!";
    //getCounterPart() of the mutable list
    EnhancedListADT<Integer> immu = muIntTest.getCounterPart();
    //invalid operation to immutable list
    try {
      immu.add(0, 1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.addFront(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.addBack(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.remove(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    //test the immutable list hasn't been changed
    assertEquals("(1 12 5 21)", immu.toString());

    //the initial immutable object can't be modified!
    try {
      immuIntTest.add(0, 1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immuIntTest.remove(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    //change back to mutable list again
    EnhancedListADT<Integer> mu = immu.getCounterPart();
    //add operations on the mutable list
    mu.add(0, 11);
    mu.addBack(21);
    mu.addFront(10);
    mu.addBack(121);

    assertEquals("(10 11 1 12 5 21 21 121)", mu.toString());
    assertEquals("(1 12 5 21)", immu.toString());
    assertEquals("(1 12 5 21)", muIntTest.toString());
  }

  /**
   * Test empty mutable list.
   */
  @Test
  public void testEmptyMu() {
    assertEquals("()", muCharTest.toString());
  }

  /**
   * Test empty immutable list.
   */
  @Test
  public void testEmptyImmu() {
    assertEquals("()", immuIntTest.toString());
  }


  /**
   * Test addFront().
   */
  @Test
  public void mutCharTestAddFront() {
    muCharTest.addFront('$');
    muCharTest.addFront('&');
    muCharTest.addFront('*');
    muCharTest.addFront('0');
    assertEquals("(0 * & $)", muCharTest.toString());
  }

  /**
   * Test addBack().
   */
  @Test
  public void mutCharTestAddBack() {
    muCharTest.addBack('$');
    muCharTest.addBack('&');
    muCharTest.addBack('*');
    muCharTest.addBack('0');
    assertEquals("($ & * 0)", muCharTest.toString());
  }

  /**
   * Test add().
   */
  @Test
  public void mutCharTestAdd() {
    muCharTest.add(0, '$');
    muCharTest.add(1, '&');
    muCharTest.add(2, '*');
    muCharTest.add(3, '0');
    assertEquals("($ & * 0)", muCharTest.toString());
  }

  /**
   * Test remove().
   */
  @Test
  public void mutCharTestRemove() {
    muCharTest.add(0, '$');
    muCharTest.add(1, '&');
    muCharTest.add(2, '*');
    muCharTest.add(3, '0');
    assertEquals("($ & * 0)", muCharTest.toString());
    muCharTest.remove('$');
    muCharTest.remove('&');
    muCharTest.remove('*');
    assertEquals("(0)", muCharTest.toString());
  }

  /**
   * Test get().
   */
  @Test
  public void mutCharTestGet() {
    muCharTest.add(0, '$');
    muCharTest.add(1, '&');
    muCharTest.add(2, '*');
    muCharTest.add(3, '0');
    assertEquals('$', (char) muCharTest.get(0));
    assertEquals('&', (char) muCharTest.get(1));
    assertEquals('*', (char) muCharTest.get(2));
    assertEquals('0', (char) muCharTest.get(3));
  }

  /**
   * Test invalid add().
   */
  @Test(expected = UnsupportedOperationException.class)
  public void setImmuCharTestAdd() {
    immuCharTest.add(0, '1');
  }

  /**
   * Test invalid addFront().
   */
  @Test(expected = UnsupportedOperationException.class)
  public void setImmuCharTestAddFront() {
    immuCharTest.addFront('1');
  }

  /**
   * Test invalid addBack().
   */
  @Test(expected = UnsupportedOperationException.class)
  public void setImmuCharTestAddBack() {
    immuCharTest.addBack('1');
  }

  /**
   * Test invalid remove().
   */
  @Test(expected = UnsupportedOperationException.class)
  public void setImmuCharTestRemove() {
    immuCharTest.remove('1');
  }

  /**
   * test map().
   */
  @Test
  public void mutIntTestMap() {
    muIntTest.add(0, 2);
    muIntTest.add(1, 3);
    muIntTest.add(2, 1);
    muIntTest.add(3, 5);
    muCharTest.add(0, '$');
    muCharTest.add(1, '&');
    muCharTest.add(2, '*');
    muCharTest.add(3, '0');
    ListADT<Integer> map1 = muIntTest.map(x -> x + 2);
    ListADT<Character> map2 = muCharTest.map(x -> x);
    assertEquals("(4 5 3 7)", map1.toString());
    assertEquals("($ & * 0)", map2.toString());
  }

  /**
   * test map() for immutable integer.
   */
  @Test
  public void immutIntTestMap() {
    muIntTest.add(0, 2);
    muIntTest.add(1, 3);
    muIntTest.add(2, 1);
    muIntTest.add(3, 5);
    EnhancedListADT<Integer> immu = muIntTest.getCounterPart();
    ListADT<Integer> map1 = immu.map(x -> x + 2);
    assertEquals("(4 5 3 7)", map1.toString());
  }

  /**
   * test getCounterPart.
   */
  @Test
  public void mutIntTestGetCounterPartAddImmu() {
    muIntTest.add(0, 2);
    muIntTest.add(1, 3);
    muIntTest.add(2, 1);
    muIntTest.add(3, 5);

    String expected = "Unsupported Operation !!";
    EnhancedListADT<Integer> immu = muIntTest.getCounterPart();
    try {
      immu.add(0, 1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.addFront(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.addBack(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    try {
      immu.remove(1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
  }

  /**
   * Test getCounterPart() twice.
   */
  @Test
  public void mutIntTestGetCounterPartTwice() {
    muIntTest.add(0, 2);
    muIntTest.add(1, 3);
    muIntTest.add(2, 1);
    muIntTest.add(3, 5);

    String expected = "Unsupported Operation !!";
    EnhancedListADT<Integer> immu = muIntTest.getCounterPart();
    try {
      immu.add(0, 1);
    } catch (UnsupportedOperationException e) {
      assertEquals(expected, e.getMessage());
    }
    EnhancedListADT<Integer> mu = immu.getCounterPart();
    mu.addFront(1);
    mu.addBack(2);
    mu.add(2, 11);
    mu.remove(1);
    assertEquals("(2 11 3 1 5 2)", mu.toString());
    assertEquals("(2 3 1 5)", immu.toString());
    assertEquals("(2 3 1 5)", muIntTest.toString());
  }
}