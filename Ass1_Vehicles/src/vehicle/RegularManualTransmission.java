package vehicle;

/**
 * An implementation class called RegularManualTransmission that implements
 * the ManualTransmission interface. The constructor takes the speed ranges l1,h1,l2,h2,
 * l3,h3,l4,h4,l5,h5. Through calling different functions, drivers can increase and decrease
 * the speed as well as the gear according to a bunch of roles. After each operation,
 * the implementation also provides the status string for different status.
 */
public class RegularManualTransmission implements ManualTransmission {

  public int currSpeed;
  public int[] speedArr;
  public int gear;
  public String statusString;

  private static final int MINSPEED = 0;
  private static final int MINGEAR = 1;
  private static final int MAXGEAR = 5;

  private static final String EVERYTHINGISOK = "OK: everything is OK.";
  private static final String OKCANINCGEAR = "OK: you may increase the gear.";
  private static final String OKMAYDECGEAR = "OK: you may decrease the gear.";
  private static final String CANTINCSPEEDINCGEARFIRST =
          "Cannot increase speed, increase gear first.";
  private static final String CANTDECSPEEDDECGEARFIRST =
          "Cannot decrease speed, decrease gear first.";
  private static final String CANTINCGEARINCSPEEDFIRST =
          "Cannot increase gear, increase speed first.";
  private static final String CANTDECGEARDECSPEEDFIRST =
          "Cannot decrease gear, decrease speed first.";
  private static final String CANTINCSPEEDMAX = "Cannot increase speed. Reached maximum speed.";
  private static final String CANTDECSPEEDMIN = "Cannot decrease speed. Reached minimum speed.";
  private static final String CANTINCGEARMAX = "Cannot increase gear. Reached maximum gear.";
  private static final String CANTDECGEARMIN = "Cannot decrease gear. Reached minimum gear.";

  /**
   * The class offers a constructor that takes the speed ranges for the 5 gears
   * as two integral numbers each: low and high. Thus it takes arguments as
   * l1,h1,l2,h2,...,l5,h5. If the arguments don't satisfy one of the rules, it
   * throws a IllegalArgumentException.
   */
  public RegularManualTransmission(int l1, int h1, int l2, int h2, int l3,
                                   int h3, int l4, int h4, int l5, int h5) {
    this.currSpeed = MINSPEED;
    this.gear = MINGEAR;
    this.speedArr = new int[]{l1, h1, l2, h2, l3, h3, l4, h4, l5, h5};

    if (l1 != 0 || h1 < l1 || h2 < l2 || h3 < l3
            || h4 < l4 || h5 < l5 || l2 <= l1 || l3 <= l2
            || l4 <= l3 || l5 <= l4 || l2 > h1 || l3 > h2
            || l4 > h3 || l5 > h4) {
      throw new IllegalArgumentException("Illegal speed range setup!");
    }
  }

  /**
   * The status message it returns is exactly one of the following possibilities
   * (depending on the state of the transmission).
   *
   * @return the message depending on the state of the transmission
   */
  @Override
  public String getStatus() {
    return this.statusString;
  }

  /**
   * getGear() returns the current gear value.
   * @return current gear value
   */
  @Override
  public int getGear() {
    return this.gear;
  }

  /**
   * getSpeed() returns the current speed value.
   * @return current speed value
   */
  @Override
  public int getSpeed() {
    return this.currSpeed;
  }

  /**
   * This method increases speed by 1 at a time if the operation is valid
   * and prints out the rules according to the output rules.
   *
   * @return an ManualTransmission object with the according speed after increasing legally
   */
  @Override
  public ManualTransmission increaseSpeed() {
    if (this.gear == MAXGEAR) {
      if ((this.currSpeed + 1) > speedArr[2 * MAXGEAR - 1]) {
        this.statusString = CANTINCSPEEDMAX;
      } else {
        this.statusString = EVERYTHINGISOK;
        this.currSpeed++;
      }
    } else if ((this.currSpeed + 1) <= speedArr[2 * this.gear - 1]) {
      if ((this.currSpeed + 1) >= speedArr[2 * this.gear]) {
        this.statusString = OKCANINCGEAR;
        this.currSpeed++;
      } else {
        this.statusString = EVERYTHINGISOK;
        this.currSpeed++;
      }
    } else {
      this.statusString = CANTINCSPEEDINCGEARFIRST;
    }
    return this;
  }

  /**
   * This method decreases speed by 1 at a time if the operation is valid
   * and prints out the rules according to the output rules.
   *
   * @return an ManualTransmission object with the according speed after decreasing legally
   */
  @Override
  public ManualTransmission decreaseSpeed() {

    if (this.gear == MINGEAR) {
      if ((this.currSpeed - 1) < MINSPEED) {
        this.statusString = CANTDECSPEEDMIN;
      } else {
        this.statusString = EVERYTHINGISOK;
        this.currSpeed--;
      }
    } else if ((this.currSpeed - 1) >= speedArr[2 * this.gear - 2]) {
      if ((this.currSpeed - 1) <= speedArr[2 * this.gear - 3]) {
        this.statusString = OKMAYDECGEAR;
        this.currSpeed--;
      } else {
        this.statusString = EVERYTHINGISOK;
        this.currSpeed--;
      }
    } else {
      this.statusString = CANTDECSPEEDDECGEARFIRST;
    }
    return this;
  }

  /**
   * This method increases gear by 1 at a time if the operation is valid
   * and prints out the rules according to the output rules.
   *
   * @return an ManualTransmission object with the according speed after increasing legally
   */
  @Override
  public ManualTransmission increaseGear() {
    if ((this.gear + 1) > MAXGEAR) {
      this.statusString = CANTINCGEARMAX;
    } else {
      if (this.currSpeed > speedArr[2 * this.gear]
              && this.currSpeed <= speedArr[2 * this.gear - 1]) {
        this.statusString = EVERYTHINGISOK;
        this.gear++;
      } else if (this.currSpeed < speedArr[2 * this.gear]) {
        this.statusString = CANTINCGEARINCSPEEDFIRST;
      }
    }
    return this;
  }

  /**
   * This method decreases gear by 1 at a time if the operation is valid
   * and prints out the rules according to the output rules.
   *
   * @return an ManualTransmission object with the according speed after decreasing legally
   */
  @Override
  public ManualTransmission decreaseGear() {
    if ((this.gear - 1) < MINGEAR) {
      this.statusString = CANTDECGEARMIN;
    } else {
      if (this.currSpeed > speedArr[2 * this.gear - 3]) {
        this.statusString = CANTDECGEARDECSPEEDFIRST;
      } else {
        this.statusString = EVERYTHINGISOK;
        this.gear--;
      }
    }
    return this;
  }
}
