package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator;
  private DigitalInput left_limit, right_limit;

  public HatchClaw() {
    super("HatchClaw");
    actuator = new Solenoid(RobotMap.PCM, RobotMap.Carriage.HatchClaw.ACTUATOR);
    left_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.LEFT_LIMIT);
    right_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.RIGHT_LIMIT);
  }

  /**
   * opens claw
   */
  public void grab() {
    actuator.set(true);
  }


  /**
   * closes claw
   */
  public void release() {
    actuator.set(false);
  }

  /**
   * @return whether the claw is grabbed or not
   */
  public boolean isGrabbed() {
    return actuator.get();
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
  public void init() {
    release();
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Claw Grabbed", isGrabbed());
    SmartDashboard.putBoolean("Hatch Claw Hatch Detected", isHatchDetected());
    SmartDashboard.putBoolean("Hatch Claw Left", getLeftLimit());
    SmartDashboard.putBoolean("Hatch Claw Right", getRightLimit());
  }

  @Override
  public void initDebug() {
    addDebug("Left Limit", left_limit);
    addDebug("Right Limit", right_limit);
  }

  @Override
  public void updateDebug() {
    addDebug("Grabbed", isGrabbed());
    addDebug("Hatch Detected", isHatchDetected());
  }

  @Override
  public void initTab() {
    addTab("Left Limit", left_limit);
    addTab("Right Limit", right_limit);
    addTab("Hatch Actuator", actuator);
  }

  @Override
  public void updateTab() {
    addTab("Grabbed", isGrabbed());
    addTab("Hatch Detected", isHatchDetected());
  }

}
