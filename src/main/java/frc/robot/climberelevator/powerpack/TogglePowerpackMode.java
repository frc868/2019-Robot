/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.climberelevator.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TogglePowerpackMode extends Command {
  public TogglePowerpackMode() {
    requires(Robot.powerPack);
  }

  @Override
  protected void initialize() {
    if (Robot.powerPack.isElevatorMode()) {
        Robot.powerPack.switchToClimber();
    } else {
        Robot.powerPack.switchToElevator();
    }

  }

  @Override
  protected boolean isFinished() {
    return true;
  }

}

