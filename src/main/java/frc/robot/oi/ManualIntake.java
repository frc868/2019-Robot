package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ManualIntake extends Command {

    public ManualIntake() {
        requires(Robot.ballIntake);
    }

    @Override
    protected void execute() {
        double power = OI.operator.getRT() - OI.operator.getLT();
        Robot.ballIntake.setSpeed(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}