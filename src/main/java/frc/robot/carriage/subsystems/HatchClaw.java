/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  
  private Solenoid actuator;

  public HatchClaw() {
    actuator = new Solenoid(RobotMap.Carraige.HATCH_CLAW);
  }
  
  /**
   * 
   * @param state which state to set the claw to
   */
  public void setState(boolean state) {
    actuator.set(state);
  }

  /**
   * opens claw
   */
  public void open() {
    setState(false);
  }


  /**
   * closes claw
   */
  public void close() {
    setState(true);
  }

  /**
   * 
   * @return state of claw
   */
  public boolean getState() {
    return actuator.get();
  }

}
