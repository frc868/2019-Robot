package frc.robot.drivetrain.commands;

import frc.robot.Robot;
import frc.robot.helpers.pid.PIDCommandPlus;

public class TurnToAngleGyro extends PIDCommandPlus {
	public static final double P = 0.05, I = 0.0, D = 0.0;

	public TurnToAngleGyro(double angle) {
		super(P, I, D, angle);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrainNEO.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drivetrainNEO.setSpeed(output, -output);
	}
}