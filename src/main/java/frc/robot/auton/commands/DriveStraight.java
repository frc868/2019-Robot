package frc.robot.auton.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.helpers.Helper;

public class DriveStraight extends PIDCommand {
    private static final double P = 0.0001, I = 0.0, D = 0.0; //TODO: tune these constants
    public double initialDistance;
    public double targetDistance, targetPower, targetAngleChange;

    public DriveStraight(double targetDistance, double targetPower) {
        super(P, I, D);
        this.targetDistance = targetDistance;
        this.targetPower = targetPower;
        requires(Robot.drivetrain);
    }
    
    public DriveStraight(double targetDistance, double targetPower, double targetAngleChange) {
        this(targetDistance, targetPower);
        this.targetAngleChange = targetAngleChange;
    }


    @Override
    protected void initialize() {
        setSetpoint(Robot.drivetrain.getGyroAngle() + targetAngleChange); //TODO: if it's drive straight, wouldn't target angle change be 0?
        // setSetpoint(targetDistance);
        getPIDController().setAbsoluteTolerance(1);
        initialDistance = Robot.drivetrain.getAvgScaledDistance();
        
    }

    @Override
    protected double returnPIDInput() {
        return Robot.drivetrain.getGyroAngle();
        // return Robot.drivetrain.getAvgEncPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        double left = targetPower + output;
        double right = targetPower - output;
        Robot.drivetrain.setSpeed(Helper.boundValue(left), Helper.boundValue(right));
    }


    @Override
    protected boolean isFinished() {
        SmartDashboard.putNumber("dist", distanceToTarget());
        // Robot.drivetrain.stop();
        return distanceToTarget() < 0;
        // SmartDashboard.putNumber("Distance to Target", distanceToTarget());
        // SmartDashboard.putBoolean("isFinished", Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDistance) >= getSetpoint());
        // return Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDistance) >= getSetpoint();
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    protected double distanceToTarget() {
        return Math.abs(targetDistance) - Math.abs(Robot.drivetrain.getAvgScaledDistance() - initialDistance);
    }
}