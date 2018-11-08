import org.junit.Before;
import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderImpl;

import static org.junit.Assert.assertEquals;

/**
 * DecoderImplTest class is a test class used to test the all the methods and exception
 * occurred in the methods.
 */
public class DecoderImplTest {

  private Decoder decoderTest0;
  private Decoder decoderTest2;
  private Decoder decoderTest3;

  /**
   * Set up some decoder for testing.
   */
  @Before
  public void setUp() {
    decoderTest0 = new DecoderImpl("01");
    Decoder decoderTest1 = new DecoderImpl("0123456789abcdef");
    decoderTest2 = new DecoderImpl("123def");
    decoderTest2.addCode('a', "11");
    decoderTest2.addCode('b', "12");
    decoderTest2.addCode('c', "13");
    decoderTest2.addCode('d', "d11");
    decoderTest2.addCode('e', "d12");
    decoderTest2.addCode('f', "d1d");
    decoderTest2.addCode('g', "fe1");
    decoderTest2.addCode('h', "fee");
    decoderTest2.addCode('i', "ff1");
    decoderTest2.addCode('j', "ffe");
    decoderTest2.addCode('k', "fff");
  }

  /**
   * Test invalid constructor with duplicate coding symbols.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidConstructorDuplicate() {
    decoderTest3 = new DecoderImpl("bb");
  }

  /**
   * Test invalid constructor with legal coding symbols with space.
   */
  @Test(expected = Test.None.class)
  public void testInvalidConstructorlegelCodingWithSpace() {
    decoderTest3 = new DecoderImpl("b a");
  }

  /**
   * Test invalid constructor with empty String.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidConstructorEmptyString() {
    decoderTest3 = new DecoderImpl("");
  }

  /**
   * Test invalid constructor with empty String.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidConstructorWithNullString() {
    String nullStr = null;
    decoderTest3 = new DecoderImpl(nullStr);
  }

  /**
   * Test valid constructor with hex.
   */
  @Test(expected = Test.None.class)
  public void testValidConstructor() {
    decoderTest3 = new DecoderImpl("0123456789abcd");
  }

  /**
   * Test invalid addCode() with invalid coding symbol.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeInvalidCode() {
    decoderTest0.addCode('b', "02");
  }

  /**
   * Test invalid addCode() with duplicate symbol for different code.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeDuplicateSymbol() {
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('b', "01");
  }

  /**
   * Test invalid addCode() with empty code.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeEmptyCode() {
    decoderTest0.addCode('a', "");
  }

  /**
   * Test invalid addCode() with empty symbol.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeEmptyChar() {
    decoderTest0.addCode('\0', "00");
  }

  /**
   * Test invalid addCode() with new symbol for the same code.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeDuplicateCode() {
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('a', "00");
  }

  /**
   * Test invalid addCode() with new symbol for the same code.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddCodeDuplicateCode1() {
    decoderTest0.addCode('a', "100");
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('c', "01");
    decoderTest0.addCode('d', "11");
    decoderTest0.addCode('e', "101");
    decoderTest0.addCode('f', "101");
  }

  /**
   * Test valid addCode() with decimal coding symbol.
   */
  @Test(expected = Test.None.class)
  public void testValidAddCode() {
    decoderTest0.addCode('a', "100");
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('c', "01");
    decoderTest0.addCode('d', "11");
    decoderTest0.addCode('e', "101");
  }

  /**
   * Test valid decode() with decimal coding symbol.
   */
  @Test
  public void testValidDecoderDecimal() {
    decoderTest0.addCode('a', "100");
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('c', "01");
    decoderTest0.addCode('d', "11");
    decoderTest0.addCode('e', "101");

    assertEquals(decoderTest0.decode("100"), "a");
    assertEquals(decoderTest0.decode("00"), "b");
    assertEquals(decoderTest0.decode("01"), "c");
    assertEquals(decoderTest0.decode("11"), "d");
    assertEquals(decoderTest0.decode("101"), "e");
    assertEquals(decoderTest0.decode("10001101"), "ace");
  }

  /**
   * Test valid decode() with 6-digit coding symbol.
   */
  @Test
  public void testValidDecoder6DigitTree() {
    assertEquals(decoderTest2.decode("11"), "a");
    assertEquals(decoderTest2.decode("fff11"), "ka");
    assertEquals(decoderTest2.decode("fff11d1d"), "kaf");
    assertEquals(decoderTest2.decode("fff11d1dfee"), "kafh");
  }

