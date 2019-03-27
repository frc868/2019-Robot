package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FollowVision extends Command {
  public final int VISION_TARGET = 180;
  private int counts = 0;
  private final int COUNTS_NEEDED = 5;

  public static double k_dist = -0.028; // this is negative as a larger value means we are closer to the target 
  public static double k_pos =  0.000;
  public static double k_angle =  0.000;

  private VisionData data;
 
  public FollowVision() {
    requires(Robot.drivetrain);
    requires(Robot.camera);
  }

  @Override
  protected void execute() {
    data = Robot.camera.getData();

    if (data.hasTarget()) {
      k_dist  = SmartDashboard.getNumber("k_dist",  k_dist);
      k_pos   = SmartDashboard.getNumber("k_pos",   k_pos);
      k_angle = SmartDashboard.getNumber("k_angle", k_angle);
      
      double distError = data.getDistance() - VISION_TARGET;
      double distValue = distError * k_dist;

      double posError = data.getPosition();
      double posValue = posError * k_pos;

      double angleError = data.getAngle();
      double angleValue = angleError * k_angle;

      double left = (distValue + posValue + angleValue) / 10.0;
      double right = (distValue - posValue - angleValue) / 10.0;

      Robot.drivetrain.setSpeed(left, right);

      counts = 0;
    } else {
      counts++;
    }
  }

  @Override
  protected boolean isFinished() {
      return counts >= COUNTS_NEEDED;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}
