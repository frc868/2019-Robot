/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Grab;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.drivetrain.commands.DriveStraight;
import frc.robot.drivetrain.commands.TurnToAngleGyro;
import frc.robot.sensors.camera.FollowVision;

public class LeftRocketFrontDouble extends CommandGroup {
  /**
   * Place hatch on left front rocket, get hatch from loading station, place hatch on 2nd level left front rocket
   */
  public LeftRocketFrontDouble() {
    
    // Set Tilt and Elevator
    addParallel(new SetTiltPosition(Tilt.MIDDLE));
    addParallel(new SetElevatorPosition(PowerPack.LOWER_HATCH));

    // Drive to the Rocket
    addSequential(new DriveStraight(184, .4, -28.75)); //TODO: after testing, add ramping and tune values

    // Follow vision into the rocket and score hatch
    addSequential(new FollowVision(), 3);  //TODO: make shorter after testing
    addSequential(new Release());

    // Back out and turn towards player station
    addSequential(new DriveStraight(-50, -.2, -150));

    // Drive to player Station
    addSequential(new DriveStraight(200, .4));

    // Follow vision into player station and grab hatch
    addSequential(new FollowVision(), 3);
    addSequential(new Grab());

    // Back out and turn
    addSequential(new DriveStraight(-50, -.2));
    addSequential(new TurnToAngleGyro(false,-180));

    // Drive to rocket and set elevator height
    addSequential(new DriveStraight(180, .4));
    addSequential(new SetElevatorPosition(PowerPack.MIDDLE_HATCH));

    // Follow Vision and score hatch
    addSequential(new FollowVision(), 3);
    addSequential(new Release());

    // Back out
    addSequential(new DriveStraight(-30, -.2));

  }
}
