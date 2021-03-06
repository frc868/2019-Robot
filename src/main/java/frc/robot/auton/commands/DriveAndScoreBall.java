package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.ballintake.SetBallIntakeSpeed;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.drivetrain.commands.RunProfile;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndScoreBall extends CommandGroup {

    public enum Height {
        lower, middle, upper
    }
    
    public DriveAndScoreBall(String filename, boolean backwards, Height height){
        this(new RunProfile(filename), height);
    }

    public DriveAndScoreBall(Command command, Height height) {
        addSequential(command);
        // // addSequential(new FollowVisionAndUltrasonic());
        
         if (height == Height.lower) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.lower));
        } else if (height == Height.middle) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.middle));
        } else if (height == Height.upper) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.upper));
        }

        addSequential(new SetBallIntakeSpeed());
    }
}