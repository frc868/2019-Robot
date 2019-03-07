package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.helpers.Helper;
import frc.robot.helpers.oi.XboxControllerPlus;

public class ArcadeDrive extends Command {

    public boolean isSlow;

    public ArcadeDrive() {
        this.isSlow = false;
        requires(Robot.drivetrain);
    }

    public ArcadeDrive(boolean isSlow){
        this.isSlow = isSlow;
    }

    @Override
    protected void execute() {
        double y = -OI.driver.getLY();
        double x = -OI.driver.getRX();
        y = isSlow ? (y * 0.25) : y;
        x = isSlow ? (x * 0.25) : x;
        Robot.drivetrain.setSpeed(y - x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}