package frc.robot.carriage.ballintake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetBallIntakeSpeed extends Command {
    private double speed; // speed to set the intake to 
    private static final double DEFAULT_POWER = 1.0; // the default power to set the intake to 

    /**
     * sets intake to default power, stops intake when ended
     */
    public SetBallIntakeSpeed() {
        this(DEFAULT_POWER);
    }

    /**
     * sets intake to given power, stops intake when ended
     * @param speeed speed to set intake to
     */
    public SetBallIntakeSpeed(double speed) {
        requires(Robot.ballIntake);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        // set the motor to speed
        Robot.ballIntake.setSpeed(speed);
    }

    @Override
    protected void end() {
        // stop the motor
        Robot.ballIntake.stop();
    }
    
    @Override
    protected boolean isFinished() {
        // never finish
        return false;
    }
}