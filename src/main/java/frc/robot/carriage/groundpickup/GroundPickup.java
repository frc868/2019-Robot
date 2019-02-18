/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.groundpickup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;
import edu.wpi.first.wpilibj.Encoder;

public class GroundPickup extends SubsystemManagerChild {
    private WPI_TalonSRX intake, wrist;
    private Encoder encoder;

    public GroundPickup() {
        intake = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.INTAKE);
        wrist = new WPI_TalonSRX(RobotMap.Carriage.GroundPickup.WRIST);
        encoder = new Encoder(RobotMap.Carriage.Tilt.ENCODER_A, RobotMap.Carriage.Tilt.ENCODER_B);
    }
    
    /**
     * 
     * @param power speed to set intake motor to
     */
    public void setIntakeSpeed(double power) {
        intake.set(Helper.boundValue(power));
    }

    /**
     * 
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
     * 
     * @param power speed to set wrist to
     */
    public void setWristSpeed(double power) {
        wrist.set(Helper.boundValue(power));
    }

    /**
     * 
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
     * 
     * @return position wrist is at
     */
    public double getWristEncPosition() {
        return encoder.get();
    }

    @Override
    public void updateSD() {
        SmartDashboard.putNumber("Hatch Pickup Intake Speed", getIntakeSpeed());
        SmartDashboard.putNumber("Hatch Pickup Wrist Speed", getWristSpeed());
        SmartDashboard.putNumber("Hatch Pickup Wrist Position", getWristEncPosition());
    }
}
