package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.helpers.triggers.TriggerPlus;

public class LimitTrigger extends TriggerPlus {
  private DigitalInput digitalLimit;
  private PotentiometerLimit potentiometerLimit;
  private AnalogDistanceLimit analogDistanceLimit;
  
  public LimitTrigger(DigitalInput digitalInput) {
    this.digitalLimit = digitalInput;
  }

  public LimitTrigger(PotentiometerLimit potentiometerLimit) {
    this.potentiometerLimit = potentiometerLimit;
  }

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
