package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.sensors.AnalogDistanceLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;
import frc.robot.oi.Rumble;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator;
  private AnalogDistanceLimit detection_limit;
  private final boolean GRABBED_STATE = false;
  private final boolean HATCH_DETECTED_STATE = true;
  private final double ACTIVATION_DISTANCE = 0;

  public HatchClaw() {
    super("HatchClaw");
    actuator = new Solenoid(RobotMap.PCM, RobotMap.Carriage.HatchClaw.ACTUATOR);
    detection_limit = new AnalogDistanceLimit(RobotMap.Carriage.HatchClaw.DETECTION_LIMIT, ACTIVATION_DISTANCE);
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
  public boolean getLimit() {
    return detection_limit.get();
  }

  /**
   * @return true if both limits detect a hatch
   */
  public boolean isHatchDetected() {
    return getLimit() == HATCH_DETECTED_STATE;
  }

  @Override
  public void init() {
    detection_limit.getTrigger().whenActive(new Rumble());
  }

  @Override
  public void initSD() {
    addTab("Actuator", actuator);
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Grabbed?", isGrabbed());
    SmartDashboard.putBoolean("Hatch Detected?", isHatchDetected());
  }
}
