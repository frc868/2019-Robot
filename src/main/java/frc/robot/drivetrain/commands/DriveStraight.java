package frc.robot.drivetrain.commands;

import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.helpers.pid.PIDCommandPlus;

public class DriveStraight extends PIDCommandPlus {
    private static final double P = 0.01, I = 0.0, D = 0.0; //TODO: tune these constants
    public double initialDistance;
    public double targetDistance, targetPower, targetAngleChange;

    public DriveStraight(double targetDistance, double targetPower) {
        this(targetDistance, targetPower, 0);
    }
    
    public DriveStraight(double targetDistance, double targetPower, double targetAngleChange) {
        super(P, I, D, targetAngleChange);
        this.targetDistance = targetDistance;
        this.targetPower = targetPower;
    }


    @Override
    protected void initialize() {
        setSetpointRelative(Robot.gyro.getAngle());
        initialDistance = Robot.drivetrain.getAvgScaledDistance();
    }

    @Override
    protected double returnPIDInput() {
        return Robot.gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        double left = targetPower + output;
        double right = targetPower - output;
        Robot.drivetrain.setSpeed(Helper.boundValue(left), Helper.boundValue(right));
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }
}