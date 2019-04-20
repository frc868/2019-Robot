package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToAngleGyro extends PIDCommand {
	public static final double P = 0.01, I = 0.0, D = 0.01;
	private double angle;
	private boolean turnright;

	public TurnToAngleGyro(boolean turnright, double angle) {
		super(P, I, D);
		requires(Robot.drivetrain);
		requires(Robot.gyro);
		this.turnright = turnright;
		this.angle = angle;
		super.getPIDController().setAbsoluteTolerance(1.5);
		setTimeout(1);
	}

	@Override
	protected void initialize() {
		Robot.gyro.reset();
		// angle = Math.abs(angle);
		// System.out.println("Adding " + angle + " to gyro value " + Robot.gyro.getAngle());
		// if (turnright) {
		// 	angle = Robot.gyro.getAngle() + angle;
		// } else {
		// 	angle = Robot.gyro.getAngle() - angle;
		// }
		SmartDashboard.putNumber("setpoint", angle);
		setSetpoint(angle);		
		System.out.println("Setpoint is now " + getSetpoint());
	}

	@Override
	protected double returnPIDInput() {
		return Robot.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drivetrain.setSpeed(output, -output);
		SmartDashboard.putNumber("gyro error", super.getPIDController().getError());
		System.out.println(output);
	}

	@Override
	protected boolean isFinished() {
		return super.getPIDController().onTarget();
	}

	

}