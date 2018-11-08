package cs5010.register;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class SimpleRegisterTest {

  private static final int PENNY_VALUE = 1;
  private static final int NICKEL_VALUE = 5;
  private static final int QUARTER_VALUE = 25;
  private static final int DIME_VALUE = 10;
  private static final int ONE_VALUE = 100;
  private static final int FIVE_VALUE = 500;
  private static final int TEN_VALUE = 1000;

  private CashRegister registerTest;
  NavigableMap<Integer, Integer> expectedMap;
  NavigableMap<Integer, Integer> withdrawMap;

  /**
   * Set up the environment.
   */
  @Before
  public void setUp() {
    registerTest = new SimpleRegister();
    withdrawMap = new TreeMap<>((k, v) -> (v - k));
    expectedMap = new TreeMap<>((k, v) -> (v - k));
    expectedMap.put(PENNY_VALUE, 0);
    expectedMap.put(NICKEL_VALUE, 0);
    expectedMap.put(QUARTER_VALUE, 0);
    expectedMap.put(DIME_VALUE, 0);
    expectedMap.put(ONE_VALUE, 0);
    expectedMap.put(FIVE_VALUE, 0);
    expectedMap.put(TEN_VALUE, 0);
  }

  @Test
  public void testEmptyLog() {
    assertEquals("", registerTest.getAuditLog());
  }

  @Test
  public void testInitializationMap() {
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddPennies() {
    registerTest.addPennies(22);
    expectedMap.put(PENNY_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroPennies() {
    registerTest.addPennies(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativePennies() {
    registerTest.addPennies(-22);
  }

  @Test
  public void testAddNickels() {
    registerTest.addNickels(22);
    expectedMap.put(NICKEL_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroNickels() {
    registerTest.addNickels(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeNickels() {
    registerTest.addNickels(-22);
  }

  @Test
  public void testAddDimes() {
    registerTest.addDimes(22);
    expectedMap.put(DIME_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroDimes() {
    registerTest.addDimes(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeDimes() {
    registerTest.addDimes(-22);
  }

  @Test
  public void testAddQuarters() {
    registerTest.addQuarters(22);
    expectedMap.put(QUARTER_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroQuarters() {
    registerTest.addDimes(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeQuarters() {
    registerTest.addDimes(-22);
  }

  @Test
  public void testAddOnes() {
    registerTest.addOnes(22);
    expectedMap.put(ONE_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroOnes() {
    registerTest.addOnes(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeOnes() {
    registerTest.addOnes(-22);
  }

  @Test
  public void testAddFives() {
    registerTest.addFives(22);
    expectedMap.put(FIVE_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroFives() {
    registerTest.addFives(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeFives() {
    registerTest.addFives(-22);
  }

  @Test
  public void testAddTens() {
    registerTest.addTens(22);
    expectedMap.put(TEN_VALUE, 22);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test
  public void testAddZeroTens() {
    registerTest.addTens(0);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeTens() {
    registerTest.addTens(-22);
  }

  @Test
  public void testMultipleValidAdd() {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);
    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 2);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWithdraw1() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    Map<Integer, Integer> changeMap = registerTest.withdraw(-2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWithdraw2() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    Map<Integer, Integer> changeMap = registerTest.withdraw(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWithdraw3() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    Map<Integer, Integer> changeMap = registerTest.withdraw(-1, -1);
  }

  @Test
  public void validSufficientWithdraw() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 2);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 0);
    assertEquals(expectedMap, registerTest.getContents());
    assertEquals(withdrawMap, (NavigableMap<Integer, Integer>) changeMap);
  }

  @Test
  public void validSufficientWithdraw2() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 2);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 2);
    expectedMap.put(PENNY_VALUE, 0);
    withdrawMap.put(PENNY_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    assertEquals(withdrawMap, (NavigableMap<Integer, Integer>) changeMap);
  }

  @Test
  public void validSufficientWithdraw3() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 2);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 7);
    expectedMap.put(PENNY_VALUE, 0);
    expectedMap.put(NICKEL_VALUE, 1);
    withdrawMap.put(PENNY_VALUE, 2);
    withdrawMap.put(NICKEL_VALUE, 1);
    assertEquals(expectedMap, registerTest.getContents());
    assertEquals(withdrawMap, (NavigableMap<Integer, Integer>) changeMap);
  }

  @Test
  public void validSufficientWithdraw4() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(10);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 10);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 12);
    expectedMap.put(PENNY_VALUE, 0);
    expectedMap.put(DIME_VALUE, 1);

    withdrawMap.put(PENNY_VALUE, 2);
    withdrawMap.put(DIME_VALUE, 1);

    assertEquals(expectedMap, registerTest.getContents());
    assertEquals(withdrawMap, (NavigableMap<Integer, Integer>) changeMap);
  }

  @Test
  public void validSufficientWithdraw5() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    expectedMap.put(PENNY_VALUE, 2);
    expectedMap.put(NICKEL_VALUE, 2);
    expectedMap.put(DIME_VALUE, 2);
    expectedMap.put(QUARTER_VALUE, 2);
    expectedMap.put(ONE_VALUE, 2);
    expectedMap.put(FIVE_VALUE, 2);
    expectedMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 3282);
    expectedMap.put(PENNY_VALUE, 0);
    expectedMap.put(NICKEL_VALUE, 0);
    expectedMap.put(DIME_VALUE, 0);
    expectedMap.put(QUARTER_VALUE, 0);
    expectedMap.put(ONE_VALUE, 0);
    expectedMap.put(FIVE_VALUE, 0);
    expectedMap.put(TEN_VALUE, 0);
    withdrawMap.put(PENNY_VALUE, 2);
    withdrawMap.put(NICKEL_VALUE, 2);
    withdrawMap.put(DIME_VALUE, 2);
    withdrawMap.put(QUARTER_VALUE, 2);
    withdrawMap.put(ONE_VALUE, 2);
    withdrawMap.put(FIVE_VALUE, 2);
    withdrawMap.put(TEN_VALUE, 2);
    assertEquals(expectedMap, registerTest.getContents());
    assertEquals(withdrawMap, (NavigableMap<Integer, Integer>) changeMap);
  }

  @Test(expected = InsufficientCashException.class)
  public void validInSufficientWithdraw() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 3283);
  }

  @Test(expected = InsufficientCashException.class)
  public void validInSufficientWithdraw2() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);

    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 8);
  }

  @Test
  public void validSufficientWithdrawLog() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);
    String expectedString = "Deposit: 0.02\n" +
            "Deposit: 0.10\n" +
            "Deposit: 0.20\n" +
            "Deposit: 0.50\n" +
            "Deposit: 2.00\n" +
            "Deposit: 10.00\n" +
            "Deposit: 20.00";
    assertEquals(expectedString, registerTest.getAuditLog());
    Map<Integer, Integer> changeMap = registerTest.withdraw(0, 30);
    expectedString = "Deposit: 0.02\n" +
            "Deposit: 0.10\n" +
            "Deposit: 0.20\n" +
            "Deposit: 0.50\n" +
            "Deposit: 2.00\n" +
            "Deposit: 10.00\n" +
            "Deposit: 20.00\n" +
            "Withdraw: 0.30";
    assertEquals(expectedString, registerTest.getAuditLog());
    registerTest.addFives(2);
    expectedString = "Deposit: 0.02\n" +
            "Deposit: 0.10\n" +
            "Deposit: 0.20\n" +
            "Deposit: 0.50\n" +
            "Deposit: 2.00\n" +
            "Deposit: 10.00\n" +
            "Deposit: 20.00\n" +
            "Withdraw: 0.30\n" +
            "Deposit: 10.00";
    assertEquals(expectedString, registerTest.getAuditLog());
    Map<Integer, Integer> changeMap2 = registerTest.withdraw(0, 220);
    expectedString = "Deposit: 0.02\n" +
            "Deposit: 0.10\n" +
            "Deposit: 0.20\n" +
            "Deposit: 0.50\n" +
            "Deposit: 2.00\n" +
            "Deposit: 10.00\n" +
            "Deposit: 20.00\n" +
            "Withdraw: 0.30\n" +
            "Deposit: 10.00\n" +
            "Withdraw: 2.20";
    assertEquals(expectedString, registerTest.getAuditLog());
    Map<Integer, Integer> changeMap3 = registerTest.withdraw(0, 1002);
    expectedString = "Deposit: 0.02\n" +
            "Deposit: 0.10\n" +
            "Deposit: 0.20\n" +
            "Deposit: 0.50\n" +
            "Deposit: 2.00\n" +
            "Deposit: 10.00\n" +
            "Deposit: 20.00\n" +
            "Withdraw: 0.30\n" +
            "Deposit: 10.00\n" +
            "Withdraw: 2.20\n" +
            "Withdraw: 10.02";
    assertEquals(expectedString, registerTest.getAuditLog());

  }

  @Test
  public void testCorrectContentAfterException() throws InsufficientCashException {
    registerTest.addPennies(2);
    registerTest.addNickels(2);
    registerTest.addDimes(2);
    registerTest.addQuarters(2);
    registerTest.addOnes(2);
    registerTest.addFives(2);
    registerTest.addTens(2);
    try {
      Map<Integer, Integer> changeMap2 = registerTest.withdraw(0, 22000);
      Map<Integer, Integer> changeMap3 = registerTest.withdraw(0, 8);
    } catch (InsufficientCashException e) {
      expectedMap.put(PENNY_VALUE, 2);
      expectedMap.put(NICKEL_VALUE, 2);
      expectedMap.put(DIME_VALUE, 2);
      expectedMap.put(QUARTER_VALUE, 2);
      expectedMap.put(ONE_VALUE, 2);
      expectedMap.put(FIVE_VALUE, 2);
      expectedMap.put(TEN_VALUE, 2);
      assertEquals(expectedMap, registerTest.getContents());
    }
  }
}