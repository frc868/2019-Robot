package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Helper;
import frc.robot.RobotMap;

public class Drivetrain {
    private CANSparkMax leftPrimary, leftSecondary, rightPrimary, rightSecondary;

    public Drivetrain() {
        leftPrimary = new CANSparkMax(RobotMap.Drivetrain.LEFT_PRIMARY, MotorType.kBrushless);
        leftSecondary = new CANSparkMax(RobotMap.Drivetrain.LEFT_SECONDARY, MotorType.kBrushless);
        rightPrimary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_PRIMARY, MotorType.kBrushless);
        rightSecondary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_SECONDARY, MotorType.kBrushless);

        rightPrimary.setInverted(true);
        rightSecondary.setInverted(true);

        leftSecondary.follow(leftPrimary);
        rightSecondary.follow(rightPrimary);
    }

    public void setLeftSpeed(double speed) {
        leftPrimary.set(Helper.boundValue(speed));
    }

    public void setRightSpeed(double speed) {
        rightPrimary.set(Helper.boundValue(speed));
    }

    public void setSpeed(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void off() {
        leftPrimary.stopMotor();
        rightPrimary.stopMotor();
    }

    public double getLeftSpeed() {
        return leftPrimary.get();
    }

    public double getRightSpeed() {
        return rightPrimary.get();
    }

    public double getSpeed() {
        return (getLeftSpeed() + getRightSpeed())/2;
    }

    public double getLeftEncPosition() {
        return leftPrimary.getEncoder().getPosition();
    }

    public double getRightEncPosition() {
        return rightPrimary.getEncoder().getPosition();
    }

    public double getEncPosition() {
        return (getLeftEncPosition() + getRightEncPosition())/2;
    }

    public double getLeftEncVelocity() {
        return leftPrimary.getEncoder().getVelocity();
    }

    public double getRightEncVelocity() {
        return rightPrimary.getEncoder().getVelocity();
    }

    public double getEncVelocity() {
        return (getLeftEncVelocity() + getRightEncVelocity())/2;
    }
}