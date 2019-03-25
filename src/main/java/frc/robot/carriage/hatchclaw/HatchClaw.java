package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.sensors.AnalogDistanceLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;
import frc.robot.oi.Rumble;
import frc.robot.Robot;

public class HatchClaw extends SubsystemManagerChild {
  private Solenoid actuator; // solenoid to actuate hatch claw
  private AnalogDistanceLimit detection_limit; // analog distance sensor limit to detect hatch
  private final boolean GRABBED_STATE = false; // state of solenoid that equates to grabbed mode
  private final boolean HATCH_DETECTED_STATE = true; // state of detection limit that equates to having a hatch
  private final double ACTIVATION_DISTANCE = 5; // distance required to activate analog distance sensor limit

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
    // sees if solenoid's state is the same as the state that indicates that the hatch is grabbed
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
    // sees if limit's state is the same as the state that indicates that a hatch is detected
    return getLimit() == HATCH_DETECTED_STATE; 
  }

  @Override
  public void init() {
    detection_limit.getTrigger().whenActive(new Rumble());
  }

  @Override
  public void initEnabled() {
    // Robot.ballIntake.detection_limit.getTrigger().whenActive(new Grab());
  }

  @Override
  public void updateSD() {
    SmartDashboard.putBoolean("Hatch Grabbed?", isGrabbed());
    SmartDashboard.putBoolean("Hatch Detected?", isHatchDetected());
    SmartDashboard.putNumber("Hatch Distance", detection_limit.getRaw().getDistance());
  }
}