  /**
   * Test invalid decode() with decimal coding symbol.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeCodePathDoesntExist() {
    decoderTest0.addCode('a', "100");
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('c', "01");
    decoderTest0.addCode('d', "11");
    decoderTest0.decode("1011");
  }

  /**
   * Test invalid decode() with incomplete decoding.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeCodeIncompleteDecoding1() {
    decoderTest2.decode("11f");
  }

  /**
   * Test invalid decode() with incomplete decoding.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeCodeIncompleteDecoding2() {
    decoderTest2.decode("ff");
  }

  /**
   * Test invalid decode() with incomplete decoding.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeCodeIncompleteDecoding3() {
    decoderTest2.decode("ffffee1");
  }

  /**
   * Test allCodes() with 6-digit coding symbol.
   */
  @Test
  public void testAllCodesDecoder2() {
    assertEquals(decoderTest2.allCodes(), "a:11\nb:12\nc:13\nd:d11\ne:d12" +
            "\nf:d1d\ng:fe1\nh:fee\ni:ff1\nj:ffe\nk:fff\n"
    );
  }

  /**
   * Test allCodes() with null coding symbol.
   */
  @Test
  public void testAllCodesDecoderNULL() {
    assertEquals(decoderTest0.allCodes(), null);
  }

  /**
   * Test allCodes() with decimal decoder.
   */
  @Test
  public void testAllCodesDecoderDecimal() {
    decoderTest0.addCode('a', "100");
    decoderTest0.addCode('b', "00");
    decoderTest0.addCode('c', "01");
    decoderTest0.addCode('d', "11");
    decoderTest0.addCode('e', "101");
    assertEquals(decoderTest0.allCodes(), "b:00\nc:01\na:100\ne:101\nd:11\n");
  }

  /**
   * Test isComplete() when the tree is complete.
   */
  @Test
  public void testIsCompleteTrue() {
    Decoder decoderComplete = new DecoderImpl("01");
    decoderComplete.addCode('a', "000");
    decoderComplete.addCode('b', "001");
    decoderComplete.addCode('c', "010");
    decoderComplete.addCode('d', "011");
    decoderComplete.addCode('e', "100");
    decoderComplete.addCode('f', "101");
    decoderComplete.addCode('g', "110");
    decoderComplete.addCode('h', "111");
    assertEquals(decoderComplete.isCodeComplete(), true);
  }

  /**
   * Test isComplete() when the tree is false.
   */
  @Test
  public void testIsCompleteFalse() {
    Decoder decoderIncomplete = new DecoderImpl("01");
    decoderIncomplete.addCode('a', "000");
    decoderIncomplete.addCode('b', "001");
    decoderIncomplete.addCode('c', "010");
    decoderIncomplete.addCode('d', "011");
    decoderIncomplete.addCode('e', "100");
    decoderIncomplete.addCode('f', "101");
    decoderIncomplete.addCode('g', "110");
    assertEquals(decoderIncomplete.isCodeComplete(), false);
  }

  /**
   * Test isComplete() when the tree is false.
   */
  @Test
  public void testIsCompleteFalse2() {
    Decoder decoderIncomplete = new DecoderImpl("01");
    decoderIncomplete.addCode('a', "000");
    decoderIncomplete.addCode('d', "011");
    decoderIncomplete.addCode('e', "100");
    decoderIncomplete.addCode('f', "101");
    decoderIncomplete.addCode('g', "110");
    assertEquals(decoderIncomplete.isCodeComplete(), false);
  }

  /**
   * Test isComplete() when the tree is false.
   */
  @Test
  public void testIsCompleteFalse3() {
    Decoder decoderIncomplete = new DecoderImpl("01");
    decoderIncomplete.addCode('a', "000");
    decoderIncomplete.addCode('b', "001");
    decoderIncomplete.addCode('c', "010");
    decoderIncomplete.addCode('d', "011");
    decoderIncomplete.addCode('f', "101");
    decoderIncomplete.addCode('g', "110");
    assertEquals(decoderIncomplete.isCodeComplete(), false);
  }

  /**
   * Test isComplete() when the tree is empty.
   */
  @Test
  public void testIsCompleteEmpty() {
    Decoder decoderIncomplete = new DecoderImpl("01");
    assertEquals(decoderIncomplete.isCodeComplete(), false);
  }

}