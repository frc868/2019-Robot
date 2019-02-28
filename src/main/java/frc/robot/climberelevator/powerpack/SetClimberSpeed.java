package frc.robot.climberelevator.powerpack;

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
        Robot.powerPack.brakesOff();
        Robot.powerPack.setSpeed(targetSpeed);
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
        Robot.powerPack.brakesOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}