package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetGroundPickupIntakeSpeed extends Command {
    private double power;
    private static final double DEFAULT_POWER = 1.0;

    public SetGroundPickupIntakeSpeed() {
        this(DEFAULT_POWER);
    }
    
    public SetGroundPickupIntakeSpeed(double power) {
        requires(Robot.groundPickup);
        this.power = power;
    }

    @Override
    protected void initialize() {
        Robot.groundPickup.setIntakeSpeed(power);
    }

    @Override
    protected void end() {
        Robot.groundPickup.stopIntake();
    }

    @Override
    protected void interrupted() {
        end();
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}