package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualTilt extends Command {

    public ManualTilt() {
        requires(Robot.tilt);
    }

    @Override
    protected void execute() {
        // double input = Helper.deadzone(-OI.operator.getRY(), 0.05);
        double input = 0;
        Robot.tilt.setSpeed(input);
    }

    @Override
    protected void end() {
        Robot.tilt.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}