package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements
 * the listadt.
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  /**
   * Non-parameter Constructor.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  /**
   * Copy constructor.
   * @param copy copy ListADT object
   */
  public ListADTImpl(ListADT<T> copy) {
    GenericListADTNode<T> copyHead = new GenericEmptyNode<>();
    for (int i = 0; i < copy.getSize(); i++) {
      copyHead = copyHead.add(i, copy.get(i));
    }
    this.head = copyHead;

  }

  /**
   * Private constructor takes a head node.
   * @param head the head node
   */
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * add Front.
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Add back.
   * @param b the object to be added to the back of this list
   */
  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * add method.
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * get the size of the list adt object.
   * @return size of the list
   */
  @Override
  public int getSize() {
    return head.count();
  }

  /**
   * remove the object.
   * @param b the object to be removed
   */
  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  /**
   * get method.
   * @param index the index of the object to be returned
   * @return the object T
   * @throws IllegalArgumentException exception
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  /**
   * Map function.
   * @param converter the function that converts T into R
   * @param <R> function
   * @return the listADT object
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  /**
   * toString() method.
   * @return String
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
