package frc.robot.carriage.groundpickup;

import frc.robot.Robot;

public class IntakeUntilHatchDetected extends SetGroundPickupIntakeSpeed {
    private int counts = 0;
    private final int COUNTS_NEEDED = 5;

    public IntakeUntilHatchDetected() {
        super();
    }

    public IntakeUntilHatchDetected(double speed) {
        super(speed);
    }

    @Override
    protected void execute() {
        if (Robot.groundPickup.isHatchDetected()) {
            counts++;
        } else {
            counts = 0;
        }
    }

    @Override
    protected boolean isFinished() {
        return counts >= COUNTS_NEEDED;
    }

}