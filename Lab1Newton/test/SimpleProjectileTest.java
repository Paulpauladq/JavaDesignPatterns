import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the SimpleProjectile class.
 */
public class SimpleProjectileTest {

  private Particle particle_1;
  private Particle particle_2;
  private Particle particle_3;

  @Before
  public void setUp() {
    particle_1 = new SimpleProjectile(0, 0, 10, -10);
    particle_2 = new SimpleProjectile(5, 0, 10, 10);
    particle_3 = new SimpleProjectile(0, 0, 0, 10);
  }

  @Test
  public void testVelocity_0_YIsNegative() {
    assertEquals(particle_1.getState(10), "At time 10.00: position is (100.00,0.00)");
  }

  @Test
  public void testTimeIsNegative() {
    assertEquals(particle_2.getState(-10), "At time -10.00: position is (5.00,0.00)");
  }

  @Test
  public void testTimeIsTooLarge() {
    assertEquals(particle_2.getState(10), "At time 10.00: position is (25.39,0.00)");
  }

  @Test
  public void testRegular() {
    assertEquals(particle_3.getState(2.0387f), "At time 2.04: position is (0.00,0.00)");
  }
}