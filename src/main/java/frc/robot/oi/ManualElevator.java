package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ManualElevator extends Command {

    public ManualElevator() {
        requires(Robot.powerPack);
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToElevator();
        Robot.powerPack.elevatorBrakeOff();
    }

    @Override
    protected void execute() {
        if(-0.1 < -OI.operator.getLY() > 0.1) {
            Robot.powerPack.setSpeed(0);
            Robot.powerPack.elevatorBrakeOn();
        }   else    {
            Robot.powerPack.elevatorBrakeOff();
            Robot.powerPack.setSpeed(-OI.operator.getLY());
        }
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
        Robot.powerPack.elevatorBrakeOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}