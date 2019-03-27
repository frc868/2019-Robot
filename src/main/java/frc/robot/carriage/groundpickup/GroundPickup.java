package frc.robot.carriage.groundpickup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.sensors.IRLimit;
// import frc.robot.helpers.subsystems.SubsystemManagerChild;

public class GroundPickup { //extends SubsystemManagerChild {
    private WPI_TalonSRX intake, wrist;
    private Encoder encoder;
    private IRLimit detection_limit;
    public static final double STORED = 0, GIVE_HATCH = 1, INTAKE_HATCH = 2;
    private final boolean HATCH_DETECTED_STATE = true;

    public GroundPickup() {
        // super("GroundPickup");
        intake = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.INTAKE);
        wrist = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.WRIST);
        encoder = new Encoder(RobotMap.Carriage.GroundPickup.WRIST_ENCODER_A, RobotMap.Carriage.GroundPickup.WRIST_ENCODER_B);
        detection_limit = new IRLimit(RobotMap.Carriage.GroundPickup.DETECTION_LIMIT);
    }
    
    /**
     * @param power speed to set intake motor to, from -1 to 1 (bounded if not in interval)
     */
    public void setIntakeSpeed(double power) {
        intake.set(Helper.boundValue(power));
    }

    /**
     * @return speed intake is set to
     */
    public double getIntakeSpeed() {
        return intake.get();
    }

    /**
     * stops the intake motor from running
     */
    public void stopIntake() {
        intake.stopMotor();
    }

    /**
     * @return state of detection limit
     */
    public boolean getDetectionLimit() {
        return detection_limit.get();
    }

    /**
     * @return state of detection limit
     */
    public boolean isHatchDetected() {
        return getDetectionLimit() == HATCH_DETECTED_STATE;
    }

    /**
     * @param power speed to set wrist to (bounded into [-1, 1] & will not function if limit tripped)
     */
    public void setWristSpeed(double power) {
        wrist.set(Helper.boundValue(power));
    }

    /**
     * @return speed wrist is set to
     */
    public double getWristSpeed() {
        return wrist.get();
    }

    /**
     * stops the wrist motor from running
     */
    public void stopWrist() {
        wrist.stopMotor();
    }

    /**
     * @return position wrist is at
     */
    public double getWristEncPosition() {
        return encoder.get();
    }

    /**
     * @return true if wrist is past forward limit
     */
    public boolean getWristForwardLimit() {
        return getWristEncPosition() > INTAKE_HATCH;
    }

    /**
     * @return false if wrist is before reverse limit
     */
    public boolean getWristReverseLimit() {
        return getWristEncPosition() < STORED;
    }

    // @Override
    // public void init() {
    //     encoder.reset();
    // }

    // @Override
    // public void initSD() {
    //     addTab("Intake Motor", intake);
    //     addTab("Wrist Motor", wrist);
    //     addTab("Encoder", encoder);
    //     addTab("Detection Limit", detection_limit);
    // }

    // @Override
    // public void updateSD() {
    //     SmartDashboard.putNumber("Ground Pickup: Intake Speed", getIntakeSpeed());
    //     SmartDashboard.putNumber("Ground Pickup: Wrist Speed", getWristSpeed());
    //     SmartDashboard.putNumber("Ground Pickup: Wrist Position", getWristEncPosition());
    //     SmartDashboard.putBoolean("Ground Pickup: Is Hatch Detected", isHatchDetected());
    // }
}
