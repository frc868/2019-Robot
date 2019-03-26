package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class Release extends InstantCommand {
  /**
   * closes the hatch claw to release a hatch
   */
  public Release() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    // releases a hatch
    Robot.hatchClaw.release();
  }
}
