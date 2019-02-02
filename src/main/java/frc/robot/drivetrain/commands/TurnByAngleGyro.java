package frc.robot.drivetrain.commands;

import frc.robot.Robot;
import frc.robot.helpers.Helper;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnByAngleGyro extends TimedCommand {
	private PIDController ctrl;
	private final double P = 0.0125, I = 0, D = 0.0125; // TODO: tune
	private double setAngle;
	private int c = 0;
	private final double MAX_POWER = 0.4;

	/**
	 * Rotates the robot by the given angle.
	 * (Note that this IS relative to the current position.)
	 * Left is negative, right is positive
	 * @param angle in degrees
	 */
    public TurnByAngleGyro(double deg, double timeout) {
        super(timeout);
        requires(Robot.drivetrain);
        setAngle = deg;
        ctrl = new PIDController(P, I, D, new PIDSource() {
        	public void setPIDSourceType(PIDSourceType pidSource) {}
        	
        	public PIDSourceType getPIDSourceType() {
        		return PIDSourceType.kDisplacement;
        	}
        	
        	public double pidGet() {
        		return Robot.gyro.getRotation();
        	}
        }, new PIDOutput() {
        	public void pidWrite(double output) {
        		/*
    			if (output > .05 && output < Drivetrain.MIN_DRIVE_SPEED)
    				output = Drivetrain.MIN_DRIVE_SPEED;
    			if (output < -0.5 && output > -Drivetrain.MIN_DRIVE_SPEED)
    				output = -Drivetrain.MIN_DRIVE_SPEED;
    				*/
    			Robot.drivetrain.setSpeed(Helper.boundValue(-output, -MAX_POWER, MAX_POWER), Helper.boundValue(output, -MAX_POWER, MAX_POWER));
    		}
        });
        ctrl.setAbsoluteTolerance(5);
    	SmartDashboard.putData("By Rotation PID", ctrl);
    }

    public TurnByAngleGyro(double angle) {
    	this(angle, 3);
    }
    
    protected void initialize() {
    	ctrl.setSetpoint(setAngle+Robot.gyro.getRotation());
    	ctrl.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (ctrl.onTarget()) {
    		c++;
    	} else {
    		c = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished() || c > 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ctrl.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}