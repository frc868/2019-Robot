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
    }

    @Override
    protected void execute() {
        Robot.powerPack.setSpeed(-OI.operator.getLY());
    }

    @Override
    protected void end() {
        // Robot.powerPack.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}