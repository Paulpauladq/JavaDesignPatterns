import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;

/**
 * A Test class that tests methods of RegularManualTransmission class.
 * Test cases includes valid construction operation and different kinds of
 * invalid construction operation that violates different kinds of rules.
 */
public class RegularManualTransmissionTest {
  private ManualTransmission transmission;

  /**
   * Set up new valid RegularManualTransmission for testing.
   */
  @Before
  public void setup() {
    transmission = new RegularManualTransmission(0, 10, 8, 20,
            18, 30, 28, 50, 48, 200);
  }

  /**
   * Test creating two valid RegularManualTransmission objects.
   * One with properly overlapping speed intervals, one with
   * touching speed intervals
   */
  @Test(expected = Test.None.class)
  public void testValidCreation() {
    new RegularManualTransmission(0, 10, 5, 20,
            15, 30, 25, 40, 35, 50);
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 30, 40, 40, 50);

  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (No overlapping in one of the speed ranges.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsNonOverlapping1() {
    new RegularManualTransmission(0, 10, 11, 20,
            20, 30, 30, 40, 40, 50);

  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (No overlapping in one of the speed ranges.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsNonOverlapping2() {
    new RegularManualTransmission(0, 10, 10, 20,
            21, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (No overlapping in one of the speed ranges.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsNonOverlapping3() {
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 31, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (No overlapping in one of the speed ranges.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsNonOverlapping4() {
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 30, 40, 41, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L1 is greater than l2.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL1GreaterThanL2() {
    new RegularManualTransmission(100, 10, 10, 20,
            20, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L2 is greater than l3.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL2GreaterThanL3() {
    new RegularManualTransmission(0, 10, 100, 20,
            20, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L3 is greater than l4.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL3GreaterThanL4() {
    new RegularManualTransmission(0, 10, 10, 20,
            100, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L4 is greater than l5.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL4GreaterThanL5() {
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 100, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L1 is greater than h1.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL1GreaterThanH1() {
    new RegularManualTransmission(10, 0, 10, 20,
            20, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L2 is greater than h2.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL2GreaterThanH2() {
    new RegularManualTransmission(0, 10, 20, 10,
            20, 30, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L3 is greater than h3.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL3GreaterThanH3() {
    new RegularManualTransmission(0, 10, 10, 20,
            30, 20, 30, 40, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L4 is greater than h4.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL4GreaterThanH4() {
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 40, 30, 40, 50);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L5 is greater than h5.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeedsL5GreaterThanH5() {
    new RegularManualTransmission(0, 10, 10, 20,
            20, 30, 30, 40, 50, 40);
  }

  /**
   * Test invalid creation of RegularManualTransmission objects.
   * (L1's value is not 0.)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeeds1Nonzero() {
    new RegularManualTransmission(10, 10, 10, 20,
            20, 30, 30, 40, 40, 50);
  }

  /**
   * Test valid initialization of ReRegularManualTransmission object.
   * Test whether the speed, the gear and the status is initialized
   * properly upon construction.
   */
  @Test
  public void testValidInitializationVariables() {
    assertEquals("The initialized speed is incorrect!",
            transmission.getSpeed(), 0);
    assertEquals("The initialized gear is incorrect!",
            transmission.getGear(), 1);
    assertEquals("The initialized status is incorrect!",
            transmission.getStatus(), null);
  }

  /**
   * Test all possible cases while increasing speed. Verify whether
   * the status string and the current speed and gear are right
   * while performing increasing speed operations.
   */
  @Test(timeout = 5000)
  public void testIncreaseSpeed() {
    int[] low = {Integer.MIN_VALUE, 0, 8, 18, 28, 48, 200};
    int[] high = {0, 10, 20, 30, 50, 200, Integer.MAX_VALUE};
    int currentSpeed = 0;
    for (int gear = 1; gear < 5; gear++) {
      //span the interval for this gear
      while (currentSpeed < high[gear]) {
        String expectedStatus;
        if ((currentSpeed + 1) >= low[gear + 1]) {
          expectedStatus = "OK: you may increase the gear.";
        } else {
          expectedStatus = "OK: everything is OK.";
        }
        transmission = transmission.increaseSpeed();
        assertEquals(
                "Speed increased to " + (currentSpeed + 1) + " but status "
                        + "is incorrect.",
                expectedStatus, transmission.getStatus());
        assertEquals("Upon increasing speed, speed is incorrect",
                (currentSpeed + 1), transmission.getSpeed());
        assertEquals("Upon increasing speed, gear is incorrect",
                gear, transmission.getGear());
        currentSpeed = transmission.getSpeed();
      }
      //try increasing speed further without increasing gear
      for (int i = 10; i <= 20; i++) {
        transmission = transmission.increaseSpeed();
        assertEquals("Attempted a speed increase, but status is incorrect",
                "Cannot increase speed, increase gear first.",
                transmission.getStatus());
        assertEquals("Upon unsuccessfully increasing speed, speed is incorrect",
                currentSpeed, transmission.getSpeed());
        assertEquals("Upon unsuccessfully increasing speed, gear is incorrect",
                gear, transmission.getGear());

      }
      //now increase gear
      transmission = transmission.increaseGear();
      assertEquals("Attempted a gear increase, but status is "
                      + "incorrect",
              "OK: everything is OK.",
              transmission.getStatus());
      assertEquals("Upon increasing gear, speed is incorrect",
              currentSpeed, transmission.getSpeed());
      assertEquals("Upon increasing gear, gear is incorrect",
              gear + 1, transmission.getGear());
    }
    //span the last gear
    while (currentSpeed < high[5]) {
      transmission = transmission.increaseSpeed();
      assertEquals("Speed increased to " + (currentSpeed + 1) + " but status "
                      + "is incorrect.",
              "OK: everything is OK.", transmission.getStatus());
      assertEquals("Upon increasing speed, speed is incorrect",
              (currentSpeed + 1), transmission.getSpeed());
      assertEquals("Upon increasing speed, gear is incorrect",
              5, transmission.getGear());
      currentSpeed = transmission.getSpeed();
    }
    //try to increase speed further than what the last gear will allow
    transmission = transmission.increaseSpeed();
    assertEquals("Tried to increase speed to "
                    + (currentSpeed + 1) + " but status is incorrect.",
            "Cannot increase speed. Reached maximum speed.",
            transmission.getStatus());
    assertEquals("Upon unsuccessfully increasing speed, speed is incorrect",
            (currentSpeed), transmission.getSpeed());
    assertEquals("Upon unsuccessfully increasing speed, gear is incorrect",
            5, transmission.getGear());

  }

  /**
   * Test all possible cases while decreasing speed. Verify whether
   * the status string and the current speed and gear are right
   * while performing decreasing speed operations.
   */
  @Test(timeout = 5000)
  public void testDecreaseSpeed() {
    int[] low = {Integer.MIN_VALUE, 0, 8, 18, 28, 48, 200};
    int[] high = {0, 10, 20, 30, 50, 200, Integer.MAX_VALUE};
    int currentSpeed = 0;


    for (int gear = 1; gear <= 5; gear++) {
      while (currentSpeed < high[gear]) {
        transmission = transmission.increaseSpeed();
        currentSpeed = transmission.getSpeed();
      }
      transmission = transmission.increaseGear();
    }

    for (int gear = 5; gear > 1; gear--) {
      //span this gear
      while (currentSpeed > low[gear]) {
        String expectedStatus;
        if ((currentSpeed - 1) <= high[gear - 1]) {
          expectedStatus = "OK: you may decrease the gear.";
        } else {
          expectedStatus = "OK: everything is OK.";
        }
        transmission = transmission.decreaseSpeed();
        assertEquals(
                "Speed decreased to " + (currentSpeed - 1) + " but status "
                        + "is incorrect.",
                expectedStatus, transmission.getStatus());
        assertEquals("Upon decreasing speed, speed is incorrect",
                (currentSpeed - 1), transmission.getSpeed());
        assertEquals("Upon decreasing speed, gear is incorrect",
                gear, transmission.getGear());
        currentSpeed = transmission.getSpeed();
      }
      //now attempt to decrease speed below this gear's low
      for (int i = 10; i <= 20; i++) {
        transmission = transmission.decreaseSpeed();
        assertEquals("Attempted a speed decrease, but status is incorrect",
                "Cannot decrease speed, decrease gear first.",
                transmission.getStatus());
        assertEquals("Upon unsuccessfully decreasing speed, speed is incorrect",
                currentSpeed, transmission.getSpeed());
        assertEquals("Upon unsuccessfully decreasing speed, gear is incorrect",
                gear, transmission.getGear());

      }
      //decrease gear
      transmission = transmission.decreaseGear();
      assertEquals("Attempted a gear decrease, but status is "
                      + "incorrect",
              "OK: everything is OK.",
              transmission.getStatus());
      assertEquals("Upon decreasing gear, speed is incorrect",
              currentSpeed, transmission.getSpeed());
      assertEquals("Upon decreasing gear, gear is incorrect",
              gear - 1, transmission.getGear());
    }
    //span the last gear
    while (currentSpeed > low[1]) {
      transmission = transmission.decreaseSpeed();
      assertEquals("Speed decreased to " + (currentSpeed - 1) + " but status "
                      + "is incorrect.",
              "OK: everything is OK.", transmission.getStatus());
      assertEquals("Upon decreasing speed, speed is incorrect",
              (currentSpeed - 1), transmission.getSpeed());
      assertEquals("Upon decreasing speed, gear is incorrect",
              1, transmission.getGear());
      currentSpeed = transmission.getSpeed();
    }

    //now try to decrease speed below what is allowed by gear 1
    transmission = transmission.decreaseSpeed();
    assertEquals("Attempted to decrease speed to " +
                    (currentSpeed - 1) + " but status is "
                    + "incorrect.",
            "Cannot decrease speed. Reached minimum speed.",
            transmission.getStatus());
    assertEquals("Upon unsuccessfully decreasing speed, speed is incorrect",
            (currentSpeed), transmission.getSpeed());
    assertEquals("Upon unsuccessfully decreasing speed, gear is incorrect",
            1, transmission.getGear());
  }

  /**
   * Test all possible cases while increasing gear. Verify whether
   * the status string and the current speed and gear are right
   * while performing increasing gear operations.
   */
  @Test(timeout = 5000)
  public void testIncreaseGear() {
    int[] low = {Integer.MIN_VALUE, 0, 8, 18, 28, 48, 200};
    int[] high = {0, 10, 20, 30, 50, 200, Integer.MAX_VALUE};
    int currentSpeed = 0;

    String expectedStatus;
    for (int gear = 1; gear < 5; gear++) {
      while (currentSpeed < high[gear]) {
        //try to change gear if speed is too low
        if (currentSpeed < low[gear + 1]) {
          transmission = transmission.increaseGear();
          expectedStatus = "Cannot increase gear, increase speed first.";
          assertEquals(
                  "Attempting to unsuccessfully increase gear from " + gear +
                          " at speed " + currentSpeed + " but status is incorrect.",
                  expectedStatus, transmission.getStatus());
          assertEquals("Attempting to unsuccessfully increase "
                          + "gear, speed is incorrect",
                  (currentSpeed), transmission.getSpeed());
          assertEquals("Attempting to unsuccessfully increase gear, gear is "
                          + "incorrect",
                  gear, transmission.getGear());
        }
        transmission = transmission.increaseSpeed();
        currentSpeed = transmission.getSpeed();
      }
      transmission = transmission.increaseGear();
      expectedStatus = "OK: everything is OK.";
      assertEquals(
              "Attempting to sucessfully increase gear from " + gear + " at "
                      + "speed " + currentSpeed + " but status is incorrect.",
              expectedStatus, transmission.getStatus());
      assertEquals("Attempting to successfully increase "
                      + "gear, speed is incorrect",
              (currentSpeed), transmission.getSpeed());
      assertEquals("Attempting to successfully increase gear, gear is "
                      + "incorrect",
              (gear + 1), transmission.getGear());

    }

    //try to increase gear further than the last gear
    transmission = transmission.increaseGear();
    assertEquals("Attempting to unsuccessfully increase gear to 6 but status "
                    + "is incorrect.",
            "Cannot increase gear. Reached maximum gear.",
            transmission.getStatus());
    assertEquals("Upon unsuccessfully increasing gear, speed is incorrect",
            (currentSpeed), transmission.getSpeed());
    assertEquals("Upon unsuccessfully increasing, gear is incorrect",
            5, transmission.getGear());

  }

  /**
   * Test all possible cases while decreasing gear. Verify whether
   * the status string and the current speed and gear are right
   * while performing decreasing gear operations.
   */
  @Test(timeout = 5000)
  public void testDecreaseGear() {
    int[] low = {Integer.MIN_VALUE, 0, 8, 18, 28, 48, 200};
    int[] high = {0, 10, 20, 30, 50, 200, Integer.MAX_VALUE};
    int currentSpeed = 0;

    String expectedStatus;

    for (int gear = 1; gear <= 5; gear++) {
      while (currentSpeed < high[gear]) {
        transmission = transmission.increaseSpeed();
        currentSpeed = transmission.getSpeed();
      }
      transmission = transmission.increaseGear();
    }

    for (int gear = 5; gear > 1; gear--) {
      while (currentSpeed > low[gear]) {
        //try to change gear if speed is too high
        if (currentSpeed > high[gear - 1]) {
          transmission = transmission.decreaseGear();
          expectedStatus = "Cannot decrease gear, decrease speed first.";
          assertEquals(
                  "Attempting to unsuccessfully decrease gear from " + gear +
                          " at speed " + currentSpeed + " but status is incorrect.",
                  expectedStatus, transmission.getStatus());
          assertEquals("Attempting to unsuccessfully decrease "
                          + "gear, speed is incorrect",
                  (currentSpeed), transmission.getSpeed());
          assertEquals("Attempting to unsuccessfully decrease gear, gear is "
                          + "incorrect",
                  gear, transmission.getGear());
        }
        transmission = transmission.decreaseSpeed();
        currentSpeed = transmission.getSpeed();
      }
      transmission = transmission.decreaseGear();
      expectedStatus = "OK: everything is OK.";
      assertEquals(
              "Attempting to decrease gear from " + gear + " at speed "
                      + "" + currentSpeed + " but status is incorrect.",
              expectedStatus, transmission.getStatus());
      assertEquals("Attempting to successfully decrease "
                      + "gear, speed is incorrect",
              (currentSpeed), transmission.getSpeed());
      assertEquals("Attempting to successfully decrease gear, gear is "
                      + "incorrect",
              (gear - 1), transmission.getGear());

    }

    //try to decrease gear further than the first gear
    transmission = transmission.decreaseGear();
    assertEquals("Attempting to unsuccessfully increase gear to 0 but status "
                    + "is incorrect.",
            "Cannot decrease gear. Reached minimum gear.",
            transmission.getStatus());
    assertEquals("Upon unsuccessfully decreasing gear, speed is incorrect",
            (currentSpeed), transmission.getSpeed());
    assertEquals("Upon unsuccessfully decreasing, gear is incorrect",
            1, transmission.getGear());

  }


}