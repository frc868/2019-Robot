/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class DriveStraightNoPID extends Command {
  private double targetDist,startPower,endPower,initialDist;

  private static final double pGain = 0.5;
  public DriveStraightNoPID(double targetDist,double startPower,double endPower) {
    requires(Robot.drivetrain);
    this.targetDist = targetDist;
    this.startPower = startPower;
    this.endPower = endPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Started");
    this.initialDist = Robot.drivetrain.getAvgScaledDistance();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double targetSpeed = startPower + ((endPower - startPower) / distanceToTarget());
    // System.out.println(targetSpeed + "   : TARGETSPEED");
    targetSpeed *= pGain;
    targetSpeed = Helper.boundValue(targetSpeed);
    Robot.drivetrain.setSpeed(targetSpeed, targetSpeed);
    SmartDashboard.putNumber("Drive Straight No PID Error", targetDist - Robot.drivetrain.getAvgScaledDistance());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.drivetrain.getAvgScaledDistance() - initialDist) > targetDist;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // System.out.println("ENDED");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  
  protected double distanceToTarget() {
            return Math.abs(targetDist) - Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDist);
        }
}
