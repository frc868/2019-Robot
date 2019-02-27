package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.helpers.commands.DelayedCommand;
import frc.robot.climberelevator.ramps.*;
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.climberelevator.footdrive.*;

public class AutoClimb extends CommandGroup {
    private final double SECOND_LEVEL_CLIMB_TIME = 2, THIRD_LEVEL_CLIMB_TIME = 4;

    public AutoClimb(boolean thirdLevel) {
        double climbTime;

        if (thirdLevel) {
            climbTime = THIRD_LEVEL_CLIMB_TIME;
        } else {
            climbTime = SECOND_LEVEL_CLIMB_TIME;
        }

        addSequential(new DeployRamps());

        addSequential(new DelayedCommand(new SetClimberSpeed(0.5), 1), climbTime);
        addParallel(new SetFootDriveSpeed(0.1));

        addSequential(new SetClimberSpeed(-0.5), climbTime);
        addSequential(new DriveStraight(30, 0.5));
    }
}