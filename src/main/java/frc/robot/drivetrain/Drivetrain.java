package frc.robot.drivetrain;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.motorcontrollers.CANSparkMaxPlus;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class Drivetrain extends SubsystemManagerChild {
    private CANSparkMaxPlus leftPrimary, leftSecondary, leftTertiary, rightPrimary, rightSecondary, rightTertiary;
    private AnalogGyro gyro;
    private final double INCHES_PER_TICK = 0.0045998; 
    private final double DEGREES_PER_LOOP = 0.1;
    private double angleOffset = 0;

    public Drivetrain() {
        super("Drivetrain");
        leftPrimary = new CANSparkMaxPlus(RobotMap.Drivetrain.LEFT_PRIMARY);
        leftSecondary = new CANSparkMaxPlus(RobotMap.Drivetrain.LEFT_SECONDARY);
        leftTertiary = new CANSparkMaxPlus(RobotMap.Drivetrain.LEFT_TERTIARY);

        rightPrimary = new CANSparkMaxPlus(RobotMap.Drivetrain.RIGHT_PRIMARY);
        rightSecondary = new CANSparkMaxPlus(RobotMap.Drivetrain.RIGHT_SECONDARY);
        rightTertiary = new CANSparkMaxPlus(RobotMap.Drivetrain.RIGHT_TERTIARY);

        leftSecondary.follow(leftPrimary);
        leftTertiary.follow(leftPrimary);
        rightSecondary.follow(rightPrimary);
        rightTertiary.follow(rightPrimary);

        leftPrimary.setInverted(false);
        leftSecondary.setInverted(false);
        leftTertiary.setInverted(false);
        rightPrimary.setInverted(true);
        rightSecondary.setInverted(true);
        rightTertiary.setInverted(true);

        brakeOn();

        gyro = new AnalogGyro(RobotMap.Drivetrain.GYRO);
     }

    public void brakeOff() {
        leftPrimary.setIdleMode(IdleMode.kCoast);
        leftSecondary.setIdleMode(IdleMode.kCoast);
        leftTertiary.setIdleMode(IdleMode.kCoast);
        rightPrimary.setIdleMode(IdleMode.kCoast);
        rightSecondary.setIdleMode(IdleMode.kCoast);
        rightTertiary.setIdleMode(IdleMode.kCoast);
     }

     public void brakeOn() {
        leftPrimary.setIdleMode(IdleMode.kBrake);
        leftSecondary.setIdleMode(IdleMode.kBrake);
        leftTertiary.setIdleMode(IdleMode.kBrake);
        rightPrimary.setIdleMode(IdleMode.kBrake);
        rightSecondary.setIdleMode(IdleMode.kBrake);
        rightTertiary.setIdleMode(IdleMode.kBrake);
     }

    /**
     * sets left motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setLeftSpeed(double speed) {
        leftPrimary.set(Helper.boundValue(speed));
    }

    /** 
     *  @param adjustment adjustment to be added to previous left motor speed
     */
    public void adjustLeftSpeed(double adjustment) {
        setLeftSpeed(getLeftSpeed() + adjustment);
    }

     /**
     * sets right motor's speed
     * @param speed percentage power from -1 to 1
     */
    public void setRightSpeed(double speed) {
        rightPrimary.set(Helper.boundValue(speed));
    }

    /** 
     *  @param adjustment adjustment to be added to previous right motor speed
     */
    public void adjustRightSpeed(double adjustment) {
        setRightSpeed(getRightSpeed() + adjustment);
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
     * 
     * @param leftAdjustment adjustment to be added to previous left motor speed
     * @param rightAdjustment adjustment to be added to previous right motor speed
     */
    public void adjustSpeed(double leftAdjustment, double rightAdjustment) {
        adjustLeftSpeed(leftAdjustment);
        adjustRightSpeed(rightAdjustment);
    }
  
    /**
     * turn off both motors
     */
    public void stop() {
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
     * 
     * @return distance travelled by left side, scaled to inches
     */
    public double getScaledLeftDistance() {
        return getLeftEncPosition() * INCHES_PER_TICK;
    }

    /**
     * @return position of right motor according to encoder
     */
    public double getRightEncPosition() {
        return rightPrimary.getEncoder().getPosition();
    }

    /**
     * 
     * @return distance travelled by left side, scaled to inches
     */
    public double getScaledRightDistance() {
        return getRightEncPosition() * INCHES_PER_TICK;
    }

    /**
     * @return average position of motors according to encoder
     */
    public double getAvgEncPosition() {
        return (getLeftEncPosition() + getRightEncPosition())/2;
    }

    /**
     * 
     * @return average distance travelled, scalled to inches
     */
    public double getAvgScaledDistance() {
        return getAvgEncPosition() * INCHES_PER_TICK;
    }

    /**
     * resets encoder counts on gyros
     */
    public void resetEncoders(){
        leftPrimary.getEncoder().setPosition(0);
        rightPrimary.getEncoder().setPosition(0);
    }

    /**
     * 
     * @return angle given by the gyro
     */
    public double getGyroAngle() {
        return gyro.getAngle() + angleOffset;
    }

    /**
     * 
     * @return angle from gyro in radians
     */
    public double getGyroRestrictedAngleRadians() {
        return Math.abs(Math.toRadians(getGyroAngle())%(2*Math.PI));
    }

    /**
     * resets gyro angle
     */
    public void resetGyro() {
        gyro.reset();
    }

    @Override
    public void update() {
        angleOffset += DEGREES_PER_LOOP;
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Left Position", getLeftEncPosition());
        SmartDashboard.putNumber("Right Position", getRightEncPosition());
        SmartDashboard.putNumber("Angle", getGyroAngle());
    }

    @Override
    public void initDebug() {
        addDebug("Gyro", gyro);
    }

    @Override
    public void updateDebug() {
        addDebug("Drivetrain Right Position", getRightEncPosition());
        addDebug("Drivetrain Left Position", getLeftEncPosition());
    }

    @Override
    public void initTab() {
        addTab("Gyro", gyro);
    }

    @Override
    public void updateTab() {
        addTab("Drivetrain Right Position", getRightEncPosition());
        addTab("Drivetrain Left Position", getLeftEncPosition());
    }
}