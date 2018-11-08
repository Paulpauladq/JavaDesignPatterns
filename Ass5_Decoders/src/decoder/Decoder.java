package decoder;

/**
 * This interface defines an interface for a decoder.  Decoding is a common
 * operations on data. Given an encoded message arrays, decoding process
 * can transfer the encoded message to a set of symbols. This process can be
 * thought as a mapping form code dictionary to symbol. For example, consider
 * that all input messages are made of symbols {𝑎,𝑏,𝑐,𝑑,𝑒}, and the coding symbols
 * are {0,1}. An example dictionary could be {𝑎→10,𝑏→1100,𝑐→1101,𝑑→01,𝑒→00}.
 * Then, an input message “dab” will be encoded as “01101100” by replacing the symbols
 * ‘d’, ‘a’, ‘b’ with their respective codes from the dictionary. Similarly, an encoded
 * string “11001001” will be decoded to “bad” using the same dictionary.
 */
public interface Decoder {

  /**
   * An addCode method that takes a symbol and its code as a character and string respectively.
   * This method should add this code to the coding tree. It should not return anything.This
   * method should throw an IllegalStateException if the code contains symbols other than the
   * coding symbols (other than 1 and 0 in the above example).
   *
   * @param symbol the symbol char
   * @param code   the code String
   * @throws IllegalStateException throw exception if the code contains symbols other than the
   *                               coding symbols
   */
  public void addCode(char symbol, String code) throws IllegalStateException;

  /**
   * A decode method that takes an encoded message as a string, and returns the decoded message
   * as a string using the coding tree created thus far.This method should throw an
   * IllegalStateException if the decoding fails (e.g. not all codes have been added, so the
   * traversal leads to a leaf that does not exist)
   *
   * @param message the encoded message
   * @return the decode String
   * @throws IllegalStateException This method should throw an IllegalStateException if the
   *                               decoding fails
   */
  public String decode(String message) throws IllegalStateException;

  /**
   * A allCodes method that returns the codes entered thus far as a string. This string contains
   * each symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return all code String
   */
  public String allCodes();

  /**
   * A isCodeComplete method that returns true if the code entered so far is complete,  false
   * otherwise. A code is said to be complete if every valid encoded message can be successfully
   * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if
   * the coding tree is full (i.e. every non-leaf node has exactly the same number of children,
   * equal to the number of coding symbols). In other words, a coding tree for coding symbols
   * {0,1} is full if each non-leaf node has exactly two children.
   *
   * @return the boolean checking if the code is complete or not
   */
  boolean isCodeComplete();

}
