package frc.robot.carriage.ballintake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetBallIntakeSpeed extends Command {
    private double speed; // speed to set the intake to 
    private static final double DEFAULT_POWER = 1.0; // the default power to set the intake to 

    /**
     * sets intake to provided power, stops intake when ended
     * power is default power
     */
    public SetBallIntakeSpeed() {
        this(DEFAULT_POWER);
    }

    public SetBallIntakeSpeed(double speed) {
        requires(Robot.ballIntake);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.ballIntake.setSpeed(speed);
    }

    @Override
    protected void end() {
        Robot.ballIntake.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}