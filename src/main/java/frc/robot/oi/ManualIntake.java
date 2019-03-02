package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class ManualIntake extends Command {

    public ManualIntake() {
        requires(Robot.ballIntake);
    }

    @Override
    protected void execute() {
        double power = OI.operator.getLT() - OI.operator.getRT();
        Robot.ballIntake.setSpeed(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}