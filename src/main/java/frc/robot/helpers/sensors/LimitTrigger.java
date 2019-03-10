package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.helpers.triggers.TriggerPlus;

public class LimitTrigger extends TriggerPlus {
  private DigitalInput digitalLimit;
  private PotentiometerLimit potentiometerLimit;
  
  public LimitTrigger(DigitalInput digitalInput) {
    this.digitalLimit = digitalInput;
  }

  public LimitTrigger(PotentiometerLimit potentiometerLimit) {
    this.potentiometerLimit = potentiometerLimit;
  }

  @Override
  public boolean get() {
    if (digitalLimit != null) {
      return digitalLimit.get();
    } else {
      return potentiometerLimit.get();
    }
  }
}
