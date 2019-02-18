package frc.robot.helpers;

import edu.wpi.first.wpilibj.command.PIDCommand;

public abstract class PIDCommandPlus extends PIDCommand {
    private boolean forward;

    public PIDCommandPlus(double P, double I, double D, double setpoint) {
        super(P, I, D);
        setSetpoint(setpoint);
    }

    @Override
    protected void initialize() {
        if (getError() > 0) {
            forward = true;
        } else {
            forward = false;
        }
    }


    protected double getError() {
        return getSetpoint() - returnPIDInput();
    }
  

    @Override
    protected boolean isFinished() {
        return forward ? getError() < 0 : getError() > 0;
    }
}