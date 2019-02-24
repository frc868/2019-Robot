package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class OpenAndIntake extends CommandGroup {

    public OpenAndIntake () {
        //TODO figure out what the lowest acceptable position is 
        if (Robot.powerPack.getEncPosition() < SetElevatorPosition.MIDDLE_HATCH) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.MIDDLE_HATCH));
        }
        addSequential(new SetGroundPickupTiltPosition(SetGroundPickupTiltPosition.INTAKE_HATCH));
        addSequential(new IntakeUntilHatchDetected());
    }
}