package frc.robot.climberelevator.powerpack;

import frc.robot.Robot;
import frc.robot.helpers.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {
    public static final double P = 1.0, I = 0.0, D = 0.0;
    public final double LOWER = 0, MIDDLE = 1, UPPER = 2;

    public SetElevatorPosition(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.powerPack);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        Robot.powerPack.switchToElevator();
    }

    @Override
    protected double returnPIDInput() {
        return Robot.powerPack.getEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.powerPack.setSpeed(output);
    }
}