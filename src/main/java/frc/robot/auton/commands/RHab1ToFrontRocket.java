/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.Release;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.drivetrain.commands.DriveStraightNoPID;
import frc.robot.drivetrain.commands.TurnToAngleGyro;
import frc.robot.sensors.camera.FollowVision;

public class RHab1ToFrontRocket extends CommandGroup {
  /**
   * Drives off Hab 2, turns, begins vision
   * @param position the position on rocket to place hatch: 1 for lower, 2 for mid, 3 for upper
   */
  public RHab1ToFrontRocket(int position) {
    addSequential(new DriveStraightNoPID(36,0.3,0.3));
    addParallel(new SetTiltPosition(Tilt.MIDDLE));

    addSequential(new TurnToAngleGyro(50));
    addSequential(new FollowVision(true,70));
    if(position == 1) {
      
      addSequential(new Release());
    }
    else if(position == 2) {
      // addParallel(new SetTiltPosition(Tilt.MIDDLE));
      addSequential(new SetElevatorPosition(PowerPack.MIDDLE_HATCH));
      addSequential(new Release());
    }
    else if(position == 3) {
      addSequential(new SetElevatorPosition(PowerPack.UPPER_HATCH));
      // addSequential(new SetTiltPosition(Tilt.MIDDLE));
      addSequential(new Release());
    }
  }
}
