package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualIntake extends Command {
    private double KEEP_BALL_POWER = 0;

    public ManualIntake() {
        requires(Robot.ballIntake);
    }

    @Override
    protected void execute() {
        double power = Helper.deadzone(OI.operator.getLT() - OI.operator.getRT() + OI.driver.getLT() - OI.driver.getRT(), 0.1);
        if (Robot.ballIntake.isBallDetected() && power == 0) {
            Robot.ballIntake.setSpeed(KEEP_BALL_POWER);
        } else {
            Robot.ballIntake.setSpeed(power);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}