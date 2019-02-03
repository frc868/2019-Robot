package frc.robot.powerpack.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetClimberSpeed extends Command {
    private double targetSpeed;

    public SetClimberSpeed(double targetSpeed) {
        requires(Robot.powerPack);
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToClimber();
        Robot.powerPack.setSpeed(targetSpeed);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}