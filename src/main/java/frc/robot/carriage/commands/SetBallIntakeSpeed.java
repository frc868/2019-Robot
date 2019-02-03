package frc.robot.carriage.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetBallIntakeSpeed extends Command {
    private double speed;

    public SetBallIntakeSpeed(double speed) {
        requires(Robot.ballIntake);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.ballIntake.setSpeed(speed);
    }
    
    @Override
    protected boolean isFinished() {
        return true; //TODO fix this
    }
}