/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator;
  private final boolean IS_OPEN = true;

  public HatchClaw() {
    actuator = new Solenoid(RobotMap.Carriage.HATCH_CLAW);
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
    setState(IS_OPEN);
  }


  /**
   * closes claw
   */
  public void close() {
    setState(!IS_OPEN);
  }

  /**
   * 
   * @return state of claw
   */
  public boolean getState() {
    return actuator.get();
  }

  /**
   * @return whether the claw is open or not
   */
  public boolean isOpen() {
    return getState() == IS_OPEN;
  }

  /**
   * 
   * @return whether the claw is closed or not
   */
  public boolean isClosed() {
    return !isOpen();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Claw Openned", isOpen());
  }

}
