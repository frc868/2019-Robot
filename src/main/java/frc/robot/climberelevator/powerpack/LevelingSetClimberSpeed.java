package frc.robot.climberelevator.powerpack;

import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class LevelingSetClimberSpeed extends PIDCommandPlus {
    private double targetSpeed;
    private static final double P = 1.0, I = 0.0, D = 0.0;

    public LevelingSetClimberSpeed(double targetSpeed) {
        this(targetSpeed, P, I, D);
    }

    public LevelingSetClimberSpeed(double targetSpeed, double kP, double kI, double kD) {
        super(kP, kI, kD, 0);
        requires(Robot.powerPack);
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToClimber();
        Robot.powerPack.climberBrakeOff();
        Robot.powerPack.setSpeed(targetSpeed);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.gyro.getPitch();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.powerPack.setSpeed(targetSpeed + output);
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
        Robot.powerPack.climberBrakeOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}