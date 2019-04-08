package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetTiltPositionFeedforward extends PIDCommandPlus {
    // PID constants
    private final int COUNTS_NEEDED = 10;
    private int counts =  0;
    private static final double P = 20.0, I = 1, D = 10, F = 1;

    /**
     * sets tilt to given position
     * @param setpoint setpoint for tilt
     */
    public SetTiltPositionFeedforward(double setpoint) {
        super(P, I, D, setpoint); // sets PID constants and setpoint
        requires(Robot.tilt);

        if(setpoint == Robot.tilt.UPPER){
            Robot.tilt.limitPower = true;
        } else{
            Robot.tilt.limitPower = false;
        } 

        getPIDController().setAbsoluteTolerance(0.005);
    }

    @Override
    protected void initialize() {
        Robot.tilt.brakeOff();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Tilt Error", getError());

        if (getPIDController().onTarget()) {
            counts++;
        } else {
            counts = 0;
        }

        if (counts >= COUNTS_NEEDED) {
            Robot.tilt.brakeOn();
        }
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getAngle(); // input to PID is intake angle
    }

    @Override
    protected void usePIDOutput(double pidoutput) {
        double foutput = F*Math.cos(Robot.tilt.getAngle());
        Robot.tilt.setSpeed(Helper.boundValue(foutput + pidoutput, -0.3, 0.5));
    }

    @Override
    protected boolean isFinished()  {
        return false;
    }
}