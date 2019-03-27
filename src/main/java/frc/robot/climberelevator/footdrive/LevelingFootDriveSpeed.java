package frc.robot.climberelevator.footdrive;

import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class LevelingFootDriveSpeed extends PIDCommandPlus {
    private static final double P = 1.0, I = 0.0, D = 0.0;


    public LevelingFootDriveSpeed(double setpoint) {
        super(P, I, D, setpoint);
        requires(Robot.footDrive);
    }

    @Override
    protected void initialize() {
        Robot.footDrive.setSpeed(0);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.gyro.getPitch();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.powerPack.setSpeed(output);
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