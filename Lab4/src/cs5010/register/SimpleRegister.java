package cs5010.register;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * This class implements the SimpleRegister Interface. It implements a simple register.
 * It has many methods including adding coins and bills, withdraw, get content and
 * get audit log.
 */
public class SimpleRegister implements CashRegister {
  private static final int PENNY_VALUE = 1;
  private static final int NICKEL_VALUE = 5;
  private static final int QUARTER_VALUE = 25;
  private static final int DIME_VALUE = 10;
  private static final int ONE_VALUE = 100;
  private static final int FIVE_VALUE = 500;
  private static final int TEN_VALUE = 1000;
  private static final int DOLLAR_TO_CENT = 100;

  private NavigableMap<Integer, Integer> denominationMap;
  private StringBuilder auditLogSb;

  /**
   * Constructor initialize the variables.
   */
  public SimpleRegister() {
    denominationMap = new TreeMap<>((k, v) -> (v - k));
    denominationMap.put(PENNY_VALUE, 0);
    denominationMap.put(NICKEL_VALUE, 0);
    denominationMap.put(QUARTER_VALUE, 0);
    denominationMap.put(DIME_VALUE, 0);
    denominationMap.put(ONE_VALUE, 0);
    denominationMap.put(FIVE_VALUE, 0);
    denominationMap.put(TEN_VALUE, 0);
    auditLogSb = new StringBuilder();
  }

  /**
   * Add pennies into the register.
   *
   * @param num number of pennies to be added
   */
  @Override
  public void addPennies(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(PENNY_VALUE, denominationMap.getOrDefault(PENNY_VALUE, 0) + num);
    double tempValue = (double) num * PENNY_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add nickels into the register.
   *
   * @param num number of nickels to be added
   */
  @Override
  public void addNickels(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(NICKEL_VALUE, denominationMap.getOrDefault(NICKEL_VALUE, 0) + num);
    double tempValue = (double) num * NICKEL_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add dimes into the register.
   *
   * @param num number of dimes to be added
   */
  @Override
  public void addDimes(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(DIME_VALUE, denominationMap.getOrDefault(DIME_VALUE, 0) + num);
    double tempValue = (double) num * DIME_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add quarters into the register.
   *
   * @param num number of quarters to be added
   */
  @Override
  public void addQuarters(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(QUARTER_VALUE,
            denominationMap.getOrDefault(QUARTER_VALUE, 0) + num);

    double tempValue = (double) num * QUARTER_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add ones into the register.
   *
   * @param num number of ones to be added
   */
  @Override
  public void addOnes(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(ONE_VALUE,
            denominationMap.getOrDefault(ONE_VALUE, 0) + num);
    double tempValue = (double) num * ONE_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add fives into the register.
   *
   * @param num number of fives to be added
   */
  @Override
  public void addFives(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(FIVE_VALUE, denominationMap.getOrDefault(FIVE_VALUE, 0) + num);
    double tempValue = (double) num * FIVE_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * Add tens into the register.
   *
   * @param num number of tens to be added
   */
  @Override
  public void addTens(int num) {
    if (num < 0) {
      throw new IllegalArgumentException();
    }
    denominationMap.put(TEN_VALUE, denominationMap.getOrDefault(TEN_VALUE, 0) + num);
    double tempValue = (double) num * TEN_VALUE / DOLLAR_TO_CENT;
    editAddLog(tempValue);
  }

  /**
   * private helper to edit the log.
   *
   * @param value input double
   */
  private void editAddLog(double value) {
    this.auditLogSb.append("Deposit: " + String.format("%.2f", value) + "\n");
  }

  /**
   * Withdraw coins or bills from the register.
   *
   * @param dollars the dollar amount to be withdrawn
   * @param cents   the cent amount to be withdrawn
   * @return the change map of the denominations.
   * @throws InsufficientCashException if the regsiter doesn't have sufficient
   *                                   denomination, throw exception.
   */
  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException();
    }
    NavigableMap<Integer, Integer> changeMap = new TreeMap<>((k, v) -> (v - k));

    int tempValue = dollars * DOLLAR_TO_CENT + cents;
    for (Map.Entry<Integer, Integer> entry :
            denominationMap.tailMap(tempValue, true).entrySet()) {
      int tempNum = tempValue / entry.getKey();
      if (tempNum == 0 || entry.getValue() == 0) {
        continue;
      }
      if (entry.getValue() >= tempNum) {
        changeMap.put(entry.getKey(), tempNum);
      } else {
        tempNum = entry.getValue();
        changeMap.put(entry.getKey(), entry.getValue());
      }
      tempValue -= tempNum * entry.getKey();
    }
    if (tempValue > 0) {
      throw new InsufficientCashException("Insufficient cash exception!!!");
    }

    for (Map.Entry<Integer, Integer> entry : changeMap.entrySet()) {
      int currKey = entry.getKey();
      int currValue = entry.getValue();
      denominationMap.put(currKey, denominationMap.getOrDefault(currKey, 0) - currValue);
    }

    auditLogSb.append("Withdraw: "
            + String.format("%.2f", dollars + (double) cents / DOLLAR_TO_CENT) + "\n");
    return changeMap;
  }

  /**
   * Get content, return the map.
   *
   * @return the content map
   */
  @Override
  public Map<Integer, Integer> getContents() {
    return new TreeMap<>(this.denominationMap);
  }

  /**
   * Get audit log.
   *
   * @return the log String
   */
  @Override
  public String getAuditLog() {
    if (this.auditLogSb.length() == 0) {
      return "";
    } else {
      return new StringBuilder(this.auditLogSb.substring(
              0, this.auditLogSb.length() - 1)).toString();
    }
  }
}
