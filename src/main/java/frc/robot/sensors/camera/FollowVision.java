/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FollowVision extends Command {
  public final int VISION_TARGET = 180;
  private int counts = 0;
  private final int COUNTS_NEEDED = 5;

  public static double k_dist   = -0.028; // this is negative as a larger value means we are closer to the target 
  public static double k_pos    =  0.012;
  public static double k_hratio =  0.005;
 
  public FollowVision() {
    requires(Robot.drivetrain);
    requires(Robot.camera);
  }

  @Override
  protected void initialize() {
    Robot.camera.switchToVision();
  }

  @Override
  protected void execute() {
    if (Robot.camera.hasTarget()) {
      double distError = Robot.camera.getDistance() - VISION_TARGET;
      double distValue = distError * k_dist;

      double posError = Robot.camera.getPosition();
      double posValue = posError * k_pos;

      double hRatioError = Robot.camera.getHeightRatio();
      double hRatioValue = hRatioError * k_hratio;

      double left = (distValue + posValue + hRatioValue) / 10.0;
      double right = (distValue - posValue - hRatioValue) / 10.0;

      Robot.drivetrain.setSpeed(left, -right);

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
