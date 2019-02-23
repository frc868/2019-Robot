package frc.robot.helpers.pid;

import edu.wpi.first.wpilibj.command.PIDCommand;

public abstract class PIDCommandPlus extends PIDCommand {
    private boolean forward;
    private double P, I, D;

    public PIDCommandPlus(double P, double I, double D, double setpoint) {
        super(P, I, D);

        this.P = P;
        this.I = I;
        this.D = D;

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

    public void setP(double P) {
        this.P = P;
        getPIDController().setP(P);
    }

    public void setI(double I) {
        this.I = I;
        getPIDController().setI(I);
    }

    public void setD(double D) {
        this.D = D;
        getPIDController().setD(D);
    }
}