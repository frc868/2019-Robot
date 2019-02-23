package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Grab extends Command {
  public Grab() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    Robot.hatchClaw.grab();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
