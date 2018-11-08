package listadt;

import java.util.function.Function;

/**
 * This class represents an mutable list implementation class. It not only extends the
 * ListADTImpl class for the reuse of its methods, but also implements the EnhancedList
 * interface allows the implementation of the supplement interface.
 *
 * @param <T> the generic parameter allows generic type
 */
public class MutableListImpl<T> extends ListADTImpl<T> implements EnhancedListADT<T> {

  /**
   * Default constructor.
   */
  public MutableListImpl() {
    super();
  }

  /**
   * Copy constructor calls the copy constructor of the parent class.
   *
   * @param copy EnhancedListADT copy object
   */
  public MutableListImpl(ListADT copy) {
    super(copy);
  }

  /**
   * map() function allows using lambda expression function.
   *
   * @param converter the function that converts T into R
   * @param <R>       the generic type
   * @return a new MutableListImpl object
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new MutableListImpl(super.map(converter));
  }

  /**
   * getCounterPart() methods return the counterPart object of a ListImpl object.
   * it should returns a mutable list contains the same contents.
   *
   * @return the EnhancedListADT object represents the counterpart of current object
   */
  @Override
  public EnhancedListADT<T> getCounterPart() {
    return new ImmutableListImpl(this);
  }
}
