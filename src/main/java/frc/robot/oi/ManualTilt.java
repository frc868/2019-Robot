package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualTilt extends Command {

    public ManualTilt() {
        requires(Robot.tilt);
    }

    @Override
    protected void initialize() {
        Robot.tilt.brakeOff();
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getRY(), 0.05);
        Robot.tilt.setSpeed(input);
    }

    @Override
    protected void end() {
        Robot.tilt.stop();
        // (new BrakeTilt()).start(); // maintain current position
        Robot.tilt.brakeOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}