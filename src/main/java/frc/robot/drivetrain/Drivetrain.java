package frc.robot.drivetrain;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.drivetrain.commands.ArcadeDrive;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class Drivetrain extends SubsystemManagerChild {
    private WPI_TalonSRX leftPrimary, leftSecondary, leftTertiary, rightPrimary, rightSecondary, rightTertiary;
    private AnalogGyro gyro;
    private final double INCHES_PER_TICK = 0.0045998; 

    public Drivetrain() {
        leftPrimary = new WPI_TalonSRX(RobotMap.Drivetrain.LEFT_PRIMARY);
        leftSecondary = new WPI_TalonSRX(RobotMap.Drivetrain.LEFT_SECONDARY);
        leftTertiary = new WPI_TalonSRX(RobotMap.Drivetrain.LEFT_TERTIARY);

        rightPrimary = new WPI_TalonSRX(RobotMap.Drivetrain.RIGHT_PRIMARY);
        rightSecondary = new WPI_TalonSRX(RobotMap.Drivetrain.RIGHT_SECONDARY);
        rightTertiary = new WPI_TalonSRX(RobotMap.Drivetrain.RIGHT_TERTIARY);

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
        return leftPrimary.getSelectedSensorPosition();
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
        return rightPrimary.getSelectedSensorPosition();
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
        leftPrimary.setSelectedSensorPosition(0);
        rightPrimary.setSelectedSensorPosition(0);
    }

    /**
     * 
     * @return angle given by the gyro
     */
    public double getGyroAngle() {
        return gyro.getAngle();
    }

    /**
     * resets gyro angle
     */
    public void resetGyro() {
        gyro.reset();
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Left Speed", getLeftSpeed());
        SmartDashboard.putNumber("Right Speed", getRightSpeed());
        SmartDashboard.putNumber("Left Position", getLeftEncPosition());
        SmartDashboard.putNumber("Right Position", getRightEncPosition());
    }
}