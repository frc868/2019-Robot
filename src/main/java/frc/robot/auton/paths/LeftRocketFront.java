// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.auton.paths;

// import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.carriage.hatchclaw.Release;
// import frc.robot.carriage.tilt.SetTiltPosition;
// import frc.robot.carriage.tilt.Tilt;
// import frc.robot.climberelevator.powerpack.PowerPack;
// import frc.robot.climberelevator.powerpack.SetElevatorPosition;
// import frc.robot.drivetrain.commands.DriveStraight;
// import frc.robot.drivetrain.commands.DriveStraightRamp;
// import frc.robot.sensors.camera.FollowVision;

// public class LeftRocketFront extends CommandGroup {
//   /**
//    * Place a Hatch on the left rocket close side, starting from left side of hab 1
//    */
//   public LeftRocketFront() {

//     // Set Tilt and Elevator
//     addParallel(new SetTiltPosition(Tilt.MIDDLE));
//     addParallel(new SetElevatorPosition(PowerPack.LOWER_HATCH));

//     // Drive to the Rocket
//     addSequential(new DriveStraightRamp(50, .2, .7)); //TODO: tune all values
//     addSequential(new DriveStraight(84, .7, -28.75));
//     addSequential(new DriveStraightRamp(50, .7, .2));

//     // Follow vision into the rocket and score hatch
//     addSequential(new FollowVision(), 3);  //TODO: make shorter after testing
//     addSequential(new Release());

//     // Back out
//     addSequential(new DriveStraight(-20, -.2));


//   }
// }
