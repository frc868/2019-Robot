package frc.robot.oi;

import frc.robot.Robot;
import frc.robot.sensors.camera.FollowVision;

public class ManualFollowVision extends FollowVision {

  public ManualFollowVision() {
    super();
    super.k_dist = 0; // stop distance from having an effect as the driver will control this
  }

  @Override
  protected void execute() {
    super.execute();
    double y = -OI.driver.getLY();
    Robot.drivetrain.adjustSpeed(y, y);
  }
}
