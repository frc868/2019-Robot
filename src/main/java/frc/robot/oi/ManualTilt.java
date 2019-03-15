package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.helpers.Helper;

public class ManualTilt extends Command {

    public ManualTilt() {
        requires(Robot.tilt);
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getRY(), 0.05);
        Robot.tilt.setSpeed(input);
    }

    @Override
    protected void end() {
        Robot.tilt.stop();
        (new SetTiltPosition(Robot.tilt.getPotPosition())).start(); // maintain current position
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}