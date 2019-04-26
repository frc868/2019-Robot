package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FollowVision extends Command {
  public final int VISION_TARGET = 200;

  public static double k_dist = -0.011; // this is negative as a larger value means we are closer to the target 
  public static double k_pos =  0.0125;
  public static double k_angle =  1;
  public static double l_dist = 0.0;
  public static double l_pos = 0.008;
  public static double a_correction = 1;
  // public static double l_pos = 0;
  public static double l_angle = 0;
  // public static double k_angle = 0;

  protected VisionData data;
 
  public FollowVision() {
    requires(Robot.drivetrain);
    requires(Robot.camera);
  }

  @Override
  protected void initialize() {
    k_dist = l_dist;
    k_pos = l_pos;
    k_angle = l_angle;
  }

  @Override
  protected void execute() {
    data = Robot.camera.getData();

    if (data.hasTarget()) {
      double distError = data.getDistance() - VISION_TARGET;
      double distValue = distError * k_dist;

      double posError = data.getPosition();
      double posValue = posError * k_pos;

      double angleError = data.getAngle();
      double angleValue = angleError * k_angle;

      SmartDashboard.putNumber("Angle Value", angleValue);
      System.out.println(angleValue);
      
      double left = (distValue + posValue + angleValue) / 10.0;
      double right = (distValue - posValue - angleValue) / 10.0;

      Robot.drivetrain.setSpeed(left, right);
    }
  }

  @Override
  protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}
