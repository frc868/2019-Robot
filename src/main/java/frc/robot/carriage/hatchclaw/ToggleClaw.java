package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleClaw extends Command {
  public ToggleClaw() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    if (Robot.hatchClaw.isGrabbed()) {
        Robot.hatchClaw.release();
    } else {
        Robot.hatchClaw.grab();
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
