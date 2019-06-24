/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.carriage.hatchclaw.ToggleClaw;
import frc.robot.carriage.tilt.SetTiltPosition;
import frc.robot.carriage.tilt.Tilt;
import frc.robot.climberelevator.powerpack.PowerPack;
import frc.robot.climberelevator.powerpack.SetElevatorPosition;
import frc.robot.drivetrain.commands.DriveStraightNoPID;
import frc.robot.drivetrain.commands.TurnToAngleGyro;
import frc.robot.sensors.camera.FollowVision;

public class RHab2ToFrontRocket extends CommandGroup { //TODO: finished condition in followvision
  /**
   * Drives off Hab 2, turns, begins vision
   * @param position the position on rocket to place hatch: 1 for lower, 2 for mid, 3 for upper
   */
  public RHab2ToFrontRocket(int position) {
    addSequential(new DriveStraightNoPID(36,0.3,0.3));
    addSequential(new TurnToAngleGyro(45));
    addSequential(new FollowVision());
    if(position == 1) {
      addParallel(new SetTiltPosition(Tilt.MIDDLE));
      addSequential(new ToggleClaw());
    }
    else if(position == 2) {
      addParallel(new SetTiltPosition(Tilt.MIDDLE));
      addSequential(new SetElevatorPosition(PowerPack.MIDDLE_HATCH));
      addSequential(new ToggleClaw());
    }
    else if(position == 3) {
      addSequential(new SetElevatorPosition(PowerPack.UPPER_HATCH));
      addSequential(new SetTiltPosition(Tilt.MIDDLE));
      addSequential(new ToggleClaw());
    }
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
