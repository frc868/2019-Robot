package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetTiltPosition extends PIDCommandPlus {
    private static final double P = 5, I = 0.01, D = 0.0;

    public SetTiltPosition(double setpoint) {
        super(P, I, D, setpoint, .005);
        requires(Robot.tilt);
    }
    
    @Override
    protected void initialize() {
        if (getSetpoint() > Tilt.MIDDLE) {
            Robot.hatchClaw.grab();
        }
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Erorr", getError());
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getPotPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(-output);
    }
}