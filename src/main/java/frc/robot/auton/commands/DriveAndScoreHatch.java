package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.drivetrain.commands.RunProfile;
import frc.robot.sensors.camera.FollowVision;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndScoreHatch extends CommandGroup {

    public enum Height {
        lower, middle, upper
    }
    
    public DriveAndScoreHatch(String filename, boolean backwards, Height height){
        this(new RunProfile(filename, backwards), height);
    }

    public DriveAndScoreHatch(Command command, Height height) {
        System.out.println("adding runprofile");
        addSequential(command);
        
        if (height == Height.lower) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.lower));
        } else if (height == Height.middle) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.middle));
        } else if (height == Height.upper) {
            addSequential(new SmartSetElevatorPosition(SmartSetElevatorPosition.Height.upper));
        }
        addSequential(new FollowVision(), 2000);

        addSequential(new Release());
    }
}