package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveStraight extends Command {
	
	protected double targetPower;
	protected double initialDistance;
	protected final double targetDistance;
	protected double initialAngle;
	protected boolean readInitialAngle = false;
	
	public DriveStraight(double inches, double power) {
		this(inches, power, 0);
		readInitialAngle = true;
	}
	
    public DriveStraight(double inches, double power, double targetAngle) {
    	requires(Robot.drivetrain);
    	this.targetDistance = inches;
    	this.targetPower = power;
    	this.initialAngle = targetAngle;
    }

    protected void initialize() {
    	if (readInitialAngle) {
    		initialAngle = Robot.gyro.getRotation();
    	}
    	initialDistance = Robot.drivetrain.getScaledAverageDistance();
    }

    protected void execute() {
    	double setRight = targetPower;
    	double setLeft = targetPower;
    	
    	double angleError = Robot.gyro.getRotation() - initialAngle;
    	double angleP = (angleError / 50);
    	
    	// fix for driving backwards
    	if (targetPower < 0) {
    		angleP *= -1;
    	}
    	
    	Robot.drivetrain.setSpeed(setRight * (1+angleP), setLeft * (1-angleP));
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getScaledAverageDistance() - initialDistance) > Math.abs(targetDistance);
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
	}
}
