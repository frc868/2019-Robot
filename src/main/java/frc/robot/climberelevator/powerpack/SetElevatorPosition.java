package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;    
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {
    public static final double P = 0.08 , I = 0.000, D = 0.00;

    public SetElevatorPosition(double setpoint) {
        super(P, I, D, setpoint, .1);
        requires(Robot.powerPack);
    }
    
    @Override
    protected void initialize() {
        (new SetTiltPosition(Tilt.MIDDLE)).start();
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Elevator Error", getError());
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
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }
}