package decoder;

import java.util.HashMap;
import java.util.Map;

/**
 * TrieGroupNode class is used to define a specific node of the trie node.
 * GroupNode is the nonleaf node used to construct the trie. It has the children
 * map for each node. It implements three methods of the TrieNode interface.
 */
public class TrieGroupNode implements TrieNode {
  private Map<Character, TrieNode> childrenMap;

  /**
   * The constructor of the group node, simply create a new empty children map.
   */
  public TrieGroupNode() {
    this.childrenMap = new HashMap<>();
  }

  /**
   * getChildrenMap() method is used to return the children map of this node,
   * Each entries in this map represents symbol -> TrieNode.
   *
   * @return the children Map
   */
  public Map<Character, TrieNode> getChildrenMap() {
    return this.childrenMap;
  }

  /**
   * getChildrenNumber() method is used to count the number of the children map for
   * current node.
   * @return the integer number of children number
   */
  public int getChildrenNumber() {
    if (this.childrenMap != null && this.childrenMap.size() != 0) {
      return this.childrenMap.size();
    } else {
      return 0;
    }
  }

  /**
   * getSymbol() method is used to return the symbol of current node.
   *
   * @return the symbol char
   */
  @Override
  public char getSymbol() {
    return '\0';
  }

  /**
   * getSymbol() method is used to return the symbol of current node.
   *
   * @return the symbol char
   */
  public boolean isLeaf() {
    return false;
  }
}
