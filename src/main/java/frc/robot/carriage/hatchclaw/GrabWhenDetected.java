package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrabWhenDetected extends Command{
    
    private final int COUNTS_NEEDED = 5; // counts required to determine that a hatch is detected
    private int counts = 0; // current number of counts
    
    /**
     * command to grab hatch when it is detected by sensors
     */
    public GrabWhenDetected(){
        requires(Robot.hatchClaw);
    }
    
    @Override
    protected void initialize(){
        Robot.hatchClaw.release(); // closes hatch claw in order to grab a hatch 
    }
    
    @Override
    protected void execute(){
        if(Robot.hatchClaw.isHatchDetected()){ // if we detect hatch, increment count
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
