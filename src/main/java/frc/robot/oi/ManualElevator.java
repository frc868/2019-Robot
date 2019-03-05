package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.helpers.Helper;

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
        double input = Helper.deadzone(-OI.operator.getLY(), 0.1);
        Robot.powerPack.setSpeed(input);

        if(input == 0) { 
            Robot.powerPack.elevatorBrakeOn();
        } else {
            Robot.powerPack.elevatorBrakeOff();
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