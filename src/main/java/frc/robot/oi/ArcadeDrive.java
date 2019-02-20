package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        Robot.drivetrain.setSpeed(-OI.driver.getLY() + OI.driver.getRX(), OI.driver.getLY() + OI.driver.getRX());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}