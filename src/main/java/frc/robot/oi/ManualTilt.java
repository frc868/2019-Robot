package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.helpers.Helper;

public class ManualTilt extends Command {

    public ManualTilt() {
        requires(Robot.tilt);
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getRY(), 0.05);
        Robot.tilt.setSpeed(input);
        SmartDashboard.putNumber("tilt Power", -OI.operator.getRY());
        if(input == 0)  {
            Robot.tilt.setSpeed(.1);
        }
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