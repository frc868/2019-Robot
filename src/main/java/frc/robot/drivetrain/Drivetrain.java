package frc.robot.drivetrain;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Helper;
import frc.robot.RobotMap;

public class Drivetrain {
    private CANSparkMax leftPrimary, leftSecondary, rightPrimary, rightSecondary;
    private double P, I, D, F;
    private CANPIDController rightPID, leftPID;

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

    /**
     * initiates a PID controller for the left and right motors
     */
    private void initPID(){
        rightPID = new CANPIDController(rightPrimary);
        leftPID = new CANPIDController(leftPrimary);

        setDrivePIDFValues(0.01, 0.01, 0.01, 0.01);
        rightPID.setOutputRange(-1, 1);
        leftPID.setOutputRange(-1, 1);
    }

    /**
     * sets PIDF values for both sides
     * @param P propotional, Pe, based on error it adds that times the P value
     * @param I integral, used for compensating for constant forces automagically
     * @param D derivative, used to prevent overshooting
     * @param F Feed-forward, filter for the first derivative
     */
    public void setDrivePIDFValues(double P, double I, double D, double F){
        P = this.P;
        I = this.I;
        D = this.D;
        F = this.F;
        rightPID.setP(P);
        leftPID.setP(P);
        rightPID.setI(I);
        leftPID.setI(I);
        rightPID.setD(D);
        leftPID.setD(D);
        rightPID.setFF(F);
        leftPID.setFF(F);
    }

    public void setPID(double right, double left, ControlType ctrl){
        rightPID.setReference(right, ctrl);
        leftPID.setReference(left, ctrl);
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
     * @return speed left motor is set to
     */
    public double getLeftSpeed() {
        return leftPrimary.get();
    }

    /**
     * @return speed right motor is set to
     */
    public double getRightSpeed() {
        return rightPrimary.get();
    }

    /**
     * @return position of left motor according to encoder
     */
    public double getLeftEncPosition() {
        return leftPrimary.getEncoder().getPosition();
    }

    /**
     * @return position of right motor according to encoder
     */
    public double getRightEncPosition() {
        return rightPrimary.getEncoder().getPosition();
    }

    /**
     * @return average position of motors according to encoder
     */
    public double getEncPosition() {
        return (getLeftEncPosition() + getRightEncPosition())/2;
    }

    /**
     * @return velocity of left motor according to encoder
     */
    public double getLeftEncVelocity() {
        return leftPrimary.getEncoder().getVelocity();
    }

    /**
     * @return velocity of right motor according to encoder
     */
    public double getRightEncVelocity() {
        return rightPrimary.getEncoder().getVelocity();
    }

    /**
     * @return average velocity of motors according to encoder
     */
    public double getEncVelocity() {
        return (getLeftEncVelocity() + getRightEncVelocity())/2;
    }
}