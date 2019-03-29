package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrabWhenTiltUp extends Command{
    
    private final int COUNTS_NEEDED = 1; // counts required to determine that a hatch is detected
    private final double ANGLE_UP_NEEDED = -0.025;
    private int counts = 0; // current number of counts
    private double initialReading;
    
    /**
     * command to grab hatch when it is detected by sensors
     */
    public GrabWhenTiltUp(){
        requires(Robot.hatchClaw);
    }
    
    @Override
    protected void initialize(){
        Robot.hatchClaw.release(); // closes hatch claw in order to grab a hatch 
        initialReading = Robot.tilt.getPotPosition();
    }
    
    @Override
    protected void execute(){
        if((Robot.tilt.getPotPosition() - initialReading) < ANGLE_UP_NEEDED){ // if we detect hatch, increment count
            counts++;
        }else{ // if not, reset count
            counts = 0;
        }
    }
    
    @Override
    protected boolean isFinished(){
        // return true if we have enough counts
        return counts >= COUNTS_NEEDED;
    }
    
    @Override
    protected void end(){
        // grab hatch
        Robot.hatchClaw.grab();
    }
    
    @Override
    protected void interrupted(){
        // don't do anything (if we don't overide it will end() when interupted)
    }
}
