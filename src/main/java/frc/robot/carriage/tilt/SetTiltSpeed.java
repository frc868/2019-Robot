package frc.robot.carriage.tilt;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetTiltSpeed extends Command {
    private double speed; // target speed for tilt

    /**
     * sets tilt to given speed
     * @param speed to set tilt to 
     */
    public SetTiltSpeed(double speed) {
        requires(Robot.tilt);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        // sets tilt to speed
        Robot.tilt.setSpeed(speed);
    }

    @Override
    protected void end() {
        // stops tilt motor
        Robot.tilt.stop();
    }

    @Override
    protected boolean isFinished() {
        // never end
        return false;
    }

}