package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;    
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {
    public static final double P = 0.08 , I = 0.000, D = 0.00;
    private double setpoint;

    public SetElevatorPosition(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.powerPack);
        this.setpoint = setpoint;
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        getPIDController().setAbsoluteTolerance(.1);
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
        (new SetTiltPosition(Tilt.MIDDLE)).start();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Error", getError());
    }

    @Override
    protected double returnPIDInput() {
        return Robot.powerPack.getEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.powerPack.setSpeed(output);
    }

    @Override
    protected void end() {
        if(setpoint == Robot.powerPack.UPPER_BALL){
            (new SetTiltPosition(Robot.tilt.UPPER)).start();
        } else if (setpoint == Robot.powerPack.INTAKE_BALL) {
            (new SetTiltPosition(Robot.tilt.LOWER)).start();
        }
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }
}