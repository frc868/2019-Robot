package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class ManualElevator extends Command {
    private boolean trainingMode;
    public ManualElevator(boolean trainingMode) {
        this.trainingMode = trainingMode;
        requires(Robot.powerPack);
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToElevator();
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getLY(), 0.1);      //deadzone to allow the brake to know when to enable
        if(trainingMode) input = input*0.5;
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