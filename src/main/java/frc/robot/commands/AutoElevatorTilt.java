package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoElevatorTilt extends Command {
    
    public AutoElevatorTilt() {
        requires(Robot.powerPack);
        requires(Robot.tilt);
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToElevator();
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}