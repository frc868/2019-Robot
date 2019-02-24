package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetTiltSpeed extends Command {
    private double targetSpeed;

    public SetTiltSpeed(double targetSpeed) {
        requires(Robot.tilt);
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void initialize() {
        Robot.tilt.setSpeed(targetSpeed);
    }

    @Override
    protected void end() {
        Robot.tilt.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}