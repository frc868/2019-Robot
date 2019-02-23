package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class GiveToClaw extends CommandGroup {


    public GiveToClaw () {
        addSequential(new SetElevatorPosition(SetElevatorPosition.GET_FROM_GROUND_PICKUP));
        addSequential(new Release());
        addSequential(new SetHatchPickupWristPosition(SetHatchPickupWristPosition.GIVE_HATCH));
        addSequential(new SetElevatorPosition(SetElevatorPosition.MIDDLE_HATCH));
        addSequential(new SetHatchPickupWristPosition(SetHatchPickupWristPosition.STORED));
    }
}