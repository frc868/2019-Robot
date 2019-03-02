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
        // Robot.powerPack.elevatorBrakeOff();
    }

    @Override
    protected void execute() {
        if( (-0.05 < OI.operator.getLY()) && (OI.operator.getLY() < 0.05)) {     //create deadzone & brake in deadzone
            Robot.powerPack.setSpeed(0);
            Robot.powerPack.elevatorBrakeOn();
        }   else    {                               //normal elevator movement
            Robot.powerPack.elevatorBrakeOff();
            Robot.powerPack.setSpeed(-OI.operator.getLY());
        }
    }

    @Override
    protected void end() {
        Robot.powerPack.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}