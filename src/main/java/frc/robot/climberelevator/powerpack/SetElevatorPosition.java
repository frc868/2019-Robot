package frc.robot.climberelevator.powerpack;

import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class SetElevatorPosition extends PIDCommandPlus {
    public static final double P = 1.0, I = 0.0, D = 0.0;
    public static final double INTAKE_BALL = 0, GET_FROM_GROUND_PICKUP = 0,
        LOWER_HATCH = 1, LOWER_BALL = 2, 
        MIDDLE_HATCH = 3, MIDDLE_BALL = 4, 
        UPPER_HATCH = 5, UPPER_BALL = 6;

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