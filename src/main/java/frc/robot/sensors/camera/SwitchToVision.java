package frc.robot.sensors.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwitchToVision extends Command {
  public SwitchToVision() {
    // requires(Robot.camera);
  }

  @Override
  protected void initialize() {
    // Robot.camera.switchToVision();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}