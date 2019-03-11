package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.helpers.Helper;

public class ArcadeDrive extends Command {


    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }


    @Override
    protected void execute() {
        double y = Helper.deadzone(-OI.driver.getLY(), .1);
        double x = Helper.deadzone(-OI.driver.getRX(), .1);
        if(!Robot.powerPack.isElevatorMode())   {
            y = y*.25;
            x = x*.25;
        }
        
        Robot.drivetrain.setSpeed(y - x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}