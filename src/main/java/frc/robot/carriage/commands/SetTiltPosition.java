package frc.robot.carriage.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class SetTiltPosition extends PIDCommand {
    private static final double P = 1.0, I = 0.0, D = 0.0;
    public static final double HATCH = 0, BALL = 1;

    public SetTiltPosition(int setpoint) {
        super("SetTiltPosition", P, I, D);
        requires(Robot.tilt);
        setSetpoint(setpoint);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(output);
    }

    @Override
    protected boolean isFinished() {
        return false; //TODO fix this
    }    
}