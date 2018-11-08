
/**
 * This interface represents a set of operations on any newtonian particle. A
 * newtonian particle is a particle that obeys Newton's laws of motion.
 */

public interface Particle {
  /**
   * Return the state of this particle as a formatted string. The format of the
   * string is as follows:
   * <code>At time [t]: position is ([x],[y])</code> where
   * <ul>
   * <li>[t] is the time passed to this method, rounded to two decimal
   * places</li>
   * <li>[x] is the x-coordinate of the position of this particle at this
   * time, rounded to two decimal places </li>
   * <li>[y] is the y-coordinate of the position of this particle at this
   * time, rounded to two decimal places
   * </li> </ul>
   *
   * @param time the time at which the state must be obtained
   * @return the state of the particle as a string formatted as above
   */
  String getState(float time);
}