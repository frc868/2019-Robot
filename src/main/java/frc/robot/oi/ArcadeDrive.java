package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ArcadeDrive extends Command{
    
    /**
     * defalt command for running teleop drive train for the driver
     */
    public ArcadeDrive(){
        requires(Robot.drivetrain);
    }
    
    @Override
    protected void execute(){
        double y = Helper.deadzone(-OI.driver.getLY(), .03);
        double x = Helper.deadzone(-OI.driver.getRX(), .03);
        if(!Robot.powerPack.isElevatorMode()){       //slow for climbing
            y = y * .25;
            x = x * .25;
        }
        
        if(OI.driver.rb.get()){      //Josh's sniper button
            if(x > 0){      //this is in case we change it to an even power
                x = Math.abs(Math.pow(x, 0.55555555555555555555));
            }
            if(x < 0){
                x = -Math.abs(Math.pow(x, 0.55555555555555555555));
            }
        }
        
        Robot.drivetrain.setSpeed(y - x, y + x);
    }
    
    @Override
    protected boolean isFinished(){
        return false;
    }
}