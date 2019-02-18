package frc.robot.climberelevator.footdrive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetFootDriveSpeed extends Command {
    private double speed;

    public SetFootDriveSpeed(double speed) {
        requires(Robot.footDrive);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.footDrive.setSpeed(speed);
    }

    @Override
    protected void end() {
        Robot.footDrive.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}