/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.carriage.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.helpers.Helper;
import frc.robot.helpers.SubsystemManagerChild;

public class HatchPickup extends SubsystemManagerChild {
  
    private WPI_TalonSRX intake, wrist;

    public HatchPickup() {
        intake = new WPI_TalonSRX(RobotMap.Carraige.HATCH_PICKUP_INTAKE);
        wrist = new WPI_TalonSRX(RobotMap.Carraige.HATCH_PICKUP_WRIST);
    }
    
    public void setIntakeSpeed(double power) {
        intake.set(Helper.boundValue(power));
    }

    public double getIntakeSpeed() {
        return intake.get();
    }

    public void setWristSpeed(double power) {
        wrist.set(Helper.boundValue(power));
    }

    public double getWristSpeed() {
        return wrist.get();
    }

    public double getWristEncPosition() {
        return wrist.getSelectedSensorPosition();
    }


}
