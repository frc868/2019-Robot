package frc.robot.powerpack.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetFootDriveSpeed extends Command {
    private double targetSpeed;

    public SetFootDriveSpeed(double targetSpeed) {
        requires(Robot.footDrive);
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void initialize() {
        Robot.footDrive.setSpeed(targetSpeed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}