package frc.robot.helpers;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DelayedCommand extends CommandGroup {
	public DelayedCommand(Command command, double delaySeconds) {
		addSequential(new WaitCommand(delaySeconds));
		addSequential(command);
	}
}