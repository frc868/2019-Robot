package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;

public class AutoElevatorTilt extends CommandGroup {

    public enum State {
        intakeBall, hatchLower, ballLower, hatchMiddle, ballMiddle, hatchUpper, ballUpper;
    }
    
    public AutoElevatorTilt(State state) {
        if (state == State.ballUpper) {
            addSequential(new SetTiltPosition(SetTiltPosition.UPPER));
        } else if (state == State.intakeBall) {
            addSequential(new SetTiltPosition(SetTiltPosition.LOWER));
        } else {
            addSequential(new SetTiltPosition(SetTiltPosition.MIDDLE));
        }
        
        if (state == State.intakeBall) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.INTAKE_BALL));
        } else if (state == State.hatchLower) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.LOWER_HATCH));
        } else if (state == State.ballLower) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.LOWER_BALL)); 
        } else if (state == State.hatchMiddle) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.MIDDLE_HATCH));   
        } else if (state == State.ballMiddle) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.MIDDLE_BALL));   
        } else if (state == State.hatchUpper) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.UPPER_HATCH));   
        } else if (state == State.ballUpper) {
            addSequential(new SetElevatorPosition(SetElevatorPosition.UPPER_BALL));   
        }
    }
}