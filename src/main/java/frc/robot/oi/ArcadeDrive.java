package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ArcadeDrive extends Command {
    public static boolean climber_speed_reduction = false;

    public static void toggleClimberSpeedReduction() {
        climber_speed_reduction = !climber_speed_reduction;
    }

    public static class ToggleClimberSpeedReduction extends InstantCommand {
        @Override
        protected void initialize() {
            toggleClimberSpeedReduction();
        }
    }


    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }


    @Override
    protected void execute() {
        double y = Helper.deadzone(-OI.driver.getLY(), .03);
        double x = Helper.deadzone(-OI.driver.getRX(), .03);

        boolean leftFlag = false;
        boolean rightFlag = false;


        if(!Robot.powerPack.isElevatorMode())   {// slow for climbing
            if(climber_speed_reduction) {
                y = y*.25;
                x = x*.25;
            }

            if(Robot.powerPack.getClimberLimitSwitch()){

                if(Robot.powerPack.getLeftUltrasonicValue() > Robot.powerPack.ULTRASONIC_HAB_HEIGHT){
                    Robot.drivetrain.setLeftSpeed(Helper.boundValue(y-x, -1, 0));
                }   else  if(Robot.powerPack.getRightUltrasonicValue() > Robot.powerPack.ULTRASONIC_HAB_HEIGHT)   {
                    Robot.drivetrain.setRightSpeed(Helper.boundValue(y+x, -1, 0));
                }   else    {
                    Robot.drivetrain.setSpeed(y-x,y+x);
                }
            }
            
        } else if(OI.driver.rb.get())   {//Josh's sniper button
            x = x*.5;
        }
        if(Robot.powerPack.isElevatorMode())    {
            Robot.drivetrain.setSpeed(y - x, y + x);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}