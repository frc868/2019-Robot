package frc.robot.climberelevator.powerpack.smartsetelevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;


public class BallOrHatch extends ConditionalCommand {

    public BallOrHatch(Command hatchCommand, Command ballCommand) {
        super(hatchCommand, ballCommand);
    }

    @Override
    protected boolean condition() {
        return !Robot.ballIntake.isBallDetected();
    }
}
