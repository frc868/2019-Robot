package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetTiltPosition extends PIDCommandPlus {
    // PID constants
    private static final double P = 20.0, I = 2, D = 10;
    private int counts = 0;
    private final int COUNTS_NEEDED = 10;

    // private static final double P_HATCH = 0, I_HATCH = 0, D_HATCH = 0;
    // private static final double P_BALL = 0, I_BALL = 0, D_BALL = 0;
    // private static final double P_EMPTY = 0, I_EMPTY = 0, D_EMPTY = 0;


    /**
     * sets tilt to given position
     * @param setpoint setpoint for tilt
     */
    public SetTiltPosition(double setpoint) {
        super(P, I, D, setpoint); // sets PID constants and setpoint
        requires(Robot.tilt);
        if(setpoint == Robot.tilt.UPPER){
            Robot.tilt.limitPower = true;
        }else{
            Robot.tilt.limitPower = false;
        }
    }

    @Override
    protected void initialize() {
        Robot.tilt.brakeOff();

        SmartDashboard.putNumber("Tilt Setpoint", getSetpoint());

        // if (Robot.ballIntake.isBallDetected()) {
        //     setConstants(P_BALL, I_BALL, D_BALL);
        // } else if (Robot.hatchClaw.isGrabbed()) {
        //     setConstants(P_HATCH, I_HATCH, D_HATCH);
        // } else {
        //     setConstants(P_EMPTY, I_EMPTY, D_EMPTY);
        // }

        getPIDController().setAbsoluteTolerance(0.005);
        // getPIDController().setToleranceBuffer(100P);
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
        return Robot.tilt.getPotPosition(); // input to PID is pot position
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(Helper.boundValue(-output, -0.3, 0.5)); // output of PID is negated and set to tilt motor
    }

    @Override
    protected boolean isFinished()  {
        // never end
        return false;
    }

    @Override
    protected void end() {
        // Robot.tilt.brakeOn();
    }
}