package frc.robot.climberelevator.powerpack.smartsetelevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class ElevatorToHatchUpperHeight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorToHatchUpperHeight() {
    addSequential(new SetElevatorPosition(Robot.powerPack.UPPER_HATCH));

  }
}
