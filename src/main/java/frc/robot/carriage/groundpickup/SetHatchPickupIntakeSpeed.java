package frc.robot.carriage.groundpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetHatchPickupIntakeSpeed extends Command {
    private double speed;

    public SetHatchPickupIntakeSpeed(double speed) {
        requires(Robot.groundPickup);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.groundPickup.setIntakeSpeed(speed);
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