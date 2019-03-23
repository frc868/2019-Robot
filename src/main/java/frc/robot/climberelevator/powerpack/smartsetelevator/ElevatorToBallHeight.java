package frc.robot.climberelevator.powerpack.smartsetelevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class ElevatorToBallHeight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorToBallHeight() {
    addSequential(new SetElevatorPosition(Robot.powerPack.INTAKE_BALL));
    addSequential(new SetTiltPosition(Tilt.LOWER));
    addParallel(new Grab());
  }
}
