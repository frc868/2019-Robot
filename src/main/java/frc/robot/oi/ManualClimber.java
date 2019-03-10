package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualClimber extends Command {

    public ManualClimber() {
        requires(Robot.powerPack);
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToClimber();
        Robot.powerPack.climberBrakeOff();
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getLY(), 0.1);
        Robot.powerPack.setSpeed(input);

        if(input == 0) { 
            Robot.powerPack.elevatorBrakeOn();
        } else {
            Robot.powerPack.elevatorBrakeOff();
        }

        Robot.footDrive.setSpeed(-OI.operator.getRY());
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
        Robot.powerPack.climberBrakeOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}