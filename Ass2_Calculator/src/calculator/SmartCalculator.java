package calculator;

/**
 * SmartCalculator class extends the AbstractCalculator class. It overrides
 * input() function of the AbstractCalculator class. It's a simple calculator
 * takes straightforward inputs. Also it has a series of designated
 * rules to limit the input.
 */
public class SmartCalculator extends AbstractCalculator {

  /**
   * A SmartCalculator constructor calls the constructor of the
   * AbstractCalculator class.
   */
  public SmartCalculator() {
    super();
  }

  /**
   * A SmartCalculator constructor calls the copy constructor of the
   * AbstractCalculator class.
   *
   * @param copy a SmartCalculator object
   */
  public SmartCalculator(SmartCalculator copy) {
    super(copy);
  }

  /**
   * A smart calculator accepts inputs like a normal calculator. This
   * calculator is backward compatible with the simple calculator (i.e.
   * it can handle everything the simple calculator can). Due to limited
   * processing power it too cannot work with whole numbers longer than
   * 32 bits. However this calculator can also handle the following “smart”
   * inputs:Input “=” multiple times: 3 2 + 2 4 = produces 56 as before.
   * However 3 2 + 2 4 = = and 3 2 + 2 4 = = = are also valid input sequences,
   * and produce 80 and 104 respectively.Skipping the second operand: the input
   * 3 2 + = produces 64. The input 3 2 + = = produces 96, and so on. The state
   * at the end of each “=” is the result of the computation thus far.Two
   * consecutive operators: 3 2 + - 2 4 = ignores the first operator, and
   * produces 8 as the result.Begin with operator: + 3 2 - 2 4 = ignores the
   * “+” and produces 8 as the result.Like SimpleCalculator it does not allow
   * negative inputs although it can handle negative numbers, and it uses
   * IllegalArgumentException to report all invalid inputs and sequences.
   * However it throws a RuntimeException if a valid input causes an operand
   * to overflow. If the operand does not overflow but the result of the arithmetic
   * does, then the result reported should be 0. For example, a + b - 1 0 should
   * result in -10 if a+b will overflow.
   *
   * @param character a char
   * @return a Calculator object
   */
  @Override
  public Calculator input(char character) {

    AbstractCalculator copyObj = new SmartCalculator(this);
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

      return new SmartCalculator();
    }
    //********************* input: +/-/* ************************
    else if (character == '+' || character == '-' || character == '*') {
      //input: ++,--,etc.
      if (lastCharRe == '+' || lastCharRe == '-' || lastCharRe == '*') {
        copyObj.currOperator = character;
        str.replace(str.length() - 1, str.length(), Character.toString(character));
      } else if (str.length() == 0) {
        //++
      }
      //input: ...=+,...=-,etc.
      else if (copyObj.currOperator == '!') {
        copyObj.currOperator = character;
        str.append(character);
      }
      //input: 22+33+ (55+)
      else if (copyObj.currOperator == '+' && lastCharRe >= '0' && lastCharRe <= '9') {
        //sum OVERFLOWS!
        long addRes = (long) copyObj.lastResult + copyObj.lastOperand;
        if (addRes > Integer.MAX_VALUE || addRes < Integer.MIN_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult += copyObj.lastOperand;
        }
        copyObj.lastOperand = 0;
        copyObj.currOperator = character;
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
        str.append(character);
      }
      //input: 22-33- (-11-)
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
      //input: 22*3* (66*)
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
      //input: 3=, 45=, ..
      if (copyObj.currOperator == '#') {
        throw new IllegalArgumentException();
      }
      //input: 323+=, 3-=, ..
      else if (lastCharRe == '+') {
        copyObj.lastOperand = copyObj.lastResult;
        long addRes = (long) copyObj.lastResult + copyObj.lastOperand;
        if (addRes < Integer.MIN_VALUE || addRes > Integer.MAX_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult += copyObj.lastOperand;
        }
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '+';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
      //input: 32-=, ..
      else if (lastCharRe == '-') {
        copyObj.lastOperand = copyObj.lastResult;
        long subRes = (long) copyObj.lastResult - copyObj.lastOperand;
        if (subRes < Integer.MIN_VALUE || subRes > Integer.MAX_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult -= copyObj.lastOperand;
        }
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '-';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
      //input: 2*=
      else if (lastCharRe == '*') {
        copyObj.lastOperand = copyObj.lastResult;
        long mulRes = (long) copyObj.lastResult * copyObj.lastOperand;
        if (mulRes < Integer.MIN_VALUE || mulRes > Integer.MAX_VALUE) {
          copyObj.lastResult = 0;
        } else {
          copyObj.lastResult *= copyObj.lastOperand;
        }
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '*';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
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
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '+';
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
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '-';
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
        copyObj.implicitOperand = copyObj.lastOperand;
        copyObj.lastOperand = 0;
        copyObj.currOperator = '!';
        copyObj.implicitOperator = '*';
        str = new StringBuilder(Integer.toString(copyObj.lastResult));
      }
      //3+===, 3-===,34+32===
      else if (copyObj.currOperator == '!') {
        //3+=====,3+2=====
        if (copyObj.implicitOperator == '+') {
          long addRes = (long) copyObj.lastResult + copyObj.implicitOperand;
          if (addRes > Integer.MAX_VALUE || addRes < Integer.MIN_VALUE) {
            copyObj.lastResult = 0;
          } else {
            copyObj.lastResult += copyObj.implicitOperand;
            str = new StringBuilder(Integer.toString(copyObj.lastResult));
          }
        }
        //4-=====,4-3====
        else if (copyObj.implicitOperator == '-') {
          long subRes = (long) copyObj.lastResult - copyObj.implicitOperand;
          if (subRes > Integer.MAX_VALUE || subRes < Integer.MIN_VALUE) {
            copyObj.lastResult = 0;
          } else {
            copyObj.lastResult -= copyObj.implicitOperand;
            str = new StringBuilder(Integer.toString(copyObj.lastResult));
          }
        }
        //3*====，3*3====
        else if (copyObj.implicitOperator == '*') {
          long mulRes = (long) copyObj.lastResult * copyObj.implicitOperand;
          if (mulRes > Integer.MAX_VALUE || mulRes < Integer.MIN_VALUE) {
            copyObj.lastResult = 0;
          } else {
            copyObj.lastResult *= copyObj.implicitOperand;
            str = new StringBuilder(Integer.toString(copyObj.lastResult));
          }
        }
      }
    }
    copyObj.resultString = str.toString();
    return copyObj;
  }

}
