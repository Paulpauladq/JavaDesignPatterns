package cs5010.register;

import java.util.Map;

/**
 * This is the interface for a cash register.
 */
public interface CashRegister {
  /**
   * Add pennies to the register.
   *
   * @param num number of pennies to be added
   */
  void addPennies(int num);

  /**
   * Add nickels to the register.
   *
   * @param num number of nickels to be added
   */
  void addNickels(int num);

  /**
   * Add dimes to the register.
   *
   * @param num number of dimes to be added
   */
  void addDimes(int num);

  /**
   * Add quarters to the register.
   *
   * @param num number of quarters to be added
   */
  void addQuarters(int num);

  /**
   * Add one-dollar bills to the register.
   *
   * @param num number of ones to be added
   */
  void addOnes(int num);

  /**
   * Add five-dollar bills to the register.
   *
   * @param num number of fives to be added
   */
  void addFives(int num);

  /**
   * Add ten-dollar bills to the register.
   *
   * @param num number of tens to be added
   */
  void addTens(int num);

  /**
   * Withdraw the provided amount from the cash register, if there is enough.
   * The input is provided in dollars and cents, instead of a
   * floating-point number to avoid precision errors, as only two
   * decimal places of precision are realistic.
   *
   * <p>It works as follows:
   * 1. Start from highest denomination.
   * 2. Find the highest number of coins/notes of current denomination such that
   * their value is less than required value.
   * 3. Take that many notes/coins out of the cash register, and reduce the required
   * value by the appropriate amount.
   * 4. If the required value is greater than 0 and there is a lesser denomination, go to step 2.
   * 5. If there are no more denominations, throw an exception because the amount
   * cannot be dispensed with what the register contains.
   *
   * @param dollars the dollar amount to be withdrawn
   * @param cents   the cent amount to be withdrawn
   * @return if dispensing is possible, a map of &lt;value of coin/bill in cents, number
   *     of coins/bills&gt; that represents the change
   */
  Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException;

  /**
   * Return the contents of this register as
   * a map of &lt; value of coin/bill in cents,number of coins/bills &gt;.
   *
   * @return the contents of this register as a map
   */
  Map<Integer, Integer> getContents();

  /**
   * Returns a string describing the history of transactions performed on the cash register.
   * The audit log is a series of transactions (1 per line). Each line is of the form:
   * "Deposit: $x.y" or "Withdraw: $x.y", where x is the dollar amount
   * and y is cents up to 2 decimal places.
   *
   * @return the string of the audit log
   */
  String getAuditLog();
}

