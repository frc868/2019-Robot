package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.helpers.oi.XboxControllerPlus;

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double y = -OI.driver.getLY();
        double x = OI.driver.getRX();
        Robot.drivetrain.setSpeed(y - x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}