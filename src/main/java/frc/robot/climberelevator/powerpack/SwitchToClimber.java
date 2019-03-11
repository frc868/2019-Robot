package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwitchToClimber extends Command {
  public SwitchToClimber() {
    requires(Robot.powerPack);
  }

  @Override
  protected void initialize() {
    Robot.powerPack.switchToClimber();
  }


  @Override
  protected boolean isFinished() {
    return false;
  }
}