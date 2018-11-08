package decoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * The class DecoderImpl that implements the above interface. It offers a single constructor
 * that takes the coding symbols as a single string (e.g. “01” in the above example). This
 * class should build a coding tree and then use it to decode a message using the above process.
 * It may not have any other public methods.
 */
public class DecoderImpl implements Decoder {

  private TrieNode root;
  private HashSet<Character> codeSymbolsSet;
  private HashSet<Character> symbolsSet;

  /**
   * The constructor takes a String parameter as the input coding symbols. These code are the
   * elements of each coding symbols for each decoding symbols.
   *
   * @param codeSymbols the coding symbols
   */
  public DecoderImpl(String codeSymbols) {
    //String is empty
    if (codeSymbols == null || codeSymbols.length() == 0) {
      throw new IllegalStateException();
    }
    codeSymbolsSet = new HashSet<>();
    symbolsSet = new HashSet<>();
    root = new TrieGroupNode();
    //same coding symbol, throw ex
    for (char ch : codeSymbols.toCharArray()) {
      if (codeSymbolsSet.contains(ch)) {
        throw new IllegalStateException();
      }
      codeSymbolsSet.add(ch);
    }
  }

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
  @Override
  public void addCode(char symbol, String code) throws IllegalStateException {
    if (code == null || code.length() == 0 || symbol == '\u0000') {
      throw new IllegalStateException();
    }
    //if any digit of the code doesn't belong to the symbol set, throw exception
    for (char ch : code.toCharArray()) {
      if (!this.codeSymbolsSet.contains(ch)) {
        throw new IllegalStateException();
      }
    }
    //the symbol is legal
    TrieNode currNode = root;
    //insert the group nodes(code)
    for (int i = 0; i < code.length() - 1; i++) {
      char currChar = code.charAt(i);
      //currChar is not currNode's children
      if (!currNode.getChildrenMap().containsKey(currChar)) {
        currNode.getChildrenMap().put(currChar, new TrieGroupNode());
      }
      //travel down the tree
      currNode = currNode.getChildrenMap().get(currChar);
    }
    //same symbol e.g. 00a, 01a, throw ex
    if (this.symbolsSet.contains(symbol)) {
      throw new IllegalStateException();
    } else {
      char lastChar = code.charAt(code.length() - 1);
      //same code diff symbol: 00a , 00b throw ex
      if (currNode.getChildrenMap().get(lastChar) != null) {
        throw new IllegalStateException();
      }
      //insert the leaf node
      else {
        currNode.getChildrenMap().put(lastChar, new TrieLeafNode());
        //insert the leaf properly
        ((TrieLeafNode) currNode.getChildrenMap().get(lastChar)).setSymbol(symbol);
        symbolsSet.add(symbol);
      }
    }
  }

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
  @Override
  public String decode(String message) throws IllegalStateException {
    StringBuilder sb = new StringBuilder();
    TrieNode currNode = root;
    //check validity of the encoded message
    for (char ch : message.toCharArray()) {
      if (!this.codeSymbolsSet.contains(ch)) {
        throw new IllegalStateException();
      }
    }
    //regular decoding
    for (int i = 0; i < message.length(); i++) {
      char currChar = message.charAt(i);
      if (currNode.getChildrenMap().containsKey(currChar)) {
        currNode = currNode.getChildrenMap().get(currChar);
        if (currNode.isLeaf()) {
          sb.append(currNode.getSymbol());
          currNode = root;
        }
      }
      //wrong decoding
      else {
        throw new IllegalStateException();
      }
    }
    if (!currNode.isLeaf() && currNode != root) {
      throw new IllegalStateException();
    }
    return sb.toString();
  }

  /**
   * A allCodes method that returns the codes entered thus far as a string. This string contains
   * each symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return all code String
   */
  @Override
  public String allCodes() {
    String allCodeStr = "";
    if (root == null || root.getChildrenMap().size() == 0) {
      return null;
    }
    List<String> answer = new ArrayList<String>();
    allCodesHelper(root, "", answer);
    for (String string : answer) {
      allCodeStr += string + "\n";
    }
    return allCodeStr;
  }

  /**
   * A private helper for the recursive way to traverse all the tree nodes. It takes
   * three different parameters -- root TrieNode, path String and a String List
   * object to store the final result.
   *
   * @param root   the root TrieNode
   * @param path   the path String
   * @param answer the String List object to store the final result
   */
  private void allCodesHelper(TrieNode root, String path, List<String> answer) {
    if (root.isLeaf()) {
      answer.add(root.getSymbol() + ":" + path);
    }
    if (root.getChildrenMap() != null && root.getChildrenMap().size() != 0) {
      for (Map.Entry<Character, TrieNode> entries : root.getChildrenMap().entrySet()) {
        allCodesHelper(entries.getValue(), path + entries.getKey(), answer);
      }
    }
  }

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
  @Override
  public boolean isCodeComplete() {
    return isCompleteHelper(root);
  }

  private boolean isCompleteHelper(TrieNode node) {
    int completeSize = codeSymbolsSet.size();
    boolean completeFlag = false;
    if (node.isLeaf()) {
      completeFlag = true;
      return completeFlag;
    } else {
      if (((TrieGroupNode) node).getChildrenNumber() != completeSize) {
        return false;
      } else {
        for (Map.Entry<Character, TrieNode> entries : node.getChildrenMap().entrySet()) {
          completeFlag = isCompleteHelper(entries.getValue());
          if (!completeFlag) {
            return completeFlag;
          }
        }
      }
    }
    return completeFlag;
  }
}
