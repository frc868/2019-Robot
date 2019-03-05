package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.carriage.tilt.Tilt;

public class AutoElevatorTilt extends CommandGroup {

    public enum State {
        intakeBall, lower, middle, upper;
    }
    
    public AutoElevatorTilt(State state) {
        if (state == State.upper) {
            addSequential(new SetTiltPosition(Tilt.UPPER));
        } else {
            addSequential(new SetTiltPosition(Tilt.MIDDLE));
        }
        
        if (state == State.intakeBall) {
            addSequential(new SetElevatorPosition(PowerPack.INTAKE_BALL));
        } else if (state == State.lower) {
            addSequential(new SetElevatorPosition(PowerPack.LOWER));
        } else if (state == State.middle) {
            addSequential(new SetElevatorPosition(PowerPack.MIDDLE)); 
        } else if (state == State.upper) {
            addSequential(new SetElevatorPosition(PowerPack.UPPER));   
        }
    }
}