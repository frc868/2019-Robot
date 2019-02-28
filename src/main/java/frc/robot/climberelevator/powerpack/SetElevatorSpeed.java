package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetElevatorSpeed extends Command {
    private double targetSpeed;

    public SetElevatorSpeed(double targetSpeed) {
        requires(Robot.powerPack);
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void initialize() {
        Robot.powerPack.switchToElevator();
        Robot.powerPack.setSpeed(targetSpeed);
        Robot.powerPack.elevatorBrakeOff();
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