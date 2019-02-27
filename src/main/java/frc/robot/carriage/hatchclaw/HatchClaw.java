package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid grab_solenoid, release_solenoid;
  private DigitalInput left_limit, right_limit;

  public HatchClaw() {
    super("HatchClaw");
    grab_solenoid = new Solenoid(RobotMap.PCM, RobotMap.Carriage.HatchClaw.GRAB_SOLENOID);
    release_solenoid = new Solenoid(RobotMap.PCM, RobotMap.Carriage.HatchClaw.RELEASE_SOLENOID);
    left_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.LEFT_LIMIT);
    right_limit = new DigitalInput(RobotMap.Carriage.HatchClaw.RIGHT_LIMIT);
  }

  /**
   * opens claw
   */
  public void grab() {
    grab_solenoid.set(true);
    release_solenoid.set(false);
  }


  /**
   * closes claw
   */
  public void release() {
    grab_solenoid.set(false);
    release_solenoid.set(true);
  }

  /**
   * @return whether the claw is grabbed or not
   */
  public boolean isGrabbed() {
    return grab_solenoid.get();
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
    addTab("Grab Solenoid", grab_solenoid);
    addTab("Release Solenoid", grab_solenoid);
  }

  @Override
  public void updateTab() {
    addTab("Grabbed", isGrabbed());
    addTab("Hatch Detected", isHatchDetected());
  }

}
