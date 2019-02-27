package frc.robot.drivetrain.commands;

import frc.robot.Robot;

public class TurnByAngleGyro extends TurnToAngleGyro {
	public TurnByAngleGyro(double angleChange) {
		super(angleChange);
	}

	@Override
	protected void initialize() {
		setSetpointRelative(Robot.drivetrain.getGyroAngle());
	}
}