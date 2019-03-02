package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.carriage.tilt.Tilt;

public class AutoElevatorTilt extends CommandGroup {

    public enum State {
        intakeBall, hatchLower, ballLower, hatchMiddle, ballMiddle, hatchUpper, ballUpper;
    }
    
    public AutoElevatorTilt(State state) {
        // if (state == State.ballUpper) {
        //     addSequential(new SetTiltPosition(Tilt.UPPER));
        // } else if (state == State.intakeBall) {
        //     addSequential(new SetTiltPosition(Tilt.LOWER));
        // } else {
        //     addSequential(new SetTiltPosition(Tilt.MIDDLE));
        // }
        
        if (state == State.intakeBall) {
            addSequential(new SetElevatorPosition(PowerPack.INTAKE_BALL));
        } else if (state == State.hatchLower || state == State.ballLower) {
            addSequential(new SetElevatorPosition(PowerPack.LOWER));
        } else if (state == State.ballMiddle || state == State.hatchMiddle) {
            addSequential(new SetElevatorPosition(PowerPack.MIDDLE)); 
        } else if (state == State.ballUpper || state == State.hatchUpper) {
            addSequential(new SetElevatorPosition(PowerPack.UPPER));   
        }
    }
}