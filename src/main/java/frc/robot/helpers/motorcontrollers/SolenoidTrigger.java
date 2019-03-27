package frc.robot.helpers.motorcontrollers;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.helpers.triggers.TriggerPlus;

public class SolenoidTrigger extends TriggerPlus {
  private Solenoid solenoid;
  private boolean state;
  
  public SolenoidTrigger(Solenoid solenoid, boolean state) {
    this.solenoid = solenoid;
    this.state = state;
  }

  @Override
  public boolean get() {
    return solenoid.get() == state;
  }
}
