package calculator;

/**
 * SimpleCalculator class extends the AbstractCalculator class. It overrides
 * input() function of the AbstractCalculator class. It's a simple calculator
 * takes straightforward inputs. Also it has a series of designated
 * rules to limit the input.
 */
public class SimpleCalculator extends AbstractCalculator {

  /**
   * A SimpleCalculator constructor calls the constructor of the
   * AbstractCalculator class.
   */
  public SimpleCalculator() {
    super();
  }

  /**
   * A SimpleCalculator constructor calls the copy constructor of the
   * AbstractCalculator class.
   *
   * @param copy a SimpleCalculator object
   */
  public SimpleCalculator(SimpleCalculator copy) {
    super(copy);
  }

  /**
   * This input() methods overrides the Abstract method input().
   * The rules are as follows:
   * A correct basic sequence of inputs is the first operand, followed by the operator,
   * followed by the second operand, followed by “=” (e.g. 3 2 + 2 4 =). Note that each
   * operand may have multiple digits.
   * A valid sequence can contain a sequence of operands and operators
   * (e.g. 3 2 + 2 4 - 1 0 = , 3 2 + 2 4 = - 10 =, etc.).The result at any point should
   * show either what was entered thus far, or the result. For example, for the sequence
   * of inputs 3 2 + 2 4 = the result after each input should be "3", "32", "32+", "32+2",
   * "32+24", "56" in that order. For the sequence of inputs 3 2 + 2 4 - 1 0 = the result
   * after each input should be "3", "32", "32+", "32+2", "32+24", "56-", "56-1", "56-10",
   * "46" in that order.The only valid operand characters are 0-9, and the only valid operators
   * are +, - and *.The input ’C’ will clear the calculator inputs.The calculator does not
   * “infer” any missing inputs. For example, although 32+=, +12+3, etc. is valid input on
   * a normal calculator, this calculator will report that as an error.The calculator does
   * allow inputting “=” multiple times. In this case it will return the same result.
   * For example the result after 3 2 + 2 4 = and 3 2 + 2 4 = = = is the same: 56. This is
   * not what a normal calculator will do.The calculator does not allow inputting negative
   * numbers, although it can handle negative results.It throws an IllegalArgumentException
   * for all invalid inputs and sequences. However it throws a RuntimeException if a valid input
   * causes an operand to overflow. If the operand does not overflow but the result of the
   * arithmetic does, then the result reported should be 0. For example, a + b - 1 0 should
   * result in -10 if a+b will overflow.
   *
   * @param character a char
   * @return a Calculator object
   */
  @Override
  public Calculator input(char character) {

    AbstractCalculator copyObj = new SimpleCalculator(this);
    StringBuilder str = new StringBuilder();

    str.append(copyObj.resultString);
    char lastCharRe;

    if (str.length() > 0) {
      lastCharRe = str.charAt(str.length() - 1);
    } else {
      lastCharRe = ' ';
    }
    //********************* invalid char ************************
    if (!isInputValid(character)) {
      throw new IllegalArgumentException();
    }
    //********************* input: C ************************
    else if (character == 'C') {
      return new SimpleCalculator();
    }
    //********************* input: +/-/* ************************
    else if (character == '+' || character == '-' || character == '*') {
      //input: ++,--,etc.
      if (lastCharRe == '+' || lastCharRe == '-' || lastCharRe == '*') {
        throw new IllegalArgumentException();
      }
      //input: + , - , ...
      else if (str.length() == 0) {
        throw new IllegalArgumentException();
      }
      //input: ...=+,...=-,etc.
      else if (copyObj.currOperator == '!') {
        copyObj.currOperator = character;
        str.append(character);
      }
      //input: 22+33+ (55+)
      else if (copyObj.currOperator == '+' && lastCharRe >= '0' && lastCharRe <= '9') {
        //sum OVERFLOWS!
        if (copyObj.lastResult + copyObj.lastOperand < 0) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult += copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = character;
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
        str.append(character);
      }
      //input: 22-33+ (55-)
      else if (copyObj.currOperator == '-' && lastCharRe >= '0' && lastCharRe <= '9') {
        long subRes = (long) copyObj.lastResult - copyObj.lastOperand;
        if (subRes > Integer.MAX_VALUE || subRes < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult -= copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = character;
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
        str.append(character);
      }
      //input: 22*33+ (55*)
      else if (copyObj.currOperator == '*' && lastCharRe >= '0' && lastCharRe <= '9') {
        long multiplicationResult = (long) copyObj.lastResult * copyObj.lastOperand;
        //multiplication OVERFLOWS!
        if (multiplicationResult > Integer.MAX_VALUE || multiplicationResult < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult *= copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = character;
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
        str.append(character);
      } else {
        copyObj.currOperator = character;
        copyObj.lastResult = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        str.append(character);
      }
    }
    //********************* input: 0-9 ************************
    else if (character >= '0' && character <= '9') {
      //input: 23=
      if (copyObj.currOperator == '!') {
        throw new IllegalArgumentException();
      }
      //lastOperand OVERFLOWS!

      else if (copyObj.lastOperand > Integer.MAX_VALUE / 10
              || copyObj.lastOperand == Integer.MAX_VALUE / 10
              && Character.getNumericValue(character) > Integer.MAX_VALUE % 10) {
        throw new RuntimeException();
      } else {
        copyObj.lastOperand = copyObj.lastOperand * 10 + Character.getNumericValue(character);
        str.append(character);
      }
    }
    //************************input: = *************************
    else if (character == '=') {
      //input: 3=,45=
      if (copyObj.currOperator == '#') {
        throw new IllegalArgumentException();
      }
      //input: 323+=, 3-=, ...
      else if (lastCharRe == '+' || lastCharRe == '-' || lastCharRe == '*') {
        throw new IllegalArgumentException();
      }
      //input:3+5=
      else if (copyObj.currOperator == '+') {
        //sum OVERFLOWS!
        long addRes = (long) copyObj.lastResult + copyObj.lastOperand;
        if (addRes > Integer.MAX_VALUE || addRes < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult += copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
      //input：3-5=
      else if (copyObj.currOperator == '-') {
        long subRes = (long) copyObj.lastResult - copyObj.lastOperand;
        if (subRes > Integer.MAX_VALUE || subRes < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult -= copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
      //input：4*7=
      else if (copyObj.currOperator == '*') {
        //sum OVERFLOWS!
        long multiplicationResult = (long) copyObj.lastResult * copyObj.lastOperand;
        if (multiplicationResult > Integer.MAX_VALUE
                || multiplicationResult < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult *= copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
    }
    //return copy object
    copyObj.resultString = str.toString();
    return copyObj;
  }
}
