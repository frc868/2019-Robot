package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
// import frc.robot.climberelevator.powerpack.AutoElevatorTilt;
import frc.robot.drivetrain.commands.RunProfile;
// import frc.robot.sensors.FollowVisionAndUltrasonic;

public class DriveAndGrabHatch extends CommandGroup {

    /**
     * @param driveCommand the command/command group we are using to drive the bot
     */
    public DriveAndGrabHatch(Command driveCommand) {
        addSequential(driveCommand); // drive
        // addSequential(new FollowVisionAndUltrasonic()); // line up using auton
        addSequential(new SetElevatorPosition(PowerPack.LOWER)); // lower elevator
        addSequential(new Grab()); // grab hatch
    }

    /**
     * @param filename file name of the path we are running
     */
    public DriveAndGrabHatch (String filename) {
        this(new RunProfile(filename)); // runs this command group with the motion profile given
    }
}