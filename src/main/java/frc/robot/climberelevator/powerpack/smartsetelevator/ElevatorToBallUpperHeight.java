package frc.robot.climberelevator.powerpack.smartsetelevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.Robot;
import frc.robot.carriage.tilt.Tilt;

public class ElevatorToBallUpperHeight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorToBallUpperHeight() {
    addSequential(new SetElevatorPosition(Robot.powerPack.UPPER_BALL));
    addSequential(new SetTiltPosition(Tilt.UPPER));
  }
}
