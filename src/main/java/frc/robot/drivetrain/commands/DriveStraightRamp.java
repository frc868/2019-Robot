package frc.robot.drivetrain.commands;

import frc.robot.Robot;

public class DriveStraightRamp extends DriveStraight {
    public double startPower, endPower;

    public DriveStraightRamp(double targetDistance, double startPower, double endPower) {
        super(targetDistance, startPower);
        this.startPower = startPower;
        this.endPower = endPower;
    }

    public DriveStraightRamp(double targetDistance, double startPower, double endPower, double targetAngleChange) {
        this(targetDistance, startPower, endPower);
        super.targetAngleChange = targetAngleChange;
    }

    @Override
    protected void execute() {
        super.targetPower = startPower + ((endPower - startPower) / distanceToTarget());
    }

    protected double distanceToTarget() {
        return Math.abs(targetDistance) - Math.abs(Robot.drivetrainNEO.getAvgScaledDistance() - initialDistance);
    }

}