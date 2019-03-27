package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Grab extends Command {
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

  @Override
  protected boolean isFinished() {
    // finish immediately
    return true;
  }
}
