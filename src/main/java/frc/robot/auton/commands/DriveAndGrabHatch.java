package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.drivetrain.commands.RunProfile;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndGrabHatch extends CommandGroup {

    public DriveAndGrabHatch(String filename) {
        addSequential(new RunProfile(filename));
        // addSequential(new FollowVisionAndUltrasonic());
        addSequential(new AutoElevatorTilt(AutoElevatorTilt.State.hatchLower));
        addSequential(new Grab());
    }
}