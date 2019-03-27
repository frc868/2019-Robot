package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Release extends Command{
    
    /**
     * closes the hatch claw to release a hatch
     */
    public Release(){
        requires(Robot.hatchClaw);
    }
    
    @Override
    protected void initialize(){
        // releases a hatch
        Robot.hatchClaw.release();
    }
    
    @Override
    protected boolean isFinished(){
        // end immediately
        return true;
    }
}
