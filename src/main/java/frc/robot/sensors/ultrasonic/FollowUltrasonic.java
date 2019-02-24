package frc.robot.sensors.ultrasonic;

import frc.robot.helpers.pid.PIDCommandPlus;
import frc.robot.Robot;

public class FollowUltrasonic extends PIDCommandPlus {
    private static final double P = 0.025, I = 0.0, D = 0.0;
    private static final double TARGET_DISTANCE = 0;

    public FollowUltrasonic() {
        super(P, I, D, TARGET_DISTANCE);
        requires(Robot.drivetrain);
        requires(Robot.ultrasonic);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.ultrasonic.getAdjustedDistnace();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.setSpeed(output, output);
    }
}