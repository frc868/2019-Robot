package frc.robot.auton.commands;

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
        super.targetPower = startPower + ((endPower - startPower) / super.distanceToTarget());
    }

}