package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class TriggerIntakeControl extends Command {

    public TriggerIntakeControl() {
        requires(Robot.ballIntake);
    }

    @Override
    protected void execute() {
        double power = OI.operator.getRT() - OI.operator.getLT() + OI.driver.getRT() - OI.driver.getLT();
        Robot.ballIntake.setSpeed(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}