package frc.robot.carriage.ballintake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.sensors.IRLimit;
import frc.robot.helpers.subsystems.SubsystemManagerChild;
import frc.robot.oi.Rumble;

public class BallIntake extends SubsystemManagerChild {
    private WPI_TalonSRX motor; // motor that will intake/outtake ball
    public IRLimit detection_limit; // IR limit to detect balls
    private final boolean BALL_DETECTED_STATE = true; // state the the IR will be set to if the ball is detected

    /**
     * subsytem for ball intaking and outtaking, rides on the elevator
     */
    public BallIntake() {
        super("BallIntake");
        motor = new WPI_TalonSRX(RobotMap.Carriage.BallIntake.MOTOR);
        detection_limit = new IRLimit(RobotMap.Carriage.BallIntake.DETECTION_LIMIT);

        motor.setInverted(true); // inverts intake motor
        motor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * sets motor's speed
     * @param speed percentage power from -1 to 1 (anything outside of this range will be bounded)
     */
    public void setSpeed(double speed) {
        motor.set(Helper.boundValue(speed));
    }

    /**
     * turns off the motor
     */
    public void stop() {
        motor.stopMotor();
    }

    /**
     * @return speed that the motor is set to
     */
    public double getSpeed() {
        return motor.get();
    }

    /**
     * @return state of ball detection limit switch
     */
    public boolean getBallDetectionLimit() {
        return detection_limit.get();
    }


    /**
     * @return true if ball is detected, else false
     */
    public boolean isBallDetected() {
        // sees if limit state is the same as the state in which a ball is detected
        return getBallDetectionLimit() == BALL_DETECTED_STATE; 
    }

    @Override
    public void init() {
        // rumbles both controllers when the limit is tripped
        detection_limit.getTrigger().whenActive(new Rumble());
    }

    @Override
    public void initSD() {
        addTab("Motor", motor);
        addTab("Detection Limit", detection_limit);
    }

    @Override
    public void updateSD() {
        SmartDashboard.putBoolean("Ball Detected?", isBallDetected());
        SmartDashboard.putNumber("Intake Speed", getSpeed());
    }
}
