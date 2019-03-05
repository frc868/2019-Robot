package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {

    public static final double P = .07, I = 0.00, D = 0.001, tolerance = 1;

    public SetElevatorPosition(double setpoint) {
        super(P, I, D, setpoint, tolerance);
        requires(Robot.powerPack);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
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
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }
}