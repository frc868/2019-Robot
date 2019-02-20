package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.helpers.XboxControllerPlus;

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double y = XboxControllerPlus.cube(OI.driver.getLY());
        double x = XboxControllerPlus.cube(OI.driver.getRX());
        Robot.drivetrain.setSpeed(-y + x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}