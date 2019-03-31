package frc.robot.oi;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.sensors.camera.VisionData;

public class ManualFollowVisionPosPID extends PIDCommand {
  public static double P = 0.001, I = 0, D = 0;
  protected VisionData lastData;
 
  public ManualFollowVisionPosPID() {
    super(P, I, D);
    requires(Robot.drivetrain);
    requires(Robot.camera);
  }

  @Override
  protected double returnPIDInput() {
    VisionData data = Robot.camera.getData();

    if (data.hasTarget()) {
      lastData = data;
    }

    return data.getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    double manual = Helper.deadzone(-OI.driver.getLY(), .03);
    Robot.drivetrain.setSpeed(manual + output, manual - output);
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
