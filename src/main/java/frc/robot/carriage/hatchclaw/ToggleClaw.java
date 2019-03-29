package frc.robot.carriage.hatchclaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleClaw extends Command{
    
    /**
     * toggles hatch claw between the two states
     */
    public ToggleClaw(){
        requires(Robot.hatchClaw);
    }
    
    @Override
    protected void initialize(){
        if(Robot.hatchClaw.isGrabbed()){ // if hatch is grabbed, release
            Robot.hatchClaw.release();
        }else{ // if hatch is released, grab
            Robot.hatchClaw.grab();
        }
    }
    
    @Override
    protected boolean isFinished(){
        // finish immediately
        return true;
    }
}
