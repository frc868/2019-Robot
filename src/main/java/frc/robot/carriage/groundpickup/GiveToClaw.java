package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class GiveToClaw extends CommandGroup {


    public GiveToClaw () {
        addSequential(new SetTiltPosition(SetTiltPosition.MIDDLE));
        addParallel(new Release());

        addSequential(new SetElevatorPosition(SetElevatorPosition.GET_FROM_GROUND_PICKUP));

        addSequential(new SetGroundPickupTiltPosition(SetGroundPickupTiltPosition.GIVE_HATCH));
        addSequential(new Grab()); // maybe make a command here that tilts ground pickup back until the claw's limits detect it
        addSequential(new SetElevatorPosition(SetElevatorPosition.MIDDLE_HATCH)); //TODO figure out the lowest hight to move elevator to where the hatch is completely out of the ground pickup AND we can safely store ground pickup

        addSequential(new SetGroundPickupTiltPosition(SetGroundPickupTiltPosition.STORED));
    }
}