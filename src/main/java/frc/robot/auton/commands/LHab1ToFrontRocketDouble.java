/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition;
import frc.robot.climberelevator.powerpack.SmartSetElevatorPosition.Height;
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.drivetrain.commands.DriveStraightNoPID;
import frc.robot.drivetrain.commands.TurnToAngleGyro;
import frc.robot.sensors.camera.FollowVision;


public class LHab1ToFrontRocketDouble extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LHab1ToFrontRocketDouble(int position) {
    addSequential(new LHab1ToFrontRocket(position));

    // addSequential(new DriveStraightNoPID(36,0.3,0.3));
    // addSequential(new TurnToAngleGyro(-45));
    // addSequential(new FollowVision(true, 70));
    // if(position == 1) {
    //   // addParallel(new SetTiltPosition(Tilt.MIDDLE));
    //   addSequential(new Release());
    // }

    addSequential(new DriveStraightNoPID(-36,-0.5,-0.5));
    // addSequential(new TurnToAngleGyro(-100));
    // addSequential(new FollowVision(true, 70));
    // addSequential(new Grab());
    // addSequential(new DriveStraightNoPID(-150,0.3,0.3));
    // addSequential(new TurnToAngleGyro(45));
    // addSequential(new FollowVision(true,70));
    // addSequential(new SmartSetElevatorPosition(Height.middle));
    // addSequential(new Release());
  }
}
