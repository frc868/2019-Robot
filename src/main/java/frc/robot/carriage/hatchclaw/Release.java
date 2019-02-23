package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Release extends Command {
  public Release() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    Robot.hatchClaw.release();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
