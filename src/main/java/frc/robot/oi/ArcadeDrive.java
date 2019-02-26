package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.helpers.oi.XboxControllerPlus;

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrainNEO);
    }

    @Override
    protected void execute() {
        double y = XboxControllerPlus.cube(OI.driver.getLY());
        double x = XboxControllerPlus.cube(OI.driver.getRX());
        Robot.drivetrainNEO.setSpeed(-y + x, y + x);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}