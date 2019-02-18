/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator;
  private DigitalInput left_limit, right_limit;
  private final boolean GRABBED_MODE = true;

  public HatchClaw() {
    actuator = new Solenoid(RobotMap.Carriage.HatchClaw.ACTUATOR);
    left_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.LEFT_LIMIT);
    right_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.RIGHT_LIMIT);
  }

  /**
   * opens claw
   */
  public void grab() {
    actuator.set(GRABBED_MODE);
  }


  /**
   * closes claw
   */
  public void release() {
    actuator.set(!GRABBED_MODE);
  }

  /**
   * @return whether the claw is grabbed or not
   */
  public boolean isGrabbed() {
    return actuator.get() == GRABBED_MODE;
  }

  /**
   * 
   * @return state of left limit
   */
  public boolean getLeftLimit() {
    return left_limit.get();
  }

  /**
   * @return state of right limit
   */
  public boolean getRightLimit() {
    return right_limit.get();
  }

  /**
   * @return true if both limits are triggered
   */
  public boolean isHatchDetected() {
    return getLeftLimit() && getRightLimit();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Claw Grabbed", isGrabbed());
    SmartDashboard.putBoolean("Hatch Claw Hatch Detected", isHatchDetected());
  }

}
