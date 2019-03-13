package frc.robot.climberelevator.footdrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;
import frc.robot.oi.OI;

public class SetFootDriveSpeed extends Command {
    private double speed;

    public SetFootDriveSpeed(double speed) {
        requires(Robot.footDrive);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double input = (1.5*-OI.operator.getLY()) + OI.operator.getRY();
        Robot.footDrive.setSpeed(input);
        SmartDashboard.putNumber("Foot Speed", input);
        
        // Robot.footDrive.setSpeed(-Helper.boundValue(.5(OI.operator.getLY())      //TODO: test this
        //     +.5*(Helper.deadzone(OI.operator.getRY(), .1)), 0, 1));
    }

    @Override
    protected void end() {
        Robot.footDrive.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}