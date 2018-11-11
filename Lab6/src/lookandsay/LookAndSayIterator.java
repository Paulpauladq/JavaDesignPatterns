package lookandsay;

import java.math.BigInteger;

/**
 * This class represents an implementation of the RIterator class. This class has three
 * constructor and several methods including the overridden methods and the new method added.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private final BigInteger startSeed;
  private final BigInteger endValue;
  private BigInteger currNum;
  private static final int HUNDRED = 100;

  /**
   * Default constructor.
   */
  public LookAndSayIterator() {
    this.startSeed = new BigInteger("1");
    this.endValue = defEndValue();
    this.currNum = startSeed;
  }

  /**
   * One-arg constructor.
   *
   * @param startSeed the starting seed integer
   * @throws IllegalArgumentException if the start seed is illegal
   */
  public LookAndSayIterator(BigInteger startSeed)
          throws IllegalArgumentException {
    this.endValue = defEndValue();
    if (startSeed.compareTo(new BigInteger("0")) < 0
            || startSeed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException();
    }
    this.startSeed = startSeed;
    this.currNum = startSeed;
  }

  /**
   * Two-arg constructor.
   *
   * @param startSeed start seed
   * @param endValue  end value
   * @throws IllegalArgumentException if the start seed is illegal
   */
  public LookAndSayIterator(BigInteger startSeed, BigInteger endValue)
          throws IllegalArgumentException {
    this.endValue = endValue;
    if (startSeed.compareTo(new BigInteger("0")) < 0
            || startSeed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException();
    }
    this.startSeed = startSeed;
    this.currNum = startSeed;
  }

  /**
   * Private helper to create a default end value.
   *
   * @return the BigInteger object
   */
  private BigInteger defEndValue() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < HUNDRED; i++) {
      sb.append(i);
    }
    return new BigInteger(sb.toString());
  }

  /**
   * prev() returns the previous element of count and say.
   * 
   * @return the previous BigInteger
   */
  @Override
  public BigInteger prev() {
    BigInteger temp = currNum;
    StringBuilder sb = new StringBuilder();
    String currStr = String.valueOf(currNum);

    for (int i = 0; i < currStr.length(); i += 2) {
      int count = currStr.charAt(i) - '0';
      for (int j = 0; j < count; j++) {
        sb.append(currStr.charAt(i + 1));
      }
    }
    currNum = new BigInteger(sb.toString());
    return temp;
  }

  /**
   * This method return true if the iterator has previous element.
   * @return the boolean hasPrevious
   */
  @Override
  public boolean hasPrevious() {
    String currStr = String.valueOf(currNum);
    return currStr.length() % 2 == 0;
  }

  /**
   * This method return true if the iterator has next element.
   * @return the boolean hasNext
   */
  @Override
  public boolean hasNext() {
    return currNum.compareTo(endValue) < 0;
  }

  /**
   * This methods return the next BigInteger element of the iterator.
   *
   * @return the next BigInteger
   */
  @Override
  public BigInteger next() {
    BigInteger temp = currNum;
    StringBuilder sb = new StringBuilder();
    int count = 0;
    String currStr = String.valueOf(temp);
    char ch = currStr.charAt(0);

    //go through the loop
    for (int i = 0; i <= currStr.length(); i++) {
      if (i != currStr.length() && currStr.charAt(i) == ch) {
        count++;
      } else {
        sb.append(count);
        sb.append(ch);
        if (i != currStr.length()) {
          count = 1;
          ch = currStr.charAt(i);
        }
      }
    }
    this.currNum = new BigInteger(sb.toString());
    return temp;
  }
}
