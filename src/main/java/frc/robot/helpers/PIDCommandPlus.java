package frc.robot.helpers;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class PIDCommandPlus extends PIDCommand {
    private boolean forward;
    private boolean tuningMode = false;
    private double P, I, D;

    public PIDCommandPlus(double P, double I, double D, double setpoint) {
        this(P, I, D, setpoint, false);
    }

    public PIDCommandPlus(double P, double I, double D, double setpoint, boolean tuningMode) {
        super(P, I, D);

        this.P = P;
        this.I = I;
        this.D = D;

        this.tuningMode = tuningMode;

        setSetpoint(setpoint);
    }

    public void setTuningMode() {
        tuningMode = true;
    }

    @Override
    protected void initialize() {
        if (getError() > 0) {
            forward = true;
        } else {
            forward = false;
        }

        if (tuningMode) {
            P = SmartDashboard.getNumber("P", P);
            I = SmartDashboard.getNumber("I", I);
            D = SmartDashboard.getNumber("D", D);

            getPIDController().setP(P);
            getPIDController().setI(I);
            getPIDController().setD(D);
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