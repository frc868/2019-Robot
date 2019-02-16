/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CloseClaw extends Command {
  public CloseClaw() {
    requires(Robot.hatchClaw);
  }

  @Override
  protected void initialize() {
    Robot.hatchClaw.close();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
