package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;

public class SmartSetElevatorPosition extends CommandGroup {

    public static enum Height {
        ballIntake, lower, middle, upper;
    }

    public SmartSetElevatorPosition(Height height) {
        if (height == Height.ballIntake) {
            addSequential(new SetElevatorPosition(PowerPack.INTAKE_BALL));
            addSequential(new SetTiltPosition(Tilt.LOWER));
        } else if (height == Height.lower) {
            if (Robot.ballIntake.isBallDetected()) {
                addSequential(new SetElevatorPosition(PowerPack.LOWER_BALL));
                addSequential(new SetTiltPosition(Tilt.UPPER));
            } else {
                addSequential(new SetElevatorPosition(PowerPack.LOWER_HATCH));
            }
        } else if (height == Height.middle) {
            if (Robot.ballIntake.isBallDetected()) {
                addSequential(new SetElevatorPosition(PowerPack.MIDDLE_BALL));
                addSequential(new SetTiltPosition(Tilt.UPPER));
            } else {
                addSequential(new SetElevatorPosition(PowerPack.MIDDLE_HATCH));
            }
        } else if (height == Height.upper) {
            if (Robot.ballIntake.isBallDetected()) {
                addSequential(new SetElevatorPosition(PowerPack.UPPER_BALL));
                addSequential(new SetTiltPosition(Tilt.UPPER));
            } else {
                addSequential(new SetElevatorPosition(PowerPack.UPPER_HATCH));
            }
        }
    }
}