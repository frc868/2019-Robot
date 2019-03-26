package frc.robot.helpers.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopCommand extends InstantCommand {
    private Command command;

    public StopCommand(Command command) {
        this.command = command;
    }

    @Override
    protected void initialize() {
        command.cancel();
    }
}