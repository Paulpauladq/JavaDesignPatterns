/**
 * This class implements the particle interface and represents
 * a simple Newtonian particle.
 */
public class SimpleProjectile implements Particle {

  final float gravityAcc = -9.81f;
  private float initial_x;
  private float initial_y;

  private float velocity0_x;
  private float velocity0_y;

  /**
   * Construct a SimpleProjectile object that has the
   * provided initial x coordinate, initial y coordinate, horizontal
   * velocity and vertical velocity.
   *
   * @param initial_x   the initial x coordinate
   * @param initial_y   the initial y coordinate
   * @param velocity0_x the initial horizontal velocity
   * @param velocity0_y the initial vertical velocity
   */
  public SimpleProjectile(float initial_x, float initial_y, float velocity0_x, float velocity0_y) {
    this.initial_x = initial_x;
    this.initial_y = initial_y;
    this.velocity0_x = velocity0_x;
    this.velocity0_y = velocity0_y;
  }

  /**
   * Return the initial x coordinate.
   *
   * @return the initial x coordinate
   */
  public float getInitialX() {
    return this.initial_x;
  }

  /**
   * Return the initial y coordinate.
   *
   * @return the initial y coordinate
   */
  public float getInitialY() {
    return this.initial_y;
  }

  /**
   * Return the initial horizontal velocity.
   *
   * @return the initial horizontal velocity
   */
  public float getVelocity0X() {
    return this.velocity0_x;
  }

  /**
   * Return the initial vertical velocity.
   *
   * @return the initial vertical velocity
   */
  public float getVelocity0Y() {
    return this.velocity0_y;
  }

  /**
   * @param time the time at which the state must be obtained
   */
  @Override
  public String getState(float time) {
    float final_x;
    float final_y;

    String outputString;
    //calculate the time when the particle to fall to the ground
    float ground_time = 2 * Math.abs(velocity0_y / gravityAcc);

    if (velocity0_y < 0) {
      final_x = initial_x + velocity0_x * time;
      final_y = initial_y;
    } else {
      if (time <= 0) {
        final_x = initial_x;
        final_y = initial_y;
      } else if (time >= ground_time) {
        final_x = initial_x + velocity0_x * ground_time;
        final_y = initial_y;
      } else {
        final_x = initial_x + velocity0_x * time;
        final_y = initial_y + velocity0_y * time + gravityAcc * time * time / 2;
      }
    }

    outputString = String.format("At time %.2f: position is (%.2f,%.2f)", time, final_x, final_y);
    return outputString;
  }
}
