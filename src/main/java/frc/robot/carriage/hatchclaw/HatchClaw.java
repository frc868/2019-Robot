package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.sensors.IRLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator;
  private IRLimit left_limit, right_limit;
  private final boolean GRABBED_STATE = true;
  private final boolean HATCH_DETECTED_STATE = true;

  public HatchClaw() {
    super("HatchClaw");
    actuator = new Solenoid(RobotMap.PCM, RobotMap.Carriage.HatchClaw.ACTUATOR);
    left_limit = new IRLimit(RobotMap.Carriage.HatchClaw.LEFT_LIMIT);
    right_limit = new IRLimit(RobotMap.Carriage.HatchClaw.RIGHT_LIMIT);
  }

  /**
   * @param state state to set actuator to
   */
  public void setState(boolean state) {
    actuator.set(state);
  }

  /**
   * opens claw
   */
  public void grab() {
    setState(GRABBED_STATE);
  }


  /**
   * closes claw
   */
  public void release() {
    setState(!GRABBED_STATE);
  }

  /**
   * @return state of actuator
   */
  public boolean getState() {
    return actuator.get();
  }

  /**
   * @return whether the claw is grabbed or not
   */
  public boolean isGrabbed() {
    return getState() == GRABBED_STATE;
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
   * @return true if both limits detect a hatch
   */
  public boolean isHatchDetected() {
    return (getLeftLimit() == HATCH_DETECTED_STATE) && (getRightLimit() == HATCH_DETECTED_STATE);
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Grabbed?", isGrabbed());
    SmartDashboard.putBoolean("Hatch Detected?", isHatchDetected());
    SmartDashboard.putBoolean("Hatch Left?", getLeftLimit());
    SmartDashboard.putBoolean("Hatch Right?", getRightLimit());
  }
}
