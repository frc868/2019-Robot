package frc.robot.drivetrain;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;

public class DrivetrainNEO extends SubsystemManagerChild {
    private CANSparkMax leftPrimary, leftSecondary, leftTertiary, rightPrimary, rightSecondary, rightTertiary;
    private AnalogGyro gyro;
    private final double INCHES_PER_TICK = 0.0045998; // TODO: these values may need to be changed
    private final double DEGREES_PER_LOOP = 0.1;
    private double angleOffset = 0;

    public DrivetrainNEO() {
        leftPrimary = new CANSparkMax(RobotMap.Drivetrain.LEFT_PRIMARY, MotorType.kBrushless);
        leftSecondary = new CANSparkMax(RobotMap.Drivetrain.LEFT_SECONDARY, MotorType.kBrushless);
        leftTertiary = new CANSparkMax(RobotMap.Drivetrain.LEFT_TERTIARY, MotorType.kBrushless);

        rightPrimary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_PRIMARY, MotorType.kBrushless);
        rightSecondary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_SECONDARY, MotorType.kBrushless);
        rightTertiary = new CANSparkMax(RobotMap.Drivetrain.RIGHT_TERTIARY, MotorType.kBrushless);

        leftSecondary.follow(leftPrimary);
        leftTertiary.follow(leftPrimary);
        rightSecondary.follow(rightPrimary);
        rightTertiary.follow(rightPrimary);

        leftPrimary.setInverted(false);
        rightPrimary.setInverted(true);

        gyro = new AnalogGyro(RobotMap.Drivetrain.GYRO);
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
        SmartDashboard.putNumber("Left Speed", getLeftSpeed());
        SmartDashboard.putNumber("Right Speed", getRightSpeed());
        SmartDashboard.putNumber("Left Position", getLeftEncPosition());
        SmartDashboard.putNumber("Right Position", getRightEncPosition());
        SmartDashboard.putNumber("Angle", getGyroAngle());
    }
}