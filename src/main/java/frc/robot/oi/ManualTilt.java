package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ManualTilt extends Command {

    public ManualTilt() {
        requires(Robot.tilt);
    }

    @Override
    protected void execute() {
        Robot.tilt.setSpeed(-OI.operator.getRY());
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