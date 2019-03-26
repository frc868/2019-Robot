package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class Grab extends InstantCommand {
  /**
   * opens the hatch claw to grab a hatch
   */
  public Grab() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    // grab hatch
    Robot.hatchClaw.grab();
  }
}
