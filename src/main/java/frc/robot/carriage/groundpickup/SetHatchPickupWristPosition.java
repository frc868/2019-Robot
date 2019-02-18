package frc.robot.carriage.groundpickup;

import frc.robot.Robot;
import frc.robot.helpers.PIDCommandPlus;

public class SetHatchPickupWristPosition extends PIDCommandPlus {
    private static final double P = 1.0, I = 0.0, D = 0.0;
    public final double STORED = 0, GIVE_HATCH = 1, INTAKE_HATCH = 2;

    public SetHatchPickupWristPosition(int setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.groundPickup);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.groundPickup.getWristEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.groundPickup.setWristSpeed(output);
    }

    @Override
    protected void end() {
        Robot.groundPickup.stopWrist();
    }
}