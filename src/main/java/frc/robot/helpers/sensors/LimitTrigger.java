package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.helpers.triggers.TriggerPlus;

/**
 * a Trigger to be used by limit switches
 */
public class LimitTrigger extends TriggerPlus {
  private DigitalInput digitalLimit;
  private PotentiometerLimit potentiometerLimit;
  private AnalogDistanceLimit analogDistanceLimit;
  
  /**
   * @param digitalInput the digital sensor to use
   */
  public LimitTrigger(DigitalInput digitalInput) {
    this.digitalLimit = digitalInput;
  }

  /**
   * @param potentiometerLimit digitalInput the potentiometer limit sensor to use
   */
  public LimitTrigger(PotentiometerLimit potentiometerLimit) {
    this.potentiometerLimit = potentiometerLimit;
  }

  /**
   * @param analogDistanceLimit digitalInput the analog distance limit sensor to use
   */
  public LimitTrigger(AnalogDistanceLimit analogDistanceLimit) {
    this.analogDistanceLimit = analogDistanceLimit;
  }

  @Override
  public boolean get() {
    if (digitalLimit != null) {
      return digitalLimit.get();
    } else if (potentiometerLimit != null) {
      return potentiometerLimit.get();
    } else {
      return analogDistanceLimit.get();
    }
  }
}
