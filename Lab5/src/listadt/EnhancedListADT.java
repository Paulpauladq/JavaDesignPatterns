package listadt;

/**
 * This interface extends ListADT interface, it can be acknowledged as a
 * supplement interface for ListADT since it supports getCounterPart() method
 * besides all the methods provided in the ListADT interface. That method can return
 * the counterpart for either the mutable list and the immutable list.
 *
 * @param <T> the generic parameter allows generic type
 */
public interface EnhancedListADT<T> extends ListADT<T> {

  /**
   * getCounterPart() methods return the counterPart object of a ListImpl object. If it is a
   * mutable list, it should returns an immutable list contains the same contents. Conversely,
   * if it is an immutable list, it should returns a mutable list contains the same contents.
   *
   * @return the EnhancedListADT object represents the counterpart of current object
   */
  public EnhancedListADT<T> getCounterPart();

}
