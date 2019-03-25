package frc.robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.helpers.Helper;

//TODO make this a command groups, don't know why it's like that
public class ManualClimber extends Command {

    public ManualClimber() {
        requires(Robot.powerPack);
        requires(Robot.footDrive);
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToClimber();
        (new SetTiltPosition(Tilt.UPPER)).start();
        (new Grab()).start();
    }

    @Override
    protected void execute() {
        double input = Helper.deadzone(-OI.operator.getLY(), 0.1); //deadzone so the brake knows when to enable
        Robot.powerPack.setSpeed(input);

        if(input == 0) { 
            Robot.powerPack.climberBrakeOn();
        } else {
            Robot.powerPack.climberBrakeOff();
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