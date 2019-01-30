package frc.robot.drivetrain;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Helper;
import frc.robot.RobotMap;
import frc.robot.helpers.SubsystemManagerChild;

public class Drivetrain extends SubsystemManagerChild {
    private CANSparkMax leftPrimary, leftSecondary, rightPrimary, rightSecondary;

    public Drivetrain() {
        leftPrimary = new CANSparkMax(RobotMap.Drivetrain.LEFT_PRIMARY, MotorType.kBrushless);
        leftSecondary = new CANSparkMax(RobotMap.Drivetrain.LEFT_SECONDARY, MotorType.kBrushless);
        rightPrimary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_PRIMARY, MotorType.kBrushless);
        rightSecondary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_SECONDARY, MotorType.kBrushless);

        leftSecondary.follow(leftPrimary);
        rightSecondary.follow(rightPrimary);

        leftPrimary.setInverted(RobotMap.Drivetrain.IS_LEFT_INVERTED);
        rightPrimary.setInverted(RobotMap.Drivetrain.IS_RIGHT_INVERTED);
     }

    /**
     * sets left motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setLeftSpeed(double speed) {
        leftPrimary.set(Helper.boundValue(speed));
    }

     /**
     * sets right motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setRightSpeed(double speed) {
        rightPrimary.set(Helper.boundValue(speed));
    }

     /**
     * sets  motors' speed
     * @param leftSpeed left's percentage power from -1 to 1
     * @param rigthSpeed right's percentage power from -1 to 1
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    /**
     * turn off both motors
     */
    public void off() {
        leftPrimary.stopMotor();
        rightPrimary.stopMotor();
    }

    /**
     * 
     * @return speed left motor is set to
     */
    public double getLeftSpeed() {
        return leftPrimary.get();
    }

    /**
     * 
     * @return speed right motor is set to
     */
    public double getRightSpeed() {
        return rightPrimary.get();
    }

    /**
     * 
     * @return position of left motor according to encoder
     */
    public double getLeftEncPosition() {
        return leftPrimary.getEncoder().getPosition();
    }

    /**
     * 
     * @return position of right motor according to encoder
     */
    public double getRightEncPosition() {
        return rightPrimary.getEncoder().getPosition();
    }

    /**
     * 
     * @return average position of motors according to encoder
     */
    public double getEncPosition() {
        return (getLeftEncPosition() + getRightEncPosition())/2;
    }

    /**
     * 
     * @return velocity of left motor according to encoder
     */
    public double getLeftEncVelocity() {
        return leftPrimary.getEncoder().getVelocity();
    }

    /**
     * 
     * @return velocity of right motor according to encoder
     */
    public double getRightEncVelocity() {
        return rightPrimary.getEncoder().getVelocity();
    }

    /**
     * 
     * @return average velocity of motors according to encoder
     */
    public double getEncVelocity() {
        return (getLeftEncVelocity() + getRightEncVelocity())/2;
    }
}