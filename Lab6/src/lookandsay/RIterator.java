package lookandsay;

import java.util.Iterator;

/**
 * This interface is a iterator for count and say numbers.
 *
 * @param <T> the generic type
 */
public interface RIterator<T> extends Iterator<T> {

  /**
   * The method returns the previous object.
   *
   * @return the generic object
   */
  public T prev();

  /**
   * This method return if the iterator has previous element.
   *
   * @return the boolean
   */
  public boolean hasPrevious();

}
