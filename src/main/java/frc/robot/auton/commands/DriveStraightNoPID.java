package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class DriveStraightNoPID extends Command {
  public double initialDistance;
  public double setpoint;
  public double effort;
  private int i = 0;
  private final double kP = 0.00003;

  public DriveStraightNoPID(double setpoint, double effort) {
    requires(Robot.drivetrain);
    this.setpoint = setpoint;
    this.effort = effort;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialDistance = Robot.drivetrain.getAvgEncPosition();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftSpeed = kP*(Math.abs(Robot.drivetrain.getLeftEncPosition() - setpoint));
    double rightSpeed = kP*(Math.abs(Robot.drivetrain.getRightEncPosition() - setpoint));
    Robot.drivetrain.setSpeed(Helper.boundValue(leftSpeed), Helper.boundValue(rightSpeed));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("IsFinished?", Math.abs(Robot.drivetrain.getAvgEncPosition() - initialDistance) >= setpoint);
    return Math.abs(Robot.drivetrain.getAvgEncPosition() - initialDistance) >= setpoint;
    // return i > 500;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
