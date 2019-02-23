package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.helpers.commands.DelayedCommand;
import frc.robot.climberelevator.forks.*;
import frc.robot.climberelevator.ramps.*;
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.climberelevator.footdrive.*;

public class ThirdLevelClimb extends CommandGroup {
    public ThirdLevelClimb(boolean buddyClimb) {
        addSequential(new DeployRamps());

        if (buddyClimb) {
            addParallel(new DeployForks());
        }

        addSequential(new DelayedCommand(new SetClimberSpeed(0.5), 1), 2);
        addParallel(new SetFootDriveSpeed(0.1));

        addSequential(new SetClimberSpeed(-0.5), 1);
        addSequential(new DriveStraight(30, 0.5));
    }
}