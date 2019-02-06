package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class TurnToAngleGyro extends PIDCommand {
	public static final double P = 0.05, I = 0.0, D = 0.0;
	protected double initialAngle;

	public TurnToAngleGyro(double angle) {
		super("TurnToAngleGyro", P, I, D);
		setSetpoint(angle);
	}

	@Override
	protected void initialize() {
		initialAngle = Robot.gyro.getRotation();
	}

	@Override
	protected double returnPIDInput() {
		return Robot.gyro.getRotation();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drivetrain.setSpeed(output, -output); //does it need to be -output if we've inverted right motors?
	}

	@Override
	protected boolean isFinished() {
		return false; //TODO fix this
	}
	
}