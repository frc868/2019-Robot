package frc.robot.helpers.pid;

import edu.wpi.first.wpilibj.command.PIDCommand;

public abstract class PIDCommandPlus extends PIDCommand {
    private boolean forward;
    private double P, I, D;
    private boolean toleranceMode = false;

    /**
     * sets the PID values for the inherited PIDCommand
     * @param P the propotional error multiplier, adjusts how fast and how much oscilation the system has
     * @param I the integral error multiplier, adjusts the overshoot and deals with steady state error
     * @param D the derivative error multiplier, dampens the velocity of the output and removes overshoot
     */
    public PIDCommandPlus(double P, double I, double D) {
        super(P, I, D);

        this.P = P;
        this.I = I;
        this.D = D;
    }

    /**
     * Sets the PID values for the inherited PIDCommand, the setpoint, and the tolerance
     * @param P the proportional error
     * @param I the integral error
     * @param D the derivative error
     * @param setpoint the setpoint you want to go to
     * @param tolerance sets the absolute tolerance for your PID loop
     */    
    public PIDCommandPlus(double P, double I, double D, double setpoint, double tolerance) {
        this(P, I, D, setpoint);
        toleranceMode = true;
        super.getPIDController().setAbsoluteTolerance(tolerance);
    }

    /**
     * Sets the PID values for the inherited PIDCommand, and the setpoint
     * @param P the proportional error
     * @param I the integral error
     * @param D the derivative error
     * @param setpoint the setpoint you want to go to
     */
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

    /**
     * @return the error value of the PID loop
     */
    protected double getError() {
        return getSetpoint() - returnPIDInput();
    }
  

    @Override
    protected boolean isFinished() {
        if (toleranceMode) {
            return super.getPIDController().onTarget();
        } else {
            return forward ? getError() <= 0 : getError() >= 0;
        }
    }

    public void setConstants(double P, double I, double D) {
        setP(P);
        setI(I);
        setD(D);
    }

    /**
     * sets the P value for the PID loop
     * @param P the P value for the PID loop
     */
    public void setP(double P) {
        this.P = P;
        getPIDController().setP(P);
    }

    /**
     * sets the I value of the PID loop
     * @param I the I value for the PID loop
     */
    public void setI(double I) {
        this.I = I;
        getPIDController().setI(I);
    }

    /**
     * sets the D value of the PID loop
     * @param D the D value for the PID loop
     */
    public void setD(double D) {
        this.D = D;
        getPIDController().setD(D);
    }

    /**
     * @return the P value of the PID loop
     */
    public double getP() {
        return P;
    }

    /**
     * @return the I value of the PID loop
     */
    public double getI() {
        return I;
    }

    /**
     * @return the D value of the PID loop
     */
    public double getD() {
        return D;
    }
}