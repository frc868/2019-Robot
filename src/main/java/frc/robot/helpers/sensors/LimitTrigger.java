/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.helpers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class LimitTrigger extends Trigger {
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
    } else if (potentiometerLimit != null){
      return potentiometerLimit.get();
    } else {
      return false;
    }
  }
}
