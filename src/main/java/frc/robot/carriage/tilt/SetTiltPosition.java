package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetTiltPosition extends PIDCommandPlus {
    private static final double P = 23.0, I = 0.0, D = 0.0;

    public SetTiltPosition(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.tilt);
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Tilt Error", getError());
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getPotPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(-output);
    }

    @Override
    protected boolean isFinished()  {
        return false;
    }
}