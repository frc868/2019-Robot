/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.groundpickup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;
import edu.wpi.first.wpilibj.Encoder;

public class GroundPickup extends SubsystemManagerChild {
    private WPI_TalonSRX intake, wrist;
    // private Encoder encoder;
    // private DigitalInput forward_limit, reverse_limit, detection_limit;

    public GroundPickup() {
        intake = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.INTAKE);
        // detection_limit = new DigitalInput(RobotMap.Carriage.GroundPickup.REVERSE_LIMIT);

        wrist = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.WRIST);
        // encoder = new Encoder(RobotMap.Carriage.GroundPickup.WRIST_ENCODER_A, 
            // RobotMap.Carriage.GroundPickup.WRIST_ENCODER_B);
        // forward_limit = new DigitalInput(RobotMap.Carriage.GroundPickup.FORWARD_LIMIT);
        // reverse_limit = new DigitalInput(RobotMap.Carriage.GroundPickup.REVERSE_LIMIT);
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
     * @return whether a hatch is detected
     */
    public boolean isHatchDetected() {
        return false;
        // return detection_limit.get();
    }

    /**
     * @param power speed to set wrist to (bounded into [-1, 1] & will not function if limit tripped)
     */
    public void setWristSpeed(double power) {
        if (!getWristLimits()) {
            wrist.set(Helper.boundValue(power));
        }
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
        return 0;
        // return encoder.get();
    }

    /**
     * @return whether the forward limit has been triggered
     */
    public boolean getForwardLimit() {
        // return forward_limit.get();
        return false;
    }

    /**
     * @return whether the reverse limit has been triggered
     */
     public boolean getReverseLimit() {
        // return reverse_limit.get();
        return false;
     }

     /**
      * @return whether either limit has been tripped
      */
     public boolean getWristLimits() {
        return getForwardLimit() || getReverseLimit();
     }

    @Override
    public void update() {
        if (getWristLimits()) {
            stopWrist();
        } 
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Hatch Pickup Intake Speed", getIntakeSpeed());
        SmartDashboard.putBoolean("Hatch Pickup Hatch Detected", isHatchDetected());

        SmartDashboard.putNumber("Hatch Pickup Wrist Speed", getWristSpeed());
        SmartDashboard.putNumber("Hatch Pickup Wrist Position", getWristEncPosition());
        SmartDashboard.putBoolean("Hatch Pickup Forward Limit", getForwardLimit());
        SmartDashboard.putBoolean("Hatch Pickup Reverse Limit", getReverseLimit());
    }
}
