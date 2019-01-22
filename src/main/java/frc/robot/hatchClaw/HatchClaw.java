/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.hatchClaw;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HatchClaw extends Subsystem {
  
  private Solenoid actuator;

  public HatchClaw() {
    actuator = new Solenoid(RobotMap.HatchClaw.SOLENOID);
  }

  @Override
  public void initDefaultCommand() {

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
    return actuator.get()
  }


}
