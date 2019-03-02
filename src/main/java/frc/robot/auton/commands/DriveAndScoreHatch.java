package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.drivetrain.commands.RunProfile;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndScoreHatch extends CommandGroup {

    public enum Height {
        lower, middle, upper
    }

    public DriveAndScoreHatch(String filename, Height height) {
        addSequential(new RunProfile(filename));
        // addSequential(new FollowVisionAndUltrasonic());
        
        if (height == Height.lower) {
            addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.hatchLower));
        } else if (height == Height.middle) {
            addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.hatchMiddle));
        } else if (height == Height.upper) {
            addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.hatchUpper));
        }

        addSequential(new Release());
    }
}