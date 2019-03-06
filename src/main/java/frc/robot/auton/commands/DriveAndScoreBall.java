package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.ballintake.SetBallIntakeSpeed;
// import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.drivetrain.commands.RunProfile;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndScoreBall extends CommandGroup {

    public enum Height {
        lower, middle, upper
    }

    public DriveAndScoreBall(String filename, Height height) {
        addSequential(new RunProfile(filename));
        // // addSequential(new FollowVisionAndUltrasonic());
        
        // if (height == Height.lower) {
        //     addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.lower));
        // } else if (height == Height.middle) {
        //     addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.middle));
        // } else if (height == Height.upper) {
        //     addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.upper));
        // }

        addSequential(new SetBallIntakeSpeed());
    }
}