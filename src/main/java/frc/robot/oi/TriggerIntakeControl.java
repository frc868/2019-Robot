package frc.robot.carriage.ballintake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class TriggerIntakeControl extends Command {

    public TriggerIntakeControl() {
        requires(Robot.ballIntake);
    }

    @Override
    protected void execute() {
        if (OI.operator.getRT() != 0) {
            Robot.ballIntake.setSpeed(OI.operator.getRT());
        } else if (OI.operator.getLT() != 0) {
            Robot.ballIntake.setSpeed(-OI.operator.getLT());
        } else if (OI.driver.getRT() != 0) {
            Robot.ballIntake.setSpeed(OI.driver.getRT());
        } else if (OI.driver.getLT() != 0) {
            Robot.ballIntake.setSpeed(-OI.driver.getLT());
        } else {
            Robot.ballIntake.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}