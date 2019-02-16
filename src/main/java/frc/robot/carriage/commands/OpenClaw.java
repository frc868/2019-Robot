/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OpenClaw extends Command {
  public OpenClaw() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    Robot.hatchClaw.open();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
