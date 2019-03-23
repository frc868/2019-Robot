package frc.robot.climberelevator.powerpack.smartsetelevator;


import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class ElevatorToHatchBottomHeight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorToHatchBottomHeight() {
    addSequential(new SetTiltPosition(Tilt.MIDDLE));
    addSequential(new SetElevatorPosition(Robot.powerPack.LOWER_HATCH));

  }
}
