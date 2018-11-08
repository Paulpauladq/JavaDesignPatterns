package decoder;

import java.util.Map;

/**
 * TrieLeafNode class is used to define a specific node of the trie node.
 * LeafNode is the leaf node used to construct the trie. It doesn't has the
 * meaningful children map for each node. It has an instance variable representing
 * a symbol char and can get it using getter. It implements three methods of the
 * TrieNode interface.
 */
public class TrieLeafNode implements TrieNode {
  private char symbol;

  /**
   * The constructor of the group node, simply create a new empty symbol.
   */
  public TrieLeafNode() {
    symbol = '\0';
  }

  /**
   * getSymbol() method is used to return the symbol of current node.
   *
   * @return the symbol char
   */
  public boolean isLeaf() {
    return true;
  }

  /**
   * getChildrenMap() method is used to return the children map of this node,
   * Each entries in this map represents symbol -> TrieNode.
   *
   * @return the children Map
   */
  @Override
  public Map<Character, TrieNode> getChildrenMap() {
    return null;
  }

  /**
   * setSymbol() method is used to set the symbol of current node.
   *
   * @param symbol the symbol char
   */
  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  /**
   * getSymbol() method is used to return the symbol of current node.
   *
   * @return the symbol char
   */
  public char getSymbol() {
    return symbol;
  }

}
