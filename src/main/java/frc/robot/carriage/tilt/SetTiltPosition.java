package frc.robot.carriage.tilt;

import frc.robot.Robot;
import frc.robot.helpers.PIDCommandPlus;

public class SetTiltPosition extends PIDCommandPlus {
    private static final double P = 1.0, I = 0.0, D = 0.0;
    public static final double LOWER = 0, MIDDLE = 1, UPPER = 2;

    public SetTiltPosition(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.tilt);
        requires(Robot.hatchClaw);
    }
    
    @Override
    protected void initialize() {
        if (getSetpoint() < MIDDLE) {
            Robot.hatchClaw.grab();
        }
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(output);
    }   
}