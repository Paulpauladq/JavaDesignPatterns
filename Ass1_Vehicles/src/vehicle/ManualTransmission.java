package vehicle;

/**
 * An interface that represents a manual transmission.
 * This manual transmission allows its driver change the speed and
 * the gear and present the results of the drivers' actions in some
 * way (some king of messages).
 */
public interface ManualTransmission {

  /**
   * Return the status of this transmission as a String without any additional parameters.
   * @return the status of this transmission as a String without any additional parameters
   */
  public String getStatus();

  /**
   * Get the current speed of the vehicle as a whole number.
   * @return the current speed of the vehicle as a whole number
   */
  public int getSpeed();

  /**
   * Get the current gear of the vehicle as a whole number.
   * @return the current gear of the vehicle as a whole number
   */
  public int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears and return
   * the resulting transmission object. If the speed cannot be increased,
   * then it should return an object with the same speed as before. The speed
   * change amount is up to the implementation and is not an argument of this method.
   * @return an ManualTransmission object with the according speed after increasing legally
   */
  public ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears and return the resulting
   * transmission object. If the speed cannot be decreased, then it should return an object
   * with the same speed as before. The speed change amount is up to the implementation
   * and is not an argument of this method.
   * @return an ManualTransmission object with the according speed after decreasing legally
   */
  public ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed and return the resulting transmission
   * object. If the gear cannot be increased, then it should return an object with the same
   * speed as before.
   * @return an ManualTransmission object with the according speed after increasing legally
   */
  public ManualTransmission increaseGear();

  /**
   * Decrease the gear by one without changing speed and return the resulting transmission
   * object. If the gear cannot be decreased, then it should return an object with the same
   * speed as before.
   * @return an ManualTransmission object with the according speed after decreasing legally
   */
  public ManualTransmission decreaseGear();
}
