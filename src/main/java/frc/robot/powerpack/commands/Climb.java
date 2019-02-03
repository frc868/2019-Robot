package frc.robot.powerpack.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.helpers.DelayedCommand;

public class Climb extends CommandGroup {
    public Climb() {
        addSequential(new DeployRamps());
        addSequential(new DelayedCommand(new SetClimberSpeed(0.5), 1));
        addParallel(new SetFootDriveSpeed(0.1));
    }
}