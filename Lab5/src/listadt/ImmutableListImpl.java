package listadt;

import java.util.function.Function;

/**
 * This class represents an immutable list implementation class. It not only extends the
 * ListADTImpl class for the reuse of its methods, but also implements the EnhancedList
 * interface allows the implementation of the supplement interface.
 *
 * @param <T> the generic parameter allows generic type
 */
public class ImmutableListImpl<T> extends ListADTImpl<T> implements EnhancedListADT<T> {

  //  private GenericListADTNode<T> immutableHead;
  private static final String ERR_MESS = "Unsupported Operation !!";

  /**
   * Default constructor.
   */
  public ImmutableListImpl() {
    super();
  }

  /**
   * Copy constructor calls the copy constructor of the parent class.
   *
   * @param copy EnhancedListADT copy object
   */
  public ImmutableListImpl(ListADT copy) {
    super(copy);
  }

  /**
   * addFront() method for immutable class - not allowed.
   *
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {
    throw new UnsupportedOperationException(ERR_MESS);
  }

  /**
   * addBack() method for immutable class - not allowed.
   *
   * @param b the object to be added to the back of this list
   */
  @Override
  public void addBack(T b) {
    throw new UnsupportedOperationException(ERR_MESS);
  }

  /**
   * add() method for immutable class - not allowed.
   *
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    throw new UnsupportedOperationException(ERR_MESS);
  }

  /**
   * remove() method for immutable class - not allowed.
   *
   * @param b the object to be removed
   */
  @Override
  public void remove(T b) {
    throw new UnsupportedOperationException(ERR_MESS);
  }

  /**
   * map() function allows using lambda expression function.
   *
   * @param converter the function that converts T into R
   * @param <R>       the generic type
   * @return a new ImmutableListImpl object
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ImmutableListImpl(super.map(converter));
  }

  /**
   * getCounterPart() methods return the counterPart object of a ListImpl object.
   * it should returns a mutable list contains the same contents.
   *
   * @return the EnhancedListADT object represents the counterpart of current object
   */
  @Override
  public EnhancedListADT<T> getCounterPart() {
    return new MutableListImpl(this);
  }
}
