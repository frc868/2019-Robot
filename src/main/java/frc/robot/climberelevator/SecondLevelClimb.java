package frc.robot.climberelevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.helpers.DelayedCommand;
import frc.robot.climberelevator.forks.*;
import frc.robot.climberelevator.ramps.*;
import frc.robot.climberelevator.powerpack.*;
import frc.robot.climberelevator.footdrive.*;

public class SecondLevelClimb extends CommandGroup {
    public SecondLevelClimb(boolean buddyClimb) {
        addSequential(new DeployRamps());

        if (buddyClimb) {
            addParallel(new DeployForks());
        }

        addSequential(new DelayedCommand(new SetClimberSpeed(0.5), 1));
        addParallel(new SetFootDriveSpeed(0.1));
    }
}