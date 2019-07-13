/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DoNothing extends Command {
  public DoNothing() {
    requires(Robot.drivetrain);
    requires(Robot.powerPack);
    requires(Robot.tilt);
    requires(Robot.gyro);
    requires(Robot.footDrive);
    requires(Robot.forks);
    requires(Robot.hatchClaw);
    requires(Robot.camera);
    requires(Robot.ballIntake);
    requires(Robot.climberRamps);
  
    setInterruptible(true);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.setSpeed(0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
