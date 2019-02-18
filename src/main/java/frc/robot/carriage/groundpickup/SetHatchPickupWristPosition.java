package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class SetHatchPickupWristPosition extends PIDCommand {
    private static final double P = 1.0, I = 0.0, D = 0.0;
    public final double STORED = 0, GIVE_HATCH = 1, INTAKE_HATCH = 2;

    public SetHatchPickupWristPosition(int setpoint) {
        super("SetHatchPickupWristPosition", P, I, D);
        requires(Robot.groundPickup);
        setSetpoint(setpoint);
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
    protected boolean isFinished() {
        return false; //TODO fix this
    }



    
}