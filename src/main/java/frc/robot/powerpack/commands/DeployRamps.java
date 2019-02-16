/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.powerpack.commands;
 
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DeployRamps extends Command {
  public DeployRamps() {
    requires(Robot.climberRamps);
  }

  @Override
  protected void initialize() {
    Robot.climberRamps.open();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
