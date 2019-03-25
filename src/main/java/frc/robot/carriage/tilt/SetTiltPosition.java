package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetTiltPosition extends PIDCommandPlus {
    // PID constants
    private static final double P = 16.0, I = 0.09, D = 0.9;

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

        getPIDController().setOutputRange(-0.3, 0.5);
    }

    @Override
    protected void initialize() {
        // Robot.tilt.brakeOff();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Tilt Error", getError());
    }

    @Override
    protected double returnPIDInput() {
        return Robot.tilt.getPotPosition(); // input to PID is pot position
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.tilt.setSpeed(-output); // output of PID is negated and set to tilt motor
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