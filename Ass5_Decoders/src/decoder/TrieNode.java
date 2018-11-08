package decoder;

import java.util.Map;

/**
 * This interface defines a tree node for a prefix tree. The class implementing
 * this interface are meant to design the tree node of the prefix tree.It contains
 * three methods -- isLeaf(), getChildrenMap(), getSymbol().
 */
public interface TrieNode {

  /**
   * isLeaf() method is used to check if the this tree node is leaf node, if
   * it is, returns true, otherwise returns false.
   *
   * @return the boolean checking if this node is leaf node
   */
  public boolean isLeaf();

  /**
   * getChildrenMap() method is used to return the children map of this node,
   * Each entries in this map represents symbol -> TrieNode.
   *
   * @return the children Map
   */
  public Map<Character, TrieNode> getChildrenMap();

  /**
   * getSymbol() method is used to return the symbol of current node.
   *
   * @return the symbol char
   */
  public char getSymbol();

}
