package frc.robot.climberelevator.powerpack;

import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {
    public static final double P = .05, I = 0.0, D = 0.0;

    public SetElevatorPosition(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.powerPack);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        getPIDController().setPercentTolerance(0.01);
        getPIDController().setToleranceBuffer(50);
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
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
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }
}